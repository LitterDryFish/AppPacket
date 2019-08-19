from django.contrib import admin

# Register your models here.
from appstore.models import User, AppDetail, Screen, UserInfo, ApkFile

admin.site.register(User)
admin.site.register(AppDetail)
admin.site.register(Screen)
admin.site.register(UserInfo)
admin.site.register(ApkFile)
