---
title:  "django에서 db.sqlite3 데이터를 mysql로 옮기기"
excerpt: "django에서 db.sqlite3 데이터를 mysql로 옮기기"
toc: true
toc_sticky: true
categories:
  - HOW TO
tags:
  - django
  - HOW TO
  - mysql
last_modified_at: 2020-05-06
---
### Django setting 변경
* 로컬 장고프로젝트에서 aws서버로 배포할때 mysql을 쓰려고 db.sqlite3에서 mysql 로 옮겨야 한다  
 우선 django에서 settings.py에서 데이터베이스 설정을 해준다.  
 ```python
# 기존에 있는 설정 (default)
# DATABASES = {
#     "default": {
#         "ENGINE": "django.db.backends.sqlite3",
#         "NAME": os.path.join(BASE_DIR, "db.sqlite3"),
#     }
# }
```
```python
DATABASES = { 
    'default': {
        'ENGINE': 'django.db.backends.mysql', # mysql 엔진 설정
        'NAME': 'db', # 데이터베이스 이름
        'USER': 'hongju', # 데이터베이스 연결시 사용할 유저 이름
        'PASSWORD': 'test', # 유저 패스워드
        'HOST': '서버 도메인주소',
        'PORT': '3306',
        'OPTIONS': {
            'init_command': "SET sql_mode='STRICT_TRANS_TABLES'",
            'charset': 'utf8mb4', # 테이블 생성 자동으로 해줄때 쓸 인코딩,, 이거안하면 밑에꺼해도 효과 엑스
            'use_unicode': True,
        },
    }
}
```


### mysql setting
음 나는 여러 시행착오끝에 했지만 미래의 나를 위해..  
나는 테이블에 사용자 리뷰테이블에서 이모티콘과 한글이 들어가기때문에 utf8mb4로 인코딩을 해줘야했다. 
우분투상에서는 기본이 latin1이었나 쨋든 한글을 허용하지않으므로 데이터베이스를 생성하기전에
미리 설정을 변경하고 하는게 시간상 정신상 좋다. 
우선 설정파일을 변경해야하는데 설정파일 경로는 아래 명령어로 찾을 수 있다.
```
$ mysqld --verbose --help | grep -A 1 'Default options' 
``` 
근데 구글링에서 my.cnf찾고 vi로 켜서 mysqld부분을 수정하라는데 원래 작성되어있던 내용도 없었고
내용을 써도 안먹었다.. 휴 그래서 1차 패닉  
그런데 다른블로그에서 해당 경로에 있는 폴더 내에서 각 세팅파일을 나눠서 저장해줘야 한다고 했다.  
나는 이모티콘을 써야하므로 utf8mb4로 맞춰주었다.  
** cd /etc/mysql/mysql.conf.d/ 로 이동 **
* client.cnf
```
[client]
default-character-set=utf8mb4
```
* mysql.cnf
```
[mysql]
default-character-set=utf8mb4
```
* mysqld.cnf
```
[mysqld]
...
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
init_connect = set collation_connection = utf8mb4_unicode_ci
```
다 저장하고  <code> /etc/init.d/mysql restart </code>로 서비스 재시작을해준다.  
만일 나처럼 서비스 재시작에 실패한다면 혹시 오타가 있는지 한번 확인하고,, 오타고치고 보면 재시작이 된다.  
재시작 후 mysql -u root -p 한 다음 mysql에 접속해서 status로 언어 설정된거를 보면 utf8mb4로 설정이 변한것을 볼 수 있다.  
근데 결국 이렇게 생성을 해줘벌였다..  
데이터베이스는 직접만들어주고 장고에서 자동생성해주는 테이블은 아마 위에 설정한 형식대로 만들어지는게 아닐까 싶다.  
``` 
create database <데이터베이스이름> character set utf8mb4 collate utf8mb4_general_ci;
```


### 데이터 넣기  
db.sqlite3를 <code> python manage.py dumpdata > db.json </code> 이렇게 따로 덤프파일 생성을 한다.  
```python
python manage.py migrate
python manage.py loaddata db.json
```
만약 이때 원인을 모를 deserializer 이런 오류가 난다면..
```python
python mange.py shell
$ from django.contrib.contenttypes.models import ContentType
$ ContentType.objects.all().delete()
$ ctrl + D (exit shell)
```
하고 다시 <code> python manage.py loaddata db.json </code>를 하기
