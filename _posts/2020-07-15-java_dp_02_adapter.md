---
title:  "자바 디자인 패턴 (2) - Adapter pattern "
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


### Sec.2 : 어댑터 패턴(Adapter Pattern)  
어댑터 패턴은 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환하는 패턴으로, 호환성이 없는 인터페이스 때문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해준다.  


#### 기초 개념  
##### 사전적 의미의 Adapter?  
   기계/기구등을 다목적으로 사용하기 위한 부가기구  

#### 클래스 다이어그램  
![img](/assets/images/post/200715-adpt.png)  
adaptee라는 알고리즘을 adapter를 통해 원하는 기능으로 변경하는 것  


#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/54e306004a6371ba2e7b95625b4021ca8915ca04)  
* 두 수에 대한 다음 연산을 수행하는 객체 만들기  
  + 수의 두 배의 수를 반환 : twiceOf(Float):Float  
  + 수의 반(1/2)의 수를 반환 : halfOf(Float):Float  
* 구현 객체의 이름은 'Adapter'로 하기  
* Math 클래스에서 두배와 절반을 구하는 함수는 **이미 구현되어** 있음  

##### 추가 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/4940c0d81aee3a7801a53413b7022900eb5bad83)  
* 알고리즘 변경을 원함  
* math클래스에 새롭게 두 배를 구할 수 있는 함수가 추가되어있음  
* 새로 구현한 알고리즘을 이용하도록 수정  
* 절반을 구하는 기능에서 **로그를 찍는 기능을 추가**해야함  
  + Math클래스 내부에 출력문을 쓰면 x, 라이브러리 형태로 쓰는 것이기 때문에 다른사람들이 원하지않는 출력문을 볼 수 있음  

#### 참고  
[위키백과 - 어댑터패턴](https://ko.wikipedia.org/wiki/%EC%96%B4%EB%8C%91%ED%84%B0_%ED%8C%A8%ED%84%B4)  
