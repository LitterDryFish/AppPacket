import django
import password as password
from django.db import models
from django.forms import forms
from django.utils import timezone
# Create your models here.

# 用户注册信息
class User(models.Model):
    choices = {
        ("female","男"),
        ("male", "女"),
    }
    username = models.ForeignKey(to="UserInfo", to_field="username", on_delete=models.CASCADE)
    username = models.ForeignKey(to="ApkFile", to_field="username", on_delete=models.CASCADE)
    level = models.CharField(max_length=128, default="Z")  # 用户等级
    nike_name = models.CharField(max_length=128, default="")  # 真实姓名
    username = models.CharField(max_length=128, unique=True, default="", blank=True)  # 用户名或者昵称
    Email = models.EmailField(primary_key=True, blank=True)  # 邮箱
    password = models.CharField(max_length=128, blank=True)  # 密码
    sex = models.CharField(max_length=128, choices=choices, default="男", blank=True)  # 性别
    profile_photo = models.ImageField(default="media/about.png", upload_to='media')  # 头像
    #127.0.0.1:8000/media/jkhjh.jpg
    register_time = models.DateField(default=django.utils.timezone.now, blank=True)  # 注册时间
    def __str__(self):
        return self.username

# 保存用户上传的APK文件
class ApkFile(models.Model):
    username = models.CharField(max_length=128, primary_key=True)
    apk = models.FileField()

    def __str__(self):
        return self.username

#保存app的轮播图片
class Screen(models.Model):
    appName = models.CharField(max_length=128, primary_key=True, unique=True)
    screen = models.ImageField(upload_to='media')
# 用户的使用数据
class UserInfo(models.Model):
    username = models.CharField(max_length=128, unique=True, primary_key=True)
    app_packet = ApkFile.objects.filter(username=username).values_list()  # list用来保存用户上传的软件apk

    def __str__(self):
        return self.username

class AppDetail(models.Model):

    app_type = models.CharField(max_length=128, blank=True, default="软件")  # 软件类型
    title = models.CharField(max_length=128, blank=True, default="----")  # 分类类型
    des = models.TextField()  # 描述
    downloadUrl = models.URLField()  # 下载地址
    iconUrl = models.URLField()  # 图标地址
    id = models.IntegerField(primary_key=True)  # 应用id
    name = models.CharField(max_length=128)  # 应用名
    name = models.ForeignKey(to="Screen", to_field="appName", on_delete=models.CASCADE)  # 设置外键存储轮播图
    packetName = models.CharField(max_length=128)  # 包名
    size = models.IntegerField(default='0')  # 应用大小
    stars = models.FloatField()  # 应用评星
    author = models.CharField(max_length=128, blank=True,)  # 作者
    version = models.CharField(max_length=128, blank=True, default="---")  # 版本号
    downloadNum = models.CharField(max_length=128, blank=True, default="--")  # 下载次数
    date = models.DateField()  # 更新日期
    picture = models.ImageField(default='media/ic_launcher.png', upload_to='media')  # 演示图片
    safe = models.CharField(max_length=128, blank=True, default="safe")  # 软件安全性







