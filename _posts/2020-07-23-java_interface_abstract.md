---
title:  "[java] interface와 abstract class"
excerpt: "인터페이스와 추상 클래스"
toc: true
toc_sticky: true
categories:
  - Java
tags:
  - CS
  - Java
---
## 인터페이스와 추상 클래스  
### 인터페이스(interface)  
인터페이스의 구현(상속)을 표현하는 데 있어서는 extends가 아닌 **implements**를 사용한다.  
추상클래스와 다르게 여러개를 상속받을 수 있다.(다중 상속 효과)  
  + 인터페이스를 상속받는 경우, 인터페이스 내부 메소드를 다 구현해야함  
추상 메소드(명세) + 상수 집합체  

#### 인터페이스 특징  
* 인터페이스 내에 존재하는 변수는 무조건 public static final로 선언된다.  
* 인터페이스 내에 존재하는 메소드는 무조건 public abstract로 선언된다.(별도 선언 필요x)  

#### 간단한 예제  
* 메소드 및 구현 예시  
```java
public interface Add {
	int add(int a, int b);
}
public interface Sub {
	int sub(int a, int b);
}
```
```java
public class Calc implements Add, Sub {
	@Override
	public int add(int a, int b) {
		return a + b;
	}
	@Override
	public int sub(int a, int b) {
		return a - b;
	}
}
```


* 상수 예시  
```java
public interface Number {
	int ONE = 1;
	int TWO = 2;
	int THREE = 3;
}
```
```java
public class test implements Number{
	public static void main(String[] args) {
		System.out.println(ONE);
		System.out.println(TWO);
		System.out.println(THREE);
	}
}
```


### 추상 클래스(abstract class)    
abstract 클래스는 완전하지 않은 클래스를 의미한다.  
**인스턴스의 생성이 불가능**하고, 오버라이딩 관계를 형성하는 것이 목적이다.  
추상클래스를 상속받는 클래스는 abstract가 붙은 메소드(추상메소드)를 반드시 오버라이딩 해야한다.  
```java
public abstract class Cookie{
  public abstract void bake(); // 추상 메소드
}
```
#### 추상 메소드 (abstract method)  
abstract 메소드는 구현부가 없는 것이 특징이다.  
주로 메소드는 <code>public void foo(){  // 로직 내용 };</code>형태라면 그냥 세미콜론으로 끝나 구현부분이 없다.  
하나 이상의 추상 메소드가 있다면 abstract 클래스로 선언되어야 한다.  

* abstract 메소드가 없어도 abstract 클래스를 선언할 수 있지만, abstract 메소드가 1개이상이라면 반드시 abstract 클래스여야한다.  

#### 추상클래스 상속과 오버라이딩(예제)  
쿠키클래스와 쿠키를 상속받는 초코쿠키가 있다고 가정할 때,  
쿠키클래스는 추상클래스이고 구현부없는 추상메소드(isBake)를 선언했다.  
멤버변수로 구워진 여부(baked)를 가지고 있다.  
```java
// 쿠키 클래스
public abstract class Cookie {
	boolean baked=false;
	public abstract boolean isBake();
}
```

초코쿠키클래스는 추상클래스인 쿠키클래스를 상속받았고, 이 때 추상메소드는 오버라이딩 되어야한다.  
오버라이딩 안하면 컴파일 에러 발생  
```java
//초코쿠키 클래스
public class ChocoCookie extends Cookie {
	@Override
	public boolean isBaked() {
		if (this.baked)
			return true;
		else
			return false;
	}
}
```
```java
// 메인
	public static void main(String[] args) {
		Cookie c = new ChocoCookie();
		System.out.println(c.isBaked());
		c.baked = true;
		System.out.println(c.isBaked());
	}
```
### 참고  
[추상화클래스와 인터페이스의 용도, 차이점, 공통점](https://marobiana.tistory.com/58)  