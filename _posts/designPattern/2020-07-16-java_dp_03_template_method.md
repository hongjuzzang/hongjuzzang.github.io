---
title:  "자바 디자인 패턴 (3) - Template Method pattern "
excerpt: "인프런 강의 정리노트(강의:자바 디자인 패턴의 이해 - Gof Design Pattern)"
toc: true
toc_sticky: true
categories:
  - DesignPattern
tags:
  - Java
  - DesignPattern
---
## 인프런 강의(자바 디자인 패턴의 이해 - Gof Design Pattern)를 듣고 작성하는 수업정리 노트  
[1. 강의링크:자바 디자인 패턴의 이해 - Gof Design Pattern](https://www.inflearn.com/course/%EC%9E%90%EB%B0%94-%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4)  
[2. github repository(Design Pattern)](https://github.com/hongjuzzang/DesignPattern)  


### Sec.3 : 템플릿 메소드 패턴(Template Method Pattern)  
동작 상의 알고리즘의 프로그램 뼈대를 정의하는 행위 디자인 패턴(알고리즘의 구조를 변경하지 않고 알고리즘의 특정 단계들을 다시 정의함)  


알고리즘의 **구조를 메소드에 정의하고** 하위 클래스에서 알고리즘 구조의 변경없이 알고리즘을 **재정의**하는 패턴  
* 어떨때 사용하는지?  
  + 구현하려는 알고리즘이 **일정한 프로세스**가 있다.  
  + 구현하려는 알고리즘이 **변경 가능성**이 있다.  
* 사용 시 어떤 단계로 이루어지는지?  
   + 알고리즘을 **여러 단계**로 나눈다.  
   + 나눠진 알고리즘의 단계를 **메소드로 선언**한다.  
   + 알고리즘을 수행할 **템플릿 메소드**를 만든다.  
   + 하위 클래스에서 **나눠진 메소드들을 구현**한다.  


#### 기초 개념  
##### 사전적 의미의 template?  
   플라스틱이나 아크릴로 만든 얇은 판에 여러 가지 크기의 원 또는 타원 등과 같은 기본도형이나 각종 문자기호 등을 그리는 제도용구  
   (내가생각하는 의미를 찾기가 어려운데, 자바 클래스 설명할때 나오는 붕어빵 틀 같은 개념으로 이해함;;)  


#### 클래스 다이어그램  
![img](/assets/images/post/200716-tmpt.jpg)  
그림에는 안나와있지만 subMethod()가 1,2,3으로 분할되어 있는 경우(여러단계)  
추상클래스에서 선언된 templateMethod()내부에 subMethod1,2,3이 속하는 형식  
```java
templateMethod(){
  subMethod1();
  subMethod2();
  subMethod3();
}
```

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/f1b918739226dafdf9c24c75f8efaff9fe75ccbc)  
* 신작 게임의 접속을 구현하기  
   + requestConnection(String str) : String  
* 유저가 게임 접속 시 다음을 고려해야함 
   + 보안 과정 : 보안 관련 부분을 처리한다  
      - doSecurity(String string) : String  
   + 인증 과정 : user name과 password가 일치하는지 확인  
      - authentication(String id,String password) : boolean  
   + 권한 과정 : 접속자가 유료회원인지 무료회원인지 게임마스터인지 확인  
      - authorization(String userName) : int  
   + 접속 과정 : 접속자에게 커넥션 정보를 넘겨줌  
      - connection(String info) : String  

##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/cc98e59eb04153668ff743731253ad3e3cf6fc5a)  
* 보안 부분이 정부 정책에 의해서 강화되었음. 강화된 방식으로 코드 변경  
* 밤 10시 이후 접속이 제한되도록 해야함  

#### 참고  
[위키 백과 - 템플릿 메소드 패턴](https://ko.wikipedia.org/wiki/%ED%85%9C%ED%94%8C%EB%A6%BF_%EB%A9%94%EC%86%8C%EB%93%9C_%ED%8C%A8%ED%84%B4)  