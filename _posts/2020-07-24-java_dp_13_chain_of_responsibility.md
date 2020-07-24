---
title:  "자바 디자인 패턴 (13) - Chain of Responsibility pattern "
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


### Sec.13 : 책임사슬 패턴(Chain of Responsibility Pattern)  
객체 지향 디자인에서 chain-of-responsibility pattern은 명령 객체와 일련의 처리 객체를 포함하는 디자인 패턴.  
각각의 처리 객체는 명령 객체를 처리할 수 있는 연산의 집합이고, 체인 안의 처리 객체가 다룰 수 없는 명령은 다음 처리 객체로 넘겨진다.  
이 작동방식은 새로운 처리 객체부터 체인의 끝까지 다시 반복된다.  

어떤 처리 방식에서는 다양한 방향으로 명령을 보내 책임을 트리 형태로 바꾸는 관제사 역할을 하기도 한다.  
몇몇 경우에서는, 처리 객체가 상위의 처리 객체와 명령을 호출하여 작은 파트의 문제를 해결하기 위해 재귀적으로 실행된다.  
이 경우 재귀는 명령이 처리되거나 모든 트리가 탐색될때까지 진행되게 된다.  

#### 클래스 다이어그램  
![img](/assets/images/post/200724-crpt.jpg)  
handler - > 다음번에 처리할 객체를 가지고 있음  
현재 가지고 있는 process가 실패할 경우, 다음 객체로 넘겨줌   

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/620aaa3c77ffdf64ac3c9e916b7294a6166ce2fa)  
* 사칙연산하는 프로그램 만들기  
  + abstract class Calculator를 상속받는 PlusCalculator, SubCalculator  
  + plus객체의 다음 연산객체(setNextCalculator)는 minus로 지정  
  + 만일, 지정된 다음연산객체가 요청 수행(request)를 수행할 수 있으면 다음 연산객체에서 수행, 아니면 직접 수행  


##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/ad7d935599149f5e060217edb7a42963020f4eee)  
* 방어구 착용하고 공격받기  
   + 공격 클래스 Attack, 인자 amount만큼 공격력이 있음  
   + 방어구 클래스 Armor, 인자 def만큼 공격력을 깎을 수 있음  
   + 방어 수행여부에 관계없이 다음 방어구가 등록되어있으면 공격력을 깎음  

#### 참고  
[위키 백과 - 책임연쇄 패턴](https://ko.wikipedia.org/wiki/%EC%B1%85%EC%9E%84_%EC%97%B0%EC%87%84_%ED%8C%A8%ED%84%B4)  