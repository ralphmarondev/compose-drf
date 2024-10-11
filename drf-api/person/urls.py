from django.urls import path, include
from .views import PersonViewSet
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'persons', PersonViewSet)
urlpatterns = [
  path('', include(router.urls))
]
