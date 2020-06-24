---
title:  "[Ubuntu 18.04] mysqlclient install"
excerpt: "Ubuntu 18.04에서 mysqlclient 설치 오류 해결하기"
categories:
  - TIL
tags:
  - ubuntu
  - mysqlclient
  - TIL
last_modified_at: 2020-06-24
---
## Ubuntu 18.04에서 mysqlclient 설치 오류 해결하기  
* Ubuntu 18.04  
* python 3.6.9  


### ERROR: Command errored out with exit status 1
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


### error: command 'x86_64-linux-gnu-gcc' failed with exit status 1  
새로 생성한 서버에서 mysqlclient를 위의 방법으로 설치를 한 후 진행했는데 새로운 에러를 만났다.  
```
$ sudo apt-get install python3.7-dev # 맞는 버전 입력
```

## 참고
[ubuntu 18.04 LTS, Django2 mysqlclient 설치 오류](https://blog.boxcorea.com/wp/archives/2702)  
[[ Python Skill ] Python 3.x 에서 MySQLdb 설치하기](https://potensj.tistory.com/107)