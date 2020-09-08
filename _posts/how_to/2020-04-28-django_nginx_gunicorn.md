---
title:  "Gunicorn, nginx, Django로 AWS에 배포하기"
excerpt: "Gunicorn, nginx, Django로 AWS에 배포 A to Z"
toc: true
toc_sticky: true
categories:
  - HowTo
tags:
  - gunicorn
  - nginx
  - HowTo
  - gunicorn
last_modified_at: 2020-05-06
---
## Gunicorn, nginx, Django로 AWS에 배포 A to Z  


### 가상환경 생성  
1. 프로젝트 폴더에서 `python -m venv [가상환경이름]`으로 가상환경을 생성스  
2. `source [가상환경이름]/bin/activate`를 해서 가상환경으로 진입  
그러면 앞에 `([가상환경이름])` 이렇게 붙는다  
`pip install gunicorn`으로 gnicorn 설치  
3. 그 다음 프로젝트 makemigrations, migrate 등 runserver가 동작하는지 확인  
manage.py 있는 곳에서 `manage.py runserver` 대신
4. gunicorn 동작 확인  
```
gunicorn --bind 0.0.0.0:8000 [프로젝트 이름].wsgi:application 
```
을 입력해 서버를 실행하기  
이 다음 웹에서 서버주소:8000 로 동작 확인 !  
가상환경은 `deactivate` 해서 빠져나온다  


### Gunicorn 설정파일 작성
cd /etc/systemd/system으로 이동해서 
gunicorn.service 파일을 생성해서 입력하기  
![tempsnip](https://user-images.githubusercontent.com/37270143/80963428-df664600-8e49-11ea-809c-7aeb39b0b2e3.png)  
회색부분에는 프로젝트 경로로 맞춰주고 포트는 사용할 포트를 적어준다  
하단 backend.wsgi 이 부분은 내 프로젝트 이름이 backend라서 backend인거다 ㅠ 표시 실수  
* 20-06-24 추가내용  
  새 서버에 *진실의 방* 재배포를 하면서 설정한 내용  

```
[Unit]
Description=gunicorn daemon
After=network.target

[Service]
User=ubuntu
Group=www-data
WorkingDirectory=/home/ubuntu/backend
ExecStart=/home/ubuntu/backend/venv/bin/gunicorn \
        --workers 3 \
        --bind 0.0.0.0:8000 \
        bigdata.wsgi:application

[Install]
WantedBy=multi-user.target
```

  + workingDirectory : manage.py 있는 폴더 경로  
  + execStart : 프로젝트 폴더 내에 venv폴더 경로 + gunicorn 구동 옵션  

#### gunicorn 명령어
* 수정 후 다시불러오기
```
sudo systemctl daemon-reload
```
* 서비스 등록
```
sudo systemctl start gunicorn
```
* 서버 재시작 시 자동실행
```
sudo systemctl enable gunicorn
```
* 서비스 중지
```
sudo systemctl stop gunicorn.service
```
* gunicorn 서비스 상태 확인
```
sudo systemctl status gunicorn.service
```


### nginx 세팅
nginx 설치한 다음 `cd /etc/nginx/sites-enabled` 로 이동한다.  
`vi [프로젝트이름]`으로 파일하나를 생성한다.  
![tempsnip1](https://user-images.githubusercontent.com/37270143/80963744-8e0a8680-8e4a-11ea-875d-43c57e06ee78.png)  
 servername 부분에는 도메인이나 아이피주소,  
하단 root /home/ubuntu/에는 collect static한 경로를 적어준다  
` sudo systemctl restart nginx ` 로 nginx 재시작하기  


### 참고
[Nginx, Gunicorn 배포](https://wikidocs.net/6601)  
[Django + Vue AWS 서버 배포하기(1)(WSGI, AWS)](https://ahzick.tistory.com/2)  
[Django/nginx/Gunicorn으로 AWS EC2에 Deploy하기](https://post.naver.com/viewer/postView.nhn?volumeNo=26838977&memberNo=33264526)
[Setting up Nginx, Gunicorn, Celery, Redis, Supervisor, and Postgres with Django to run your Python application](https://medium.com/@dwernychukjosh/setting-up-nginx-gunicorn-celery-redis-supervisor-and-postgres-with-django-to-run-your-python-73c8a1c8c1ba)
