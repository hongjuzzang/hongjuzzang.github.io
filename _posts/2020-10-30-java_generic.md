---
title:  "[java] Generic 알아보기"
excerpt: "generic, 제너릭이란?"
toc: true
toc_sticky: true
categories:
  - Java
tags:
  - Java
---
## 🎻 generic, 제너릭이란?(JDK 8 기준)  
자바는 공부를 할때마다 새로운 기분이다  
제네릭은, **클래스 내부에서 사용할 데이터 타입을 외부에서 지정하는 기법**을 의미한다  
사용할 타입을 매개변수화 해서 클래스 혹은 인터페이스에서 사용하는 것이다  

### non-generic  
```java
public class Box {
    private Object object;

    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}
```

### generic    
```java
/**
 * @param <T> : Box 클래스에 저장할 타입
 */
public class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) { this.t = t; }
    public T get() { return t; }
}

```
코드에서 `T`가 사용되는데, 타입 매개 변수에도 명명 규칙이 있다  
일반적으로 단일 대문자를 사용한다  
> `E` - 요소 (Java Collections Framework에서 광범위하게 사용됨)  
> `K` - 키  
> `N` - 숫자  
> `T` - 유형  
> `V` - 값  
> `S,U,V` - 유형 2개를 사용한다고 할 때, <T,S> 이런식으로 여러 타입 지정 시 사용   


### non-generic, generic 비교  
* Integer를 저장하는 ArrayList를 예시로 둘을 비교해보자  

| 기준 | non-generic | generic |  
|:-----:|:--------:|:-------:|  
| syntax | `ArrayList list = new ArrayList();` | `ArrayList<Integer> list = new ArrayList<>();` |  
| 타입 안전성 | 다양한 타입을 저장할 수 있다. <br />안전하지않음(런타임 에러 가능성 있음) | <>안에 정의한 타입만 저장할 수 있다.<br/>안전성이 높다(컴파일 에러로 확인 가능) |   
| 캐스팅 | 다양한 타입을 저장하지만, <br />개별적으로 타입 캐스팅을 해줘야한다 <br /> `int num = (int) list.get(0);` | 캐스팅이 필요하지 않다 <br /> `int num = list.get(0);` |  
{: rules="groups"}


## 🎻 제네릭 사용 이유  
`제네릭을 사용하는 이유`는 무엇일까??  
제네릭을 사용하는 코드는 제네릭을 사용하지 않는 코드에 비해 다음과 같은 이점이 있다  
* 캐스트 제거  
* 타입 안전성  
* 코드 중복 제거  

### 🎶 캐스트 제거  
#### 제네릭을 사용하지 <ins>않은</ins> 경우  
```java
public class Box {
    private Object object;
    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
}

Box box = new Box();
box.set("hello");
String s = (String) box.get();
```

#### 제네릭을 사용한 경우  
```java
public class Box<T> {
	private T t;
	public void set(T t) { this.t = t; }
	public T get() { return t; }
}
Box<String> box = new Box<String>();
box.set("hello");
String s = box.get();   // no cast
```
인스턴스를 만들 때 사용하는 타입(String)을 지정했기 때문에 캐스팅을 해 줄 필요가 없다  


### 🎶 타입 안전성  
인스턴스를 만들 때 타입을 지정했기 때문에 런타임 오류를 방지할 수 있다  
즉, 발생할 수 있는 오류를 컴파일 과정에서 확인할 수 있다  

#### 제네릭을 사용하지 <ins>않은</ins> 경우

* 코드

  ```java
  public class Box {
    private Object object;
    public void set(Object object) { this.object = object; }
    public Object get() { return object; }
  }

  Box box = new Box();
  box.set(30);
  try{
      String s1 = (String) box.get();  // 런타임 에러 발생
  }
  catch(Exception e){
      System.out.println("Exception " + e);
  }
  ```

* 출력

  ```
  Exception:
  java.lang.ClassCastException:
  java.lang.Integer cannot be cast to java.lang.String
  ```



#### 제네릭을 사용한 경우

