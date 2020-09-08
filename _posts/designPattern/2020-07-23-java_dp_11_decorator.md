---
title:  "자바 디자인 패턴 (11) - Decorator pattern "
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


### Sec.11 : 데코레이터 패턴(Decorator Pattern)  
주어진 상황 및 용도에 따라 어떤 객체에 책임을 덧붙이는 패턴  
기능 확장이 필요할 때 서브클래싱 대신 쓸 수 있는 유연한 대안이 될 수 있음  


#### 클래스 다이어그램  
![img](/assets/images/post/200723-dcpt.jpg)  
component - 실질적인 인스턴스를 컨트롤하는 역할  
concreteComponent - component의 실질적인 인스턴스 부분, 책임의 주체의 역할  
decorator - component 와 concrete decorator를 동일시 하도록 해주는 역할  
concreteDecorator - 실질적인 장식 인스턴스 및 정의이며, 추가된 책임의 주체 부분  


#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/f41454e70e3a8f6c36efec41c1980c09bbe238b5)  
![img](/assets/images/post/200723-dcpt1.JPG)  
* 커피 제조 방법을 기반으로한 가격 산출  
  + 에스프레소 : 커피의 기본  
  + 아메리카노 : 에스프레소 + 물  
  + 카페라떼 : 에스프레소 + 스팀밀크  
  + 헤이즐넛 아메리카노 : 아메리카노 + 헤이즐넛 시럽  
  + 카페모카 : 카페라떼 + 초콜릿시럽  
  + 카라멜 마끼야또 : 카페라뗴 + 카라멜 시럽  
(어리둥절 메모)  
#### 참고  
[위키 백과 - 데코레이터 패턴](https://ko.wikipedia.org/wiki/%EB%8D%B0%EC%BD%94%EB%A0%88%EC%9D%B4%ED%84%B0_%ED%8C%A8%ED%84%B4)  