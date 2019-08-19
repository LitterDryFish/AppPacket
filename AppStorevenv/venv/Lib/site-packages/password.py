"""
This module offers a single descriptor attribute called Password. This
attribute can be used to generate hashed passwords and to check them.
It is up to you to find a way for persistence of hash and salt, so this is
framework-agnostic.

>>> class User(object):
...     password = Password(
...         method='sha1', hash_encoding='base16', keep_on_blank=True
...     )
>>> user = User()
>>> user.password = 'abcdef'
>>> user.password #doctest: +ELLIPSIS
<password._HashedPassword object at ...>
>>> user.password == 'abcdef'
True
>>> user.password == '12345'
False
>>> user.password = ''
>>> user.password == ''
False
>>> user.password == 'abcdef'
True
"""

import hashlib
import base64
import random
import string

SALT_CHARS = string.ascii_letters + string.digits

def _generate_hash_salt(
    password, method, hash_encoding, salt=None, salt_length=None
):
    """Generate the has and salt for given password"""
    salt = salt or ''.join(
        (random.choice(SALT_CHARS) for i in range(salt_length))
    )
    hash_method = getattr(hashlib, method)
    hash = hash_method((password + salt).encode('utf-8')).digest()
    if hash_encoding is None:
        enc_method = lambda x: x
    elif hash_encoding[:4] == 'base':
        def enc_method(value):
            base_method = getattr(
                base64, 'b{0}encode'.format(hash_encoding[4:])
            )
            return base_method(value).decode('ascii')
    return enc_method(hash), salt

class _HashedPassword(object):
    """Object representing the hashed password. Not to be created directly."""

    def __init__(self, hash, salt, method, hash_encoding):
        self.hash = hash
        self.salt = salt
        self.method = method
        self.hash_encoding = hash_encoding
    
    def __eq__(self, s):
        hash, salt = _generate_hash_salt(
            s, self.method, self.hash_encoding, salt=self.salt
        )
        return hash == self.hash

class Password(object):
    """A password attribute.

    :param hash_attr: The attribute of target object that stores hash
    :param salt_attr: The attribute of target object that stores salt
    :param salt_lentht: The length in chars of salt string
    :param method: The hashing method (any method from hashlib)
    :param hash_encoding: The encoding of hash. None for no encoding (byte
        string) or baseXX.
    :param keep_on_blank: If this is set to True, the password will remain
        unchanged if blank string is provided (useful with HTML forms).
    """

    def __init__(
            self, hash_attr='hash', salt_attr='salt', salt_length=8,
            method='sha512', hash_encoding=None, keep_on_blank=False):
        self.hash_attr = hash_attr
        self.salt_attr = salt_attr
        self.salt_length = salt_length
        self.method = method
        self.hash_encoding = hash_encoding
        self.keep_on_blank = keep_on_blank

    def __get__(self, inst, cls):
        if inst is None:
            return self
        return _HashedPassword(
            hash=getattr(inst, self.hash_attr),
            salt=getattr(inst, self.salt_attr), 
            method=self.method,
            hash_encoding=self.hash_encoding,
        )

    def __set__(self, inst, value):
        if value == '' and self.keep_on_blank:
            return
        hash, salt = _generate_hash_salt(
            value, self.method, self.hash_encoding,
            salt_length = self.salt_length
        )
        setattr(inst, self.hash_attr, hash)
        setattr(inst, self.salt_attr, salt)

    def __delete__(self, inst):
        delattr(inst, self.hash_attr, hash)
        delattr(inst, self.salt_attr, salt)