* 코드

  ```java
  public class Box<T> {
	private T t;
	public void set(T t) { this.t = t; }
	public T get() { return t; }
  }
  Box<String> box = new Box<String>();
  box.set(10); // 컴파일 에러 발생

  ```

* 출력

  ```html
  Exception in thread "main" java.lang.Error: Unresolved compilation problem: 
	The method set(String) in the type Box<String> is not applicable for the arguments (int)

  ```
  실행하기 전에도 빨간색 줄이 표시된다  

### 🎶 코드 중복 제거  
좋은 예시는 아니지만(ㅠㅠ)  
String배열, City배열이 있다고 가정했을 때 <ins>각 배열의 첫번째 원소를 가져오는 함수</ins>를 예로 들어보자  

* City class  
  ```java 
  class City {
    String name;
    int areacode;

    public City(String name, int areacode) {   this.name = name;  this.areacode = areacode;  }
  }
  ```

* 미리 생성한  String배열과 City배열  
  ```java
  String[] toys = { "doll", "ball", "robot", "train" };
  City[] cities = new City[3];
  cities[0] = new City("대전", 240); // String : name, int : areacode
  cities[1] = new City("세종", 440);
  cities[2] = new City("서울", 20);
  ```

#### 제네릭을 사용하지 <ins>않은</ins> 경우  
 * Main.java  
  ```java
  nonGenericBox ngenbox = new nonGenericBox();
  System.out.println(ngenbox.getFirstElement(toys));
  System.out.println(ngenbox.getFirstElement(cities));
  ```

* nonGenericBox.java  
  ```java
  public class nonGenericBox {

    public String getFirstElement(String[] strs) {
      return strs[0];
    }
    public City getFirstElement(City[] cities) {
      return cities[0];
    }
  }
  ```
두 배열이 타입이 다르므로 오버로딩해서 첫번째 원소를 리턴한다  
(ArrayIndexOutofBound같은 기타 예외사항은 고려하지않음)  

#### 제네릭을 사용한 경우  
 * Main.java  
  ```java
  GenericBox genbox = new GenericBox();
  System.out.println(genbox.getFirstElement(toys));
  System.out.println(genbox.getFirstElement(cities));
  ```

* GenericBox.java  
  ```java
  public class GenericBox {

    public <T> T getFirstElement(T[] t) {
      return t[0];
    }
  }
  ```
`T`로 받아서 T타입으로(int가 들어오면 int, String이 들어오면 String) 리턴한다  
(ArrayIndexOutofBound같은 기타 예외사항은 고려하지않음)  


## 🎻 제네릭 제한  
제네릭으로 올 수 있는 데이터 타입을 특정 클래스의 자식으로 제한할 수 있다  

`<T extends Parent>` 같이 Parent대신 부모클래스를 적으면 된다  


Number클래스를 상속하는 T만 저장할 수 있는 `NumberCard`를 선언했다  
* NumberCard.java
  ```java
  public class NumberCard<T extends Number> {
    private T num;

    public NumberCard(T num) {
      this.num = num;
    }
  }
  ```
* Main.java  
  ```java
  NumberCard<Integer> intcard = new NumberCard<>(3);
  NumberCard<Float> floatcard = new NumberCard<>(2.0f);
  NumberCard<Double> doublecard = new NumberCard<>(1.0);
  NumberCard<String> stringcard = new NumberCard<>("3"); // 컴파일 에러 : String은 Number클래스를 상속받지않는다
  ```

`<T extends Parent & A & B>`처럼 여러제한을 둘 수 있다  

(이 때 Parent는 클래스이고 A,B는 인터페이스다)  
자바는 단일상속만 허용하므로 A,B 대신에 클래스가 올 수 없고,  
<T extends A & Parent & B> 처럼 extends 뒤에 인터페이스가 올 수 없다  

---
### 참고  
[The Java™ Tutorials - Generic Types](https://docs.oracle.com/javase/tutorial/java/generics/types.html)  
[Java - Generics](https://www.tutorialspoint.com/java/java_generics.htm)  
[제네릭](https://opentutorials.org/module/516/6237)  
