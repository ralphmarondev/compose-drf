from django.db import models
from django.utils import timezone

class Person(models.Model):
  name = models.CharField(max_length=255)
  age = models.IntegerField()
  date_created = models.DateTimeField(auto_now=True)

  def __str__(self):
    return self.name
