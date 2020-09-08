---
title:  "자바 디자인 패턴 (9) - Bridge pattern "
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


### Sec.9 : 브릿지 패턴(Bridge Pattern)  
구현부에서 추상층을 분리하여 각자 독립적으로 변형할 수 있게 하는 패턴(기능 계층과 구현계층의 분리)  
어댑터패턴과 유사함  

#### 클래스 다이어그램  
![img](/assets/images/post/200723-brpt.jpg)  
추상부분(기능부분) - abstraction  
구현부분 - implementor  
기능의 구현을 가지고있는 인터페이스를 멤버변수로 가지며, 이를 통해 기능 동작하게함  

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/ee83c63006fb7e1691f49c849a6c3b3e305230da)  
* 모스코드 출력기  
  + g,a,r,m에 대해 모스코드를 출력하는 클래스 생성  
  + 모스코드는 dash, dot, space로 이루어져있음 -> MorseCode class 생성  
  + 생성한 3가지 함수를 이용하여 각 알파벳 이름의 함수에 해당하는 모스코드를 출력하는 메소드를 PrintMorseCode class 생성  

##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/c9fb336b7e9cf22d4aecddad59027a2d71182415)  
* morsecode의 dash, dot, space함수에서 출력문 말고 다른 함수를 넣는다면 ??  
  + 빛을 사용한 (*, *-) FlashMorseCodeFunction  
  + 소리를 사용한 (따, 따-)  SoundMorseCodeFunction  

#### 참고  
[위키 백과 - 브리지 패턴](https://ko.wikipedia.org/wiki/%EB%B8%8C%EB%A6%AC%EC%A7%80_%ED%8C%A8%ED%84%B4)  