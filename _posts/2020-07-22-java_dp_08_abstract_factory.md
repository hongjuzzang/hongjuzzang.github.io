---
title:  "자바 디자인 패턴 (8) - Abstract Factory pattern "
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


### Sec.8 : 추상 팩토리 패턴(Abstract Factory Pattern)  
다양한 구성 요소 별로 '객체의 집합'을 생성할 때 유용하게 사용함  
이 패턴을 사용해서 상황에 알맞은 객체를 생성할 수 있음  
생성하는 부분 factory를 가상화해서 구체적인 부분을 가려줌  

#### 클래스 다이어그램  
![img](/assets/images/post/200722-afpt.jpg)  
bulider클래스에서 필요한 기능을 concreateBuilder에서 구현  

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/db354da15de2f6fcaa1e755e924c38ce63c45559)  
* 자전거 부품을 생성하는 공장  
  + 기본적인 body, wheel과 두 부품을 만드는 factory interface  
  + 각 자전거는 위의 interface를 implements해서 새로운 자전거를 생성  

##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/15d267b12bb6b344dc6944cf02ebbf53e49316bc)  
* button, textarea를 guiFactory를 이용하여 win, linux로 나누기  
  + Button, TextArea, GuiFactory 인터페이스 구현  
  + Button에는 click(), TextArea에는 getText()를 구현  

* 어떤 os든 같은 동작을 하게 할 수 없을까?  
   + 알아서 os에 맞는 객체를 생성하기  

#### 참고  
[위키 백과 - 추상 팩토리 패턴](https://ko.wikipedia.org/wiki/%EC%B6%94%EC%83%81_%ED%8C%A9%ED%86%A0%EB%A6%AC_%ED%8C%A8%ED%84%B4)  