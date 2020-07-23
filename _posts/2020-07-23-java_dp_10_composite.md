---
title:  "자바 디자인 패턴 (10) - Composite pattern "
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


### Sec.10 : 컴포짓 패턴(Composite Pattern)  
객체들의 관계를 트리구조로 구성하여 부분-전체 계층을 표현하는 패턴으로, 사용자가 단일 객체와 복합 객체 모두 동일하게 다루도록 함  
컨테이너와 내용을 동일 시 하는 패턴  

#### 클래스 다이어그램  
![img](/assets/images/post/200723-cppt.jpg)  
Component - 모양 틀  
leaf - 내용물  
composite - leaf를 담음(담는 그릇)  
#### 실습  
##### 요구사항  
[commit](https://github.com/hongjuzzang/DesignPattern/commit/2df6f34c3046d730b61f48fda3d2d186c14ab37a )  
* 파일(폴더) 시스템  
![dir](/assets/images/post/200723-cppt1.JPG)  
파일/폴더 시스템 구현 및 디렉토리 구조 출력  


#### 참고  
[위키 백과 - 컴포짓 패턴](https://ko.wikipedia.org/wiki/%EC%BB%B4%ED%8F%AC%EC%A7%80%ED%8A%B8_%ED%8C%A8%ED%84%B4)  