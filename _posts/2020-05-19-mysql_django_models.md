---
title:  "기존의 mysql테이블로 Django models 생성하기"
excerpt: "기존 mysql table schema -> django models !"

categories:
  - TIL
tags:
  - TIL
  - mysql
  - Django
last_modified_at: 2020-05-19
---
이번에 진행하는 프로젝트에서 하나의 mysql database에서 
django로도 접근하고 spring으로 접근하는 구조(?), 부분(?)이 있다.  
설마하는 마음에 models를 테이블 스키마를보고 한땀한땀 만들어야하나 고민했지만..  
방법은 있었다.. 역시 이맛에 장고를 하는게 아닐까  
현재 프로젝트이름은 **bigdata**, app 은 **api**가 있다.  
![image1](/assets/images/post/200519-1.JPG)


* settings.py 의 database 부분 수정하기
```python
DATABASES = { 
    'default': {
        'ENGINE': 'django.db.backends.mysql', # mysql 엔진 설정
        'NAME': 'test', # 데이터베이스 이름
        'USER': 'root', # 데이터베이스 연결시 사용할 유저 이름
        'PASSWORD': '1234!!', # 유저 패스워드
        'HOST': '127.0.0.1',
        'PORT': '3306',
        'OPTIONS': {
            'init_command': "SET sql_mode='STRICT_TRANS_TABLES'",
            'charset': 'utf8',
            'use_unicode': True,
        },
    }
}
```
기존에 django에서 database를 mysql로 쓰듯이 연결해준다.  

* models.py 자동작성하기  
```
$ python manage.py inspectdb > api/models.py
```
기존에 models.py에 있던 내용이 사라지고 새로 내용이 작성된다.  
자동 생성된 model 클래스들에 Meta 내부에 <code> managed = False </code>가 있는데, 만약 django에서 테이블을 생성, 수정, 삭제하고싶으면 해당 부분을 지우면 된다고 상단 주석에 써있다.  
  * 상단 주석 내용
  ```python
  # This is an auto-generated Django model module.
  # You'll have to do the following manually to clean this up:
  #   * Rearrange models' order
  #   * Make sure each model has one field with primary_key=True
  #   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
  #   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
  # Feel free to rename the models, but don't rename db_table values or field names.
  ``` 
* DB migration
```
$ python manage.py makemigrations
$ python manage.py migrate
```  
끝 ~~~!  


* 참고  
[기존 DB - Django로 Migration하기](https://ministar.tistory.com/11)