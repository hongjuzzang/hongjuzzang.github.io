---
title:  "자바 디자인 패턴 (6) - Prototype pattern "
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


### Sec.6 : 프로토타입 패턴(Prototype Pattern)  
생성할 객체들의 타입이 프로토타입인 인스턴스로부터 결정되도록 하며, 인스턴스는 새 객체를 만들기 위해 자신을 복제(clone)하게 된다.  
* *추상 팩토리패턴*과는 반대로, 클라이언트 응용 프로그램 코드내에서 객체 창조자(creator)를 서브클래스(subclass) 하는것을 피할 수 있게 해준다.  
* 새로운 객체는 일반적인 방법으로 객체를 생성하는 고유의 비용이 큰 경우 이 비용을 감내하지 않을 수 있게 해준다.  


즉, **생산 비용이 높은 인스턴스**를 복사를 통해 쉽게 생성할 수 있도록 하는 패턴  
(?? 이게 뭔소리인가)
#### 기초 개념  

##### 사전적 의미의 prototype?  
* 원형; 본, 표준, 모범  
* (어떤 것의) 옛날의 유사물  


##### 인스턴스 생산 비용이 높은 경우  
* *종류가 너무많아서* 클래스로 정리되지 않는 경우  
* 클래스로부터 *인스턴스 생성이 어려운 경우*  

##### 깊은 복사 vs 얕은 복사  
![deepshallow](/assets/images/post/200717-dscp.png)  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/d53872671b658d74c640bea3f569d029f056d081)  
* 얕은 복사는 다른 객체, 같은 주소  
p객체를 얕은 복사를 통해 q객체를 생성한다면 p의 주소(100이 저장된 주소)를 q가 가지고있음  
즉 둘다 같은 주소를 가지고 있음  
p나 q 둘중 하나가 100 대신 200으로 값을 바꾼다면, 둘다 같은 주소를 사용하므로  
p = 200 을 하는 경우라도 q도 200이 됨  


* 깊은 복사는 다른 객체, 다른 주소  
p객체를 깊은 복사해서 q객체를 생성한다면 둘은 다른 주소를 가짐  
둘이 다른 주소를 가지므로 변경이 일어나도 독립적임  
p = 200 을 하면 q는 다른주소므로 영향받지 않고 100을 가지고 있음  


#### 클래스 다이어그램  
![img](/assets/images/post/200717-ptpt.jpg)  
프로토 타입 인스턴스를 통해 실체화 하는 것  

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/cfd6645792d902e6b6336b4737b19ded6ceb6e0a)  
* 일러스트레이터와 같은 그림그리기 툴을 개발 중임  
   어떤 모양을 그릴 수 있도록 하고, 복사,붙여넣기 기능을 구현하기  

##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/99097e23cf534d9537dad52495fec66b20c27c8d)  
* 복사 후 붙여넣기 시 두 도형이 겹침  
   안겹치도록 살짝 옆으로 이동

#### 참고  
[위키 백과 - 프로토타입 패턴](https://ko.wikipedia.org/wiki/%ED%94%84%EB%A1%9C%ED%86%A0%ED%83%80%EC%9E%85_%ED%8C%A8%ED%84%B4)  