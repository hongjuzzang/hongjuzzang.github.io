---
title:  "[Ubuntu 18.04] mysqlclient 8.0 install"
excerpt: "Ubuntu 18.04에서 mysqlclient 5.7말고 8.0 설치하기"
toc: true
toc_sticky: true
categories:
  - HOW TO
tags:
  - ubuntu
  - mysqlclient
  - HOW TO
---
## Ubuntu 18.04에서 mysqlclient 5.7말고 8.0 설치하기  
현재 ubuntu 18.04에서 mysql 설치하려는데 자꾸 5.7만 깔린다.. 엉엉  

* Ubuntu 18.04 LTS  

### mysql 버전 확인하기(SQL)  


mysql 버전은 sql작성하는 부분에서 확인할 수 있다.
```sql
select version();
```


### mysql 5.7 삭제하기  


```
$ apt-get remove -y mysql-*
$ apt-get purge -y mysql-*
```
mysql과 관련 패키지, 설정들을 삭제한다.  


### mysql 8.0 설치하기 


* 파일 다운받기   
```
$ sudo wget https://dev.mysql.com/get/mysql-apt-config_0.8.15-1_all.deb
```


* 다운로드 받은 파일 설치하기  
```
 $ sudo dpkg -i mysql-apt-config_0.8.15-1_all.deb
 ```
*mysql server 선택된게 mysql-8.0인거 확인하기*  


* 시스템 패키지 업데이트하기  
```
$ sudo apt-get update
```


* mysql server 설치하기  
```
$ sudo apt-get install mysql-server
```
*비밀번호 설정할때 보안 강화가 추천되지만 Use Legacy Authentication Method 설정했다*


* 보안설정  
```
$ mysql_secure_installation
```
  + validate password plugin 설정하는지  
  + root 유저 비밀번호 바꾸는지  
  + remove anonymous users?(익명 유저들을 제거하는지..?)  
  + root 유저의 원격 접속을 막는지  
  + 테스트 데이터베이스 제거 관련  
  + 권한 테이블 다시 불러오는지  



* 데이터베이스 로그인하기  
```
$ mysql -u root -p
```
비밀번호는 아까 설정했던 내용을 입력해주기  



### 새 유저 만들기 및 권한 주기  


mysql 접속을 하고나면 bash상에서 <code>mysql></code> 이런 표식이 뜬다.  
* user 생성  
```sql
mysql> CREATE USER 'newuser'@'%' IDENTIFIED BY 'userpwd';
```
newuser - 새로 만들 유저 이름  
'%' - 접속을 허용할 주소, %는 전체 다  
userpwd - 새로만든 유저 접속 비밀번호  



* 권한 부여  
```sql
mysql> GRANT ALL PRIVILEGES ON * . * TO 'newuser'@'%';
```
마찬가지로 값을 넣어주고 어떤 권한을 줄건지 입력하면 된다.
마지막으로 적용하고 새로 불러오기
```sql
mysql> flush privileges;
```



#### [보충] mysql 권한 관련  


mysql 권한 종류  

| 권한 | 내용 |
|:-------|:------|
| create,alter,drop    | 테이블 생성, 변경, 삭제   |
| select, insert, update, delete   | 테이블 레코드 조회,입력,수정,삭제   |
| reload   | 권한 부여된 내용을 리로드    |
| shutdown   | 서버 종료 작업 실행    |
| all   | 모든 권한 허용   |
| usage   | 권한 없이 계정만 생성   |
|----

대상  

| 권한 | 내용 |
|:-------|:-------|
| <code>*.*</code> | 서버의 모든 데이터베이스, 테이블에 대한 접근 허용 |
| [데이터베이스명].*  | 특정 데이터베이스의 모든 내용에 대한 접근 허용  |
| [데이터베이스명].[테이블명] | 특정 데이터베이스의 특정테이블에 대한 접근 허용 |


## 참고
[How do I uninstall and reinstall MySQL?](https://www.digitalocean.com/community/questions/how-do-i-uninstall-and-reinstall-mysql)  
[How to Install MySQL 8.0 on Ubuntu 18.04](https://www.fosstechnix.com/install-mysql-8-on-ubuntu/)  
[[MYSQL] 계정 생성 및 권한 관리(GRANT)](https://extbrain.tistory.com/44)  