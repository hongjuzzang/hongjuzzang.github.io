---
title:  "자바 디자인 패턴 (7) - Builder pattern "
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


### Sec.7 : 빌더 패턴(Builder Pattern)  
복잡한 단계를 거쳐야 생성되는 객체의 구현을 서브클래스에게 넘겨주는 패턴  
  + 복합객체의 생성과정과 표현방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴  
많은 인자를 가진 객체 생성을 다른 객체의 도움으로 생성하는 패턴  


#### 기초 개념  

##### 사전적 의미의 Builder?  
* 건축업자, 시공자, 건조자
* (새 국가 등의)건설자


#### 클래스 다이어그램  
![img](/assets/images/post/200722-bdpt.jpg)  
bulider클래스에서 필요한 기능을 concreateBuilder에서 구현  

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/e8b8324187d15cf80e7f02d20fadfc3d0d4d6c9f)  
* ComputerFactory 객체 만들기  
   + setBluePrint()를 통해 BluePrint를 지정  
   + 이때 BluePrint는 LgGram, Mac이 있을 수 있음  
   + BluePrint는 cpu,ram, storage를 지정할 수 있음  
   + 설계도를 통해 컴퓨터를 만들 수 있음  

##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/340841360ba747d66b0d4c3e5a23412de5a23a16)  
* 컴퓨터를 computerBuilder를 사용해서 생성 시 각 인자에 대해 가독성을 높여보기  

#### 참고  
[위키 백과 - 빌더 패턴](https://ko.wikipedia.org/wiki/%EB%B9%8C%EB%8D%94_%ED%8C%A8%ED%84%B4)  