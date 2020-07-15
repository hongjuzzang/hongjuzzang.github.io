---
title:  "자바 디자인 패턴 (1) - strategy pattern "
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
### Sec.1 : 스트레티지 패턴(Strategy Pattern)  
#### 기초 개념  
* 인터페이스  
   키보드나 디스플레이 따위처럼 사람과 컴퓨터를 연결하는 장치  
   기능에 대한 **선언과 구현 분리**  
   기능을 사용하는 **통로**  
* 델리게이트  
   위임하는 것  
   어떤 기능을 구현할 때, 다른 객체의 기능을 호출해 구현하는 것  
* 스트레티지 패턴  
   여러 알고리즘을 하나의 **추상적인 접근점**을 만들어 접근점에서 서로 **교환가능** 하도록 하는 패턴  

#### 클래스 다이어그램  
![img](/assets/images/post/200715-stpt.png)
Strategy를 갖는 Context가 각각의 전략에서 (ConcreteStrategyA/B) 용도에 맞는 execute()를 호출할 수 있음  


#### 실습  
##### 요구사항  
* 신작게임에서 캐릭터와 무기를 구현하는 것  
* 무기는 칼, 검 -> 2가지가 있음  

##### 추가 요구사항  
* 무기에 도끼 추가하기  
