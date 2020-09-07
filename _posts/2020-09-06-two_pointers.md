---
title:  "[java] 투 포인터 알고리즘"
excerpt: "투 포인터(Two pointers) 알고리즘 설명 및 자바 코드"
toc: true
toc_sticky: true
categories:
  - Algorithm
tags:
  - Java
  - Algorithm
  - 알고리즘
---
## 투 포인터(Two pointers) 알고리즘  
### 개념  
투 포인터 알고리즘은 두 배열에서 검색 시 쉽고 효율적인 방법으로 사용한다  

투 포인터 , 즉 두 개의 포인터의 시작 위치 배치방법은 2가지가 있다  
1) 배열의 첫번째 원소와 배열의 마지막 원소에서 시작하는 경우(반대 진행방향)  
2) 둘 다 첫번째 원소에서 시작하는 경우(같은 진행방향)  


#### 배열의 첫번째 원소와 배열의 마지막 원소에서 시작하는 경우(반대 진행방향)  
N개의 정수를 가진 정렬된 배열 A가 있을 때  
*두 원소의 합이 X인 경우가 있는지*에 대해 확인해 본다면 어떻게 할까?  


투 포인터에서 하나의 포인터는 첫번째 원소를 나타내고 다른 하나는 마지막 원소를 나타낸다  
두 포인터는 서로를 향한 방향으로 이동하는데 두 포인터가 만나거나 어떤 조건을 만족할 때 까지 이동한다  


1) 두 원소의 합이 x보다 작다면 왼쪽 포인터를 오른쪽으로 이동한다  
정렬된 배열에서 합을 키우기 위해 오른쪽으로 이동시키는 것이다  

2) 만일 두 원소의 합이 x보다 크다면 오른쪽 포인터를 왼쪽으로 이동한다  
3) x를 찾을 때 까지 반복한다  

```java
private static boolean isPairSum(int[] n, int x) {
	int left = 0;
	int right = n.length - 1;
	while (left < right) {
		if (n[left] + n[right] == x)
			return true;
		else if (n[left] + n[right] > x)
			right--;
		else
			left++;
	}
	return false;
}
```
2중 for문을 사용한 방법은 시간복잡도가 O(n²)이다  
하지만 투 포인터를 사용하면 O(n)로 끝낼 수 있다  



#### 둘 다 첫번째 원소에서 시작하는 경우(같은 진행방향)  
n개의 정수를 가진 배열 A가 있을 때  
배열에서 *i,j까지 원소의 합이 m이 되는 경우의 수*을 구한다면 어떻게 해야할까?  
[문제 : 백준 - 수들의 합2](https://www.acmicpc.net/problem/2003)  


두 포인터를 start, end라고 지칭할 때, `start = 0, end = 0`으로 시작한다  
이 때 두 포인터는 항상 `start <= end`여야 한다  
start는 해당 칸을 포함하고, end는 해당칸을 포함하지 않는다고 하자  

`start < n`까지 반복한다  
   1) 현재 부분합이 m 초과이거나, end == n이면 start++  
   2) 아니라면 end++  
   3) 현재 부분합이 m이라면 결과 + 1  


```java
long sum = 0;
int start = 0;
int end = 0;
long result = 0;
while (start < N) {
	if (sum > M || end == N) {
		sum -= arr[start];
		start++;
	} else {
		sum += arr[end];
		end++;
	}
	if (sum == M)
		result++;
}
System.out.println(result);
```

### 응용  
투 포인터를 응용해서 풀 수 있는 문제들은,  
* 배열에서 x에 가까운 합을 이루는 원소 쌍(pair) 찾기  
* 합이 0이 되는 모든 3가지 원소 조합 찾기  
* 3원소의 합이 x가 되는 경우 찾기  
* 2원소의 합이 다른 원소의 합이 되는 경우 찾기  
등등이 있다  


### [참고] 슬라이딩 윈도우  
슬라이딩 윈도우는 투 포인터와 유사하게 접근한다  
차이점은 투 포인터의 경우 범위가 앞뒤로 움직이지만  
슬라이딩 윈도우의 경우에는 **일정 크기를 유지**하면서 배열 내에서 움직인다는 차이점이 있다  


## 풀 수 있는 문제  
관련해서 풀만한 문제 몇개   
[백준 - 수들의 합 2](https://www.acmicpc.net/problem/2003)  
[프로그래머스 - 보석 쇼핑](https://programmers.co.kr/learn/courses/30/lessons/67258)  
[백준 - 소수의 연속합](https://www.acmicpc.net/problem/1644)   
[백준 - 수 고르기](https://www.acmicpc.net/problem/2230)   


## 참고  
[Two Pointers Technique](https://www.geeksforgeeks.org/two-pointers-technique/)  
[17. 투 포인터, 슬라이딩 윈도우 (1)](https://code0xff.tistory.com/128)  
[투 포인터(Two Pointers Algorithm), 슬라이딩 윈도우(Sliding Window)](https://m.blog.naver.com/kks227/220795165570)  
[Two Pointer Algorithm | Two Sum Problem | Solve DS Problems in O(N) Time](https://www.youtube.com/watch?v=2wVjt3yhGwg)  