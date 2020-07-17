---
title:  "자바 디자인 패턴 (4) - Factory Method pattern "
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


### Sec.4 : 팩토리 메소드 패턴(Factory Method Pattern)  
팩토리 메소드 패턴은 부모(상위)클래스에 알려지지 않은 구체 클래스를 생성하는 패턴, 자식(하위)클래스가 어떤 객체를 생성할 지 결정하도록 하는 패턴.  
부모(상위) 클래스 코드에 구체 클래스 이름을 감추기 위한 방법으로도 사용  
template method의 생성 패턴 버전으로 볼 수도 있음  
상속을 사용하지만 부모(상위)클래스를 확장하지 않음  

#### 클래스 다이어그램  
![img](/assets/images/post/200717-fmpt.jpg)  
생산품을 생산하는 생산자가 있음  
구현과 구조가 분리되어있음  
내용 수정이 들어가더라도 구현부분만 수정하면 됨(유연성 증가)  

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/a39934d9f5c14d14d7067b0a7c6fb476eb0c0b77)  
* 게임아이템과 아이템 생성을 구현하기  
   + 아이템을 생성하기 전에 데이터베이스에서 아이템 정보를 요청  
   + 아이템을 생성 후 아이템 복제 등 불법을 방지하기 위해 데이터베이스에 아이템 생성 정보를 남김  
* 아이템을 생성하는 주체를 ItemCreateor로 이름 짓기  
* 아이템은 item이라는 interface로 다룰수 있도록 함  
   + item은 use함수를 기본함수로 갖고 있음  
* 현재 아이템의 종류는 체력 회복 물략, 마력 회복 물약이 있음  

#### 참고  
[위키백과 - 팩토리 메서드 패턴](https://ko.wikipedia.org/wiki/%ED%8C%A9%ED%86%A0%EB%A6%AC_%EB%A9%94%EC%84%9C%EB%93%9C_%ED%8C%A8%ED%84%B4)  