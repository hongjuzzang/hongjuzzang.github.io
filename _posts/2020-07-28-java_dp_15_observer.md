---
title:  "자바 디자인 패턴 (15) - Observer pattern "
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


### Sec.15 : 옵저버 패턴(Observer Pattern)  
옵서버 패턴(observer pattern)은 객체의 상태 변화를 관찰하는 관찰자들, 즉 옵저버들의 목록을 객체에 등록하여 상태 변화가 있을 때마다 메서드 등을 통해 객체가 직접 목록의 각 옵저버에게 통지하도록 하는 디자인 패턴.  
주로 분산 이벤트 핸들링 시스템을 구현하는 데 사용된다. 발행/구독 모델로 알려져 있기도 하다.
객체 외부에서 이벤트를 처리함  

#### 기초 개념  

##### 사전적 의미의 Observer?  
감시자, 관찰자라는 뜻  

##### Class Observable  
model-view 패러다임에서 관측가능한 객체나 데이터를 표현하는 클래스  
관찰 가능한 객체는 1개 혹은 그이상의 관찰자를 가질 수 있음  

##### Interface Observer  
observable 객체에게 변경을 알리고 싶을 때 implements해서 사용   

#### 클래스 다이어그램  
![img](/assets/images/post/200728-obpt.jpg)  
subject객체가 observer인터페이스를 가지고 있고, observer에서 target을 업데이트함  
#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/62707c24301cbc8e3362423bb6dbfbd24a4d8fd0)  
* Button Click event를 생성하기  
  + Button class  
     멤버변수로 OnClickListener를 private으로 가짐  
     내부에 OnClickListener인터페이스 선언 -> onClick(Button btn) 메소드 정의   
  + main  
    버튼 객체 생성  
    버튼 객체의 onClickListener를 지정 후 onclick 오버라이드  
    생성한 버튼의 onClick 호출   


#### 실습2  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/2aa39ca02b7cb493ac56f5c3989c46a0024d82ee)  
* java의 Observer, Observable 이용하기  
  + Button 클래스를 생성하고 Observable을 상속하기  
  + onclick메소드 내의 Observable의 메소드를 이용하여 onClick호출 시 알려주기  


#### 상속없이 observer를 가지는 형태  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/82fc91d8d00924d5bf33a1b5751fbad339e17888)  
제네릭, 델리게이트, 내부 observer를 가지는 형태  
(너무 어려움.. 이해 엑스..)


#### 참고  
[위키 백과 - 옵서버 패턴](https://ko.wikipedia.org/wiki/%EC%98%B5%EC%84%9C%EB%B2%84_%ED%8C%A8%ED%84%B4)  
[Class Observable - javadoc8](https://docs.oracle.com/javase/8/docs/api/)  
[Interface Observer - javadoc8](https://docs.oracle.com/javase/8/docs/api/)  