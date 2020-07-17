---
title:  "자바 디자인 패턴 (5) - Singleton pattern "
excerpt: "인프런 강의 정리노트(강의:자바 디자인 패턴의 이해 - Gof Design Pattern)"
toc: true
toc_sticky: true
categories:
  - DesignPattern
tags:
  - Blog
  - Note
  - DesignPattern
---
## 인프런 강의(자바 디자인 패턴의 이해 - Gof Design Pattern)를 듣고 작성하는 수업정리 노트  
[1. 강의링크:자바 디자인 패턴의 이해 - Gof Design Pattern](https://www.inflearn.com/course/%EC%9E%90%EB%B0%94-%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4)  
[2. github repository(Design Pattern)](https://github.com/hongjuzzang/DesignPattern)  


### Sec.5 : 싱글톤 패턴(Singleton Pattern)  
생성자가 여러차례 호출되더라도 실제로 생성되는 객체는 하나이고, 최초 생성 이후 호출된 생성자는 최초 생성자가 생성한 객체를 리턴한다.  
주로 공통된 객체를 여러개 생성해서 사용하는 DBCP(DataBase connection Pool)에서 사용한다.  

#### 기초 개념  
* 객체 : 속성과 기능을 갖춘 것  
* 클래스 : 속성과 기능을 정의한 것  
* 인스턴스 : 속성과 기능을 가진 것 중 실제하는 것  


##### 객체, 인스턴스의 차이  
클래스의 타입으로 선언되었을 때 **객체**라고 부르고, 그 **객체**가 메모리에 할당되어 실제 사용될 때 **인스턴스**라고 부른다.  
**객체**는 현실세계에 가깝고, **인스턴스**는 소프트웨어 세계에 가깝다.


##### 사전적 의미의 singleton?  
외동이, 한개의 것, 한장(패)  


#### 클래스 다이어그램  
![img](/assets/images/post/200717-stpt.jpg)  


#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/5ecd896115089f815be1164699391267c63fb929)  
* 개발중인 시스템에서 스피커에 접근할 수 있는 클래스를 만들기  
(스피커 100개일때 볼륨을 올린다면 100개에 올려야하는지?;; 1번으로 100개의 볼륨을 올리기)  


##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/e03c0614d554a8a75d5c1ffc79ffa879a0ce2086)  
* 인스턴스를 호출할 때 로그를 찍는 소스 추가

#### 참고  
[위키 백과 - 싱글턴 패턴](https://ko.wikipedia.org/wiki/%EC%8B%B1%EA%B8%80%ED%84%B4_%ED%8C%A8%ED%84%B4)  
[[Java] 클래스, 객체, 인스턴스의 차이](https://gmlwjd9405.github.io/2018/09/17/class-object-instance.html)  