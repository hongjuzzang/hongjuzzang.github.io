---
title:  "Spring 알아보기"
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
후후 스프링을 배우고 프로젝트를 해봤지만 원리, 개념 부분에서 살짝 가물가물하다  
공부할겸 작성하는 스프링 포스트 ~~ 

### 🌱 Spring이 무엇인가  
**스프링 프레임워크(Spring Framework)**는 자바 플랫폼을 위한 오픈 소스 애플리케이션 프레임워크로서 간단히 스프링(Spring)이라고 한다  
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

### 🌱 Spring Filter와 Interceptor의 차이점  
#### 1. 범위와 사양  
 - Filter  
  > Spring Context 외부에 존재하여 스프링과 무관한 자원에 대해 동작한다(요청 내용 변경, 인코딩 변환 등)  
  > Filter는 서블릿 사양(Servlet Specifications)에 의해 정의된다  
  > 웹 프로그램에서만 사용할 수 있다  
- Interceptor  
  > Spring Container내부에 있다 -> Spring의 context에 등록을 한다    
  > 웹 프로그램, 어플리케이션 프로그램, 스윙(swing) 프로그램에서 사용할 수 있다  

#### 2. 트리거 시간  
순서 : 필터 > 서블릿 > 인터셉터 > 컨트롤러  
 - Filter  
  > 필터는 요청이 서블릿에 전달되기 전에 처리한다  
  > 요청이 수행되고 난 후 Front-end로 돌아가기전에도 필터를 거친다  
- Interceptor  
  > 메서드가 컨트롤러에 도달하기 전에 적용된다  

#### 3. 접근과 요청  
 - Filter  
  > 거의 모든 요청에서 작동할 수 있다  
  > Context 및 스택에 접근할 수 없다  
- Interceptor  
  > 작업 요청(Action request)에서만 작동한다  
  > context의 객체(Bean)와 스택에 접근할 수 있다   

<br />

### 🌱 Annotation  
소스코드에 `@[어노테이션]`의 형태로 표현하며 클래스, 필드, 메소드의 선언부에 적용할 수 있는 특정기능이 부여된 표현법을 말한다  


애플리케이션 규모가 커질수록, xml 환경설정이 매우 복잡해지는데,  
이러한 어려움을 개선시키기 위해 자바 파일에 어노테이션을 적용해서 개발자가 설정 파일 작업을 할 때 발생시키는 오류를 최소화해주는 역할을 한다  


어노테이션 사용으로 소스 코드에 메타데이터를 보관할 수 있고, 컴파일 타임의 체크뿐 아니라 어노테이션 API를 사용해 코드 가독성도 높여준다  

* Annotation 몇 개  
```
@RequestMapping : 특정 메소드에서 요청되는 URL과 매칭시키는 어노테이션
@Autowired : 자동으로 의존성 주입하기 위한 어노테이션, type에 따라 알아서 Bean 을 주입한다  
@Controller : dispatcher-servlet.xml에서 bean 태그로 정의하는 것과 같음  
@RestController : @ResponseBody를 모든 메소드에 적용한다  
@Service : 비즈니스 로직 처리하는 서비스 클래스에 등록한다
@Repository : DAO에 등록한다
```

<br />

### 🌱 기타 용어  
* POJO  
 > POJO(Plain Old Java Object)  
 > 본래의 Java의 장점을 살리는 오래된 방식의 순수한 자바 객체  
 > 즉, 객체지향적인 원리에 충실하면서 환경과 기술에 종속되지않고 필요에 따라 재활용 될수 있게 설계된 오브젝트  


* AOP(Aspect-oriented Programming)    
 > 관점 지향 프로그래밍 : 공통의 관심사항을 적용해서 발생하는 의존 관계의 복잡성과 코드 중복을 해소해준다  
 > 각 클래스에서 공통 관심 사항을 구현한 모듈에 대한 의존관계를 갖기 보단, Aspect를 이용해 핵심 로직을 구현한 각 클래스에 공통 기능을 적용한다  
 > 간단한 설정만으로도 공통 기능을 여러 클래스에 적용할 수 있는 장점이 있다  
 > 핵심 로직 코드를 수정하지 않고도 웹 애플리케이션의 보안, 로깅, 트랜잭션과 같은 공통 관심 사항을 AOP를 이용해 간단하게 적용할 수 있다  

* DI(Dependency Injection)  
> 의존성 주입 : 설정 파일을 통해 객체간의 의존관계를 설정하는 역할을 한다  
> 컨테이너에 의해 객체를 사용할 수 있도록 생성 후, 생성자나 setter 같은 메서드를 통해 사용하는 것  

* IoC(Inversion of Control)  
 > 제어 반전 : 프로그램의 흐름 구조가 변화함  
 > 인스턴스의 생성부터 소멸까지 개발자가 아닌 컨테이너가 대신 관리해 주는 것  
 > 인스턴스 생성의 제어를 서블릿과 같은 bean을 관리해주는 컨테이너가 관리한다  
 > 설계가 깔끔해지고 유연성과 확장성을 증가시킨다  

* DS(Dispatcher Servlet)  
 > 서블릿 컨테이너에서 HTTP 프로토콜을 통해 들어오는 모든 요청을 제일 앞에서 처리해주는 프론트 컨트롤러  
 > 공통 처리작업을 DS가 처리하고 적절한 세부 컨트롤러(Handler)로 작업을 위임해준다  
 > 기존에 몯느 서블릿에 대해 url매핑을 web.xml에 모두 등록했지만, 모든 요청을 핸들링하게되면서 web.xml의 역할이 축소하고 편리해졌다  
 > MVC패턴으로 처리가 가능해진다  

---
### 참고  
[How Java Spring MVC Works: Spring MVC Request Flow Explained Step by Step](https://tutorialspedia.com/how-java-spring-mvc-works-spring-mvc-request-flow-explained-step-by-step/)  
[MVC](https://developer.mozilla.org/ko/docs/Glossary/MVC)  
[POJO - (Plain Old Java Object)란 뭘까?](https://siyoon210.tistory.com/120)  
[The difference between filters and interceptors in java](https://programmer.help/blogs/the-difference-between-filters-and-interceptors-in-java.html)  
[(Spring)Filter와 Interceptor의 차이](https://supawer0728.github.io/2018/04/04/spring-filter-interceptor/)  
[[Spring] Spring Annotation의 종류와 그 역할](https://gmlwjd9405.github.io/2018/12/02/spring-annotation-types.html)  
[웹 = 스프링](https://gyoogle.dev/blog/interview/%EC%9B%B9.html)  
[[Spring]Dispatcher-Servlet이란?](https://mangkyu.tistory.com/18)