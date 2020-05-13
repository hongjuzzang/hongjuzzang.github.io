---
title:  "[Ubuntu 18.04] mysqlclient install"
excerpt: "Ubuntu 18.04에서 mysqlclient 설치 오류 해결하기"
categories:
  - TIL
tags:
  - Ubuntu
  - TIL
  - mysqlclient
last_modified_at: 2020-05-06
---
## Ubuntu 18.04에서 mysqlclient 설치 오류 해결하기  
gunicorn 서비스 시작 시 mysqlclient를 설치하지않았다고해서  
pip install mysqlclient를 했지만 이런 오류가 났다.  
```
ERROR: Command errored out with exit status 1: python setup.py egg_info Check the logs for full command output.
```
다음 명령어로 설치
```
$ sudo apt-get install libssl-dev
$ sudo apt-get install mysql-server
$ sudo apt-get install mysql-client
$ sudo apt-get install libmysqlclient-dev
$ pip install mysqlclient
```
## 참고
[ubuntu 18.04 LTS, Django2 mysqlclient 설치 오류](https://blog.boxcorea.com/wp/archives/2702)