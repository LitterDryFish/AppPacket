from django.urls import path

from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('user/0/',views.user),
    path('home/0/',views.home),
    path('app/0/',views.app),
    path('game/0/',views.game),
    path('subject/0/',views.subject),
    path('recommend/0/',views.recommend),
    path('category/0/',views.category),
    path('hot/0/',views.hot),
    path('detail/0/', views.detail, name='detail'),

]