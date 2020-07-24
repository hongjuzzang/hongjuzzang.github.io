---
title:  "자바 디자인 패턴 (14) - Facade pattern "
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


### Sec.14 : 퍼사드 패턴(Facade Pattern)  
**퍼사드** :  클래스 라이브러리 같은 어떤 소프트웨어의 다른 커다란 코드 부분에 대한 간략화된 인터페이스를 제공하는 객체  
복잡한 과정을 간단하게 표현하는 패턴  

퍼사드는 소프트웨어 라이브러리를 쉽게 사용할 수 있게 해준다.  
또한 퍼사드는 소프트웨어 라이브러리를 쉽게 이해할 수 있게 해준다.  
퍼사드는 공통적인 작업에 대해 간편한 메소드들을 제공해준다.  
퍼사드는 라이브러리를 사용하는 코드들을 좀 더 읽기 쉽게 해준다.  
퍼사드는 라이브러리 바깥쪽의 코드가 라이브러리의 안쪽 코드에 의존하는 일을 감소시켜준다.  
대부분의 바깥쪽의 코드가 퍼사드를 이용하기 때문에 시스템을 개발하는 데 있어 유연성이 향상된다.  
퍼사드는 좋게 작성되지 않은 API의 집합을 하나의 좋게 작성된 API로 감싸준다.  
래퍼(wrapper)가 특정 인터페이스를 준수해야 하며, 폴리모픽 기능을 지원해야 할 경우에는 어댑터 패턴을 쓴다.  
단지, 쉽고 단순한 인터페이스를 이용하고 싶을 경우에는 퍼사드를 쓴다.

#### 클래스 다이어그램  
![img](/assets/images/post/200724-fcpt.png)  
다양한 패키지에서부터 여러 클래스들을 제공받고, 단순한 함수를 제공해서 client가 이용하기 쉽도록 함  

#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/24dcc21d2882e47016c7eb36a105905108b7c9eb)  
* HelpSystem01-03에 접근하는 Facade만들기  
   + Facade의 process()에서는 helpsystem01-03 의 process() 호출  
   + 각 helpSYstem의 process()는 클래스 이름을 출력함  

#### 참고  
[위키 백과 - 퍼사드 패턴](https://ko.wikipedia.org/wiki/%ED%8D%BC%EC%82%AC%EB%93%9C_%ED%8C%A8%ED%84%B4)  