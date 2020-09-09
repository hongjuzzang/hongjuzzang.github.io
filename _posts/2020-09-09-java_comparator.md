---
title:  "[java 1.8] Comparator"
excerpt: "ArrayList의 Object 정렬하기"
toc: true
toc_sticky: true
categories:
  - Java
tags:
  - Java
---
## ArrayList의 Object 정렬하기  
문제를 풀던 중 처음보는 자바문법이 있어서 찾아보던 중  
Object를 정렬하는 새로운 방법에 대해 알게되어서 정리한다  
(Comparator - 메서드 사용 방식)  



---
### 객체와 리스트 준비  
문자열 `name`과 정수형 `number`를 가지는 `Student`객체가 있다고 하자  
```java
class Student{
	String name;
	int classNo;
}
```

이때 Student를 담는 ArrayList `students`가 있다  
```java
ArrayList<Student> students = new ArrayList<>();
```

6명의 학생을 추가했다  
```java
students.add(new Student("김야옹", 1));
students.add(new Student("김미미", 1));
students.add(new Student("문토끼", 3));
students.add(new Student("박멍멍", 1));
students.add(new Student("이왈왈", 3));
students.add(new Student("강참치", 2));
```
출력 : `[[김야옹, 1], [김미미, 1], [문토끼, 3], [박멍멍, 1], [이왈왈, 3], [강참치, 2]]`  


6명의 학생을 **번호순으로 정렬하고, 번호가 같다면 이름순으로 정렬**하려고 할 때 어떻게 할 수 있을까?  


---
### 정렬  
#### int를 리턴하는 compareTo 메서드의 비교 방식  
`compareTo(A, B)` 메서드에서 리턴되는 값으로 순서를 정한다  

리턴 값이 0이면 A,B는 같다  
리턴 값이 음수이면 A가 작다(사전에서 앞에 있다)  
리턴 값이 양수이면 A가 크다(사전에서 뒤에 있다)  

숫자의 경우,  
A = 5이고 B = 3 일때  
compareTo(A, B) 하면 리턴 값은 2이다  
결과가 2, 즉 양수이므로 A가 크다  

문자열의 경우,  
A = "hawaii", B = "hello" 일때  
compareTo(A, B)하면 리턴 값은 -4이다  
hawaii가 hello보다 사전에서 먼저나온다  

#### Comparable    
Student 클래스 선언부에 Comparable 인터페이스를 implements한다  
`int compareTo(Object o)` 메서드를 오버라이드 해서 어떤 기준으로 정렬할 지 구현해준다  

```java
class Student implements Comparable<Student> {
	String name;
	int classNo;
...
	@Override
	public int compareTo(Student o) {
		if (this.classNo == o.classNo)
			return this.name.compareTo(o.name);
		return this.classNo - o.classNo;
	}
}
```
```java
Collections.sort(students);
```
출력 : `[[김미미, 1], [김야옹, 1], [박멍멍, 1], [강참치, 2], [문토끼, 3], [이왈왈, 3]]`  


🔒. 만약에 compareTo에서 가장 끝단 return부분에서  
`return o.classNo - this.classNo;`를 하면 결과가 어떻게 나올까??  



🔑. 번호의 내림차순 - 이름 오름차순으로 정렬된다  

출력 : `[[문토끼, 3], [이왈왈, 3], [강참치, 2], [김미미, 1], [김야옹, 1], [박멍멍, 1]]`  


#### Comparator  

##### 클래스에 implements해서 사용  
1) 클래스를 새로 생성해서 Comparator를 implements한다  
```java
class StudentCompare implements Comparator<Student> {
	@Override
	public int compare(Student o1, Student o2) {
		if (o1.classNo == o2.classNo)
			return o1.name.compareTo(o2.name);
		return o1.classNo - o2.classNo;
	}
}
```

2) 생성한 학생정렬 클래스의 객체를 사용해서 정렬한다  
```java
StudentCompare studentCompare = new StudentCompare();
Collections.sort(students, studentCompare);
```


##### 람다식 사용  
클래스를 새로 생성하지 않고 람다식을 이용해서 정렬할 수 있다  
```java
students.sort(new Comparator<Student>() {
	@Override
	public int compare(Student o1, Student o2) {
		if (o1.classNo == o2.classNo)
			return o1.name.compareTo(o2.name);
		return o1.classNo - o2.classNo;
	}
});
```

##### 메서드 사용  
* 준비물 : 정렬기준에 사용할 멤버변수의 getter 메소드  

```java
students.sort(Comparator.comparing(Student::getClassNo).thenComparing(Student::getName));
```


* Student::getClassNo  
람다 표현식의 일종이다  
[클래스 이름 or 인스턴스 이름]::[메소드 이름]  


* comparing  
`Comparator.comparing(Function<? super Student, ? extends Integer> keyExtractor)`  
T - 비교할 원소의 타입  
U - 정렬 기준의 타입  
해당 메서드의 parameter인 keyExtractor 기준으로 정렬한 Comparator를 반환한다  


* thenComparing   
`Comparator.thenComparing(Function<? super Student, ? extends String> keyExtractor)`  
주어진 Comparator를 keyExtractor를 기준으로 정렬한다  


🔒. 메서드를 사용해서 번호는 내림차순 - 이름은 오름차순으로 정렬하고 싶으면???


🔑. reversed() 를 사용한다  
```java
students.sort(Comparator.comparing(Student::getClassNo).reversed().thenComparing(Student::getName));
```

---
### Comparable vs Comparator  
둘다 interface이고, 사용방법은 비슷한데 뭐가다른 걸까..??  

| Comparable | Comparator | 
|:--------------------------:|:------------------------:|
| 정렬 대상 클래스(객체)에 영향을 미친다 <br /> 정렬 대상 클래스에 구현해야 한다   | 정렬 대상 클래스(객체)에 영향을 미치지 않는다   |
| compareTo(Object o) 메소드   | compare(Object o1, Object o2) 메소드   |
| single sorting sequence 제공   | multi sorting sequence 제공   | 
| java.lang 패키지에 있다   | java.util 패키지에 있다   | 
| Collections.sort(List)   | Collections.sort(List, Comparator)   | 
{: rules="groups"}


---
### 참고  
[Interface Comparator<T>](https://docs.oracle.com/javase/8/docs/api/)  
[Difference between Comparable and Comparator](https://www.javatpoint.com/difference-between-comparable-and-comparator)