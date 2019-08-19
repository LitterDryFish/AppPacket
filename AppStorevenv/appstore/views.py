from django.core import serializers
from django.core.serializers import json
from django.core.serializers.base import Serializer
from django.http import HttpResponse, Http404
from django.shortcuts import render

# Create your views here.

from django.http import  HttpResponse
from django.template import loader
from django.views.generic.base import View

from appstore.models import User, AppDetail


def index(request):
    latest_User_list = User.objects.order_by('register_time')[:5]
    template = loader.get_template('appstore/index.html')
    context = {
        'latest_user_list': latest_User_list,
    }
    return HttpResponse(template.render(context, request))


def detail(request):
    try:
        namebyte = request.body
        appName=str(namebyte, encoding='utf-8')
        app = serializers.serialize("json", AppDetail.objects.filter(name=appName))
        return HttpResponse(app)
    except AppDetail.DoesNotExist:
        raise Http404("User does not exist")
    return HttpResponse("没有")
def user(request):
    data = serializers.serialize("json", User.objects.all())
    return HttpResponse(data)

def home(request):

    data = serializers.serialize("json", AppDetail.objects.all())
    return HttpResponse(data)
def app(request):

    try:
        softbyte = request.body
        softWare=str(softbyte, encoding='utf-8')
        print(softWare)
        app = serializers.serialize("json", AppDetail.objects.filter(app_type=softWare))
        return HttpResponse(app)
    except AppDetail.DoesNotExist:
        raise Http404("User does not exist")
    return HttpResponse("没有")
def game(request):
    try:
        gamebyte = request.body
        game=str(gamebyte, encoding='utf-8')
        print(game)
        app = serializers.serialize("json", AppDetail.objects.filter(app_type=game))
        return HttpResponse(app)
    except AppDetail.DoesNotExist:
        raise Http404("User does not exist")
    return HttpResponse("没有")
def subject(request):
    data = serializers.serialize("json", AppDetail.objects.all())
    return HttpResponse(data)
def recommend(request):
    data = serializers.serialize("json", AppDetail.objects.all())
    return HttpResponse(data)
def category(request):
    data = serializers.serialize("json", AppDetail.objects.all())
    return HttpResponse(data)
def hot(request):
    data = serializers.serialize("json", AppDetail.objects.all())
    return HttpResponse(data)




def test(request):
    users = User.objects
    print(request.POST.get("email"))
    return render(request,"klkl.html",{"users":users})