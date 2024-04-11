from django.db import models
from django.contrib.auth.models import AbstractBaseUser, BaseUserManager

class UsuarioManager(BaseUserManager):
    def create_user(self, email, nome, password=None):
        if not email:
            raise ValueError('O campo de email é obrigatório.')
        
        email = self.normalize_email(email)
        user = self.model(email=email, nome=nome)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, nome, password):
        user = self.create_user(email=email, nome=nome, password=password)
        user.is_admin = True
        user.is_staff = True
        user.save(using=self._db)
        return user

class Usuario(AbstractBaseUser):
    email = models.EmailField(verbose_name='email', max_length=255, unique=True)
    nome = models.CharField(verbose_name='nome', max_length=255)
    is_active = models.BooleanField(default=True)
    is_admin = models.BooleanField(default=False)
    is_staff = models.BooleanField(default=False)

    objects = UsuarioManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['nome']

    def __str__(self):
        return self.email

    def has_perm(self, perm, obj=None):
        return True

    def has_module_perms(self, app_label):
        return True

