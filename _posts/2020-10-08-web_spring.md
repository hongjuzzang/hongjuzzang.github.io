---
title:  "Spring 알아보기 (작성중)"
excerpt: "Spring framework 알아보기"
toc: true
toc_sticky: true
categories:
  - Web
tags:
  - Spring
  - SpringBoot
---
## Spring framework 알아보기  
후후 스프링을 배우고 프로젝트를 해봤지만 살짝 가물가물한 감이 없지않다  

### 🌱 Spring이 무엇인가  
**스프링 프레임워크(Spring Framework)**는 자바 플랫폼을 위한 오픈 소스 애플리케이션 프레임워크로서 간단히 스프링(Spring)이라고도 한다  
동적인 웹 사이트를 개발하기 위한 여러 가지 서비스를 제공하고 있다  

<br />

### 🌱 Spring 특징  
스프링 프레임워크가 가지는 특징은 다음과 같다  


* > **경량 컨테이너로서 자바 객체를 직접 관리한다**  
  <ins>Spring Container</ins>라는 런타임 엔진을 제공한다  
  이 Spring Container는 각각의 객체 생성, 소멸과 같은 라이프 사이클을 관리하며 스프링으로부터 필요한 객체를 얻어올 수 있다.  

* > **스프링은 POJO(Plain Old Java Object) 방식의 프레임워크이다**  
  일반적인 J2EE 프레임워크에 비해 구현을 위해 특정한 인터페이스를 구현하거나 상속을 받을 필요가 없어 기존에 존재하는 라이브러리 등을 지원하기에 용이하고 객체가 가볍다  

* > **스프링은 제어 반전(IoC : Inversion of Control)을 지원한다**  
  컨트롤의 제어권이 사용자가 아니라 프레임워크에 있어서 필요에 따라 스프링에서 사용자의 코드를 호출한다  
  설계가 깔끔해지고 유연성과 확장성을 증가시킨다  

* > **스프링은 의존성 주입(DI : Dependency Injection)을 지원한다**  
  각각의 계층이나 서비스들 간에 의존성이 존재할 경우 프레임워크가 서로 연결시켜준다  
  (컨테이너에 의해 객체를 사용할 수 있도록 생성 후, 생성자나 setter같은 메소드를 통해 사용하는 것)  

* > **스프링은 관점 지향 프로그래밍(AOP : Aspect-Oriented Programming)을 지원한다**  
  기존의 비지니스 로직 외 작성해야 하는 코드를 분리한다  
  즉, 트랜잭션이나 로깅, 보안과 같이 여러 모듈에서 공통적으로 사용하는 기능의 경우 해당 기능을 분리하여 관리할 수 있다  


* > **스프링은 영속성과 관련된 다양한 서비스를 지원한다**  
  iBATIS나 Hibernate 등 이미 완성도가 높은 데이터베이스 처리 라이브러리와 연결할 수 있는 인터페이스를 제공한다  


* > **스프링은 확장성이 높다**  
  스프링 프레임워크에 통합하기 위해 간단하게 기존 라이브러리를 감싸는 정도로 스프링에서 사용이 가능하기 때문에 수많은 라이브러리가 이미 스프링에서 지원되고 있고 스프링에서 사용되는 라이브러리를 별도로 분리하기도 용이하다  


<br />

### 🌱 Spring MVC 구조 흐름  
![mvc flow](https://tutorialspedia.com/wp-content/uploads/2019/09/Spring-MVC-Flow-Diagram.png)  


1) DS(Dispatcher Servlet)이 요청을 받는다  

2) 요청을 처리할 수 있는 handler(Controller)의 이름을 Handler Mapping한테 물어본다  

3) Handler Mapping은 요청URL을 보고 Handler 판단 및 Handler 실행 전처리, 후처리 인터셉터 목록을 결정한다  

4) DS가 제어권을 Handler에게 전달한다  

5) Handler는 Service를 호출하고, 렌더링 해야할 View의 이름을 DS에게 전송한다  

6) DS는 View Resolver에게 응답에 필요한 view를 생성하라고 시킨다  

8~9) 해당 View에게 model, Controller 줘서 응답을 생성한 다음, DS에게 다시 보낸다  

10) DS는 View로부터 받은 것을 클라이언트에게 응답한다  

<br />

#### MVC 패턴이란?  
![MVC](https://mdn.mozillademos.org/files/16042/model-view-controller-light-blue.png)  


애플리케이션의 구성요소를 **Model**, **View**, **Controller**로 분리한 것이다  

* Model(모델)  
 > 데이터와 비지니스 로직을 관리한다  
 > Model은 데이터를 전달하는 객체, 또는 Java의 POJO를 나타낸다  
 > 데이터가 변경되면 컨트롤러를 갱신하는 로직을 가질 수 있다  

* View(뷰)  
 > 레이아웃과 화면을 처리한다  
 > Model에 포함된 데이터의 시각화를 나타낸다  
 >  = 뷰는 모델로부터 정보를 얻어 사용자가 보게되는 결과물을 생성한다  

* Controller(컨트롤러)  
 > 명령을 Model, View에게 라우팅한다  
 > Model의 데이터 흐름을 제어하고, 데이터가 변경될 때 마다 View를 업데이트 한다  
 > Controller는 Model, View 모두에서 작동한다  


<br />

### 🌱 Spring Filter, Interceptor  
필터

<br />

### 🌱 Annotation  


<br />

### 🌱 기타 용어  
* POJO  
 > POJO(Plain Old Java Object)  
 > 본래의 Java의 장점을 살리는 오래된 방식의 순수한 자바 객체  
 > 즉, 객체지향적인 원리에 충실하면서 환경과 기술에 종속되지않고 필요에 따라 재활용 될수 있게 설계된 오브젝트  


---
### 참고  
[How Java Spring MVC Works: Spring MVC Request Flow Explained Step by Step](https://tutorialspedia.com/how-java-spring-mvc-works-spring-mvc-request-flow-explained-step-by-step/)  
[MVC](https://developer.mozilla.org/ko/docs/Glossary/MVC)  
[POJO - (Plain Old Java Object)란 뭘까?](https://siyoon210.tistory.com/120)  
[The difference between filters and interceptors in java](https://programmer.help/blogs/the-difference-between-filters-and-interceptors-in-java.html)  