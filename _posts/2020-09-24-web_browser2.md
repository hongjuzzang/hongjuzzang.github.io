---
title:  "브라우저 동작 방법 - 2"
excerpt: "URL을 입력한 후 화면에 보이기까지"
toc: true
toc_sticky: true
categories:
  - Web
tags:
  - Web Browser
---
## URL을 입력한 후 화면에 보이기까지   
브라우저에 www.google.com을 입력한 후 화면에 보이기까지 과정을 간략하게 정리하면 다음과 같다.  

```
1. 브라우저 주소창에 www.google.com을 입력한다  
2. DNS를 통해 IP주소를 획득한다  
3. 획득한 IP주소에 있는 서버와 TCP 3 Way Handshake를 진행한다  
4. 통신을 맺은 서버에 Http Request를 한다  
5. 서버에서 보낸 Http Response를 통해 HTML 파일을 받는다  
6. 브라우저가 HTML을 분석해서 화면으로 그린다  
```
[이전 포스트](https://hongjuzzang.github.io/web/web_browser1/)에서 1~3까지 과정을 정리했다  


이번 포스트에서는 www.google.com의 아이피주소를 알아낸 다음의 과정을 정리해보자  

---
### 🎈4. 통신을 맺은 서버에 Http Request를 한다  
#### HTTP(Hyper Text Transfer Protocol) ??  
 **HTTP**는 W3 상에서 정보를 주고받을 수 있는 프로토콜이다  
주로 TCP를 사용하고 HTTP/3 부터는 UDP를 사용하며, 80번 포트를 사용한다  
<ins>HTTP는 클라이언트와 서버 사이에 이루어지는 요청/응답(request/response) 프로토콜</ins>이다  

클라이언트인 웹 브라우저가 HTTP를 통하여 서버로부터 웹페이지(HTML)나 그림 정보를 요청하면,  
서버는 이 요청에 응답하여 필요한 정보를 해당 사용자에게 전달하게 된다. 이 정보가 모니터와 같은 출력 장치를 통해 사용자에게 나타나는 것이다  

HTTP를 통해 전달되는 자료는 http:로 시작하는 URL(인터넷 주소)로 조회할 수 있다  

#### Http Request  
클라이언트가 서버에게 보내는 요청 메세지의 구조는 다음과 같다  
![http request](/assets/images/post/200924-2.png)  
1. 요청 내용(request Line)  
  > 실행되어야 할 요청, 또는 요청 수행에 따른 성공과 실패가 기록되어있다  
  > 항상 한줄로 끝난다  
  > HTTP 메소드 / Request target / HTTP version
2. 요청 헤더(Request headers)  
  > 요청에 대한 설명, 혹은 메시지 본문에 대한 설명이 들어간다  
  > key : value 형태로 이루어져있다  
  > Host, User-Agent, Accept, Connection, Content-Type, Content-Length  
3. 빈 줄(empty line)  
  > 요청에 대한 모든 메타정보가 전송되었음을 알리는 빈줄이 삽입된다  
4. 요청 본문(Request Message Body)  
  > 요청과 관련된 내용이 옵션으로 들어가거나, 응답과 관련한 문서가 들어간다  
  > 본문의 존재 유무 및 크기는 첫 줄과 HTTP헤더에 명시된다  
{: .notice}  
* HTTP메세지의 <ins>1. 요청내용</ins>과  <ins>2. 헤더</ins>는 `Request Header`라고 부르고,  
HTTP 메세지의 페이로드는 `Body`이라고 한다  



---
### 🎈5. 서버에서 보낸 Http Response를 통해 HTML 파일을 받는다  
서버에서 클라이언트에게 보내는 응답 메세지의 구조는 다음과 같다  
![http response](/assets/images/post/200924-3.png)  
1. 상태 표시 라인(status Line)  
  > 항상 한줄로 끝난다  
  > HTTP version / status code / status text
2. 응답 헤더(Response headers)  
  > HTTP 요청 메세지의 헤더와 동일하다  
  > key : value 형태로 이루어져있다  
  > Host, *Server* , Accept, Connection, Content-Type, Content-Length(User-Agent 제외)  
3. 빈 줄(empty line)  
  > 요청에 대한 모든 메타정보가 전송되었음을 알리는 빈줄이 삽입된다  
4. 응답 본문(Response Message Body)  
  > 전송하는 데이터가 없으면 비어있다(201, 204 상태코드에는 보통 본문이 없다)  
{: .notice}  

---
### 🎈6. 브라우저가 HTML을 분석해서 화면으로 그린다  
[이전 포스트](https://hongjuzzang.github.io/web/web_browser1/)의 *1. 브라우저 구조*에서 `렌더링 엔진`이 있다  
#### 렌더링 엔진  
렌더링 엔진의 역할은 <ins>요청 받은 내용을 브라우저 화면에 표시하는 일</ins>이다  

렌더링 엔진에는 파이어폭스는 모질라에서 직접만든 게코(Gecko)엔진을 사용하며,  
사파리와 크롬은 웹킷(Webkit)엔진을 사용한다  

#### 렌더링 엔진 동작 과정  
![main flow](/assets/images/post/200924-4.png)  
1. DOM 트리 구축을 위한 HTML 파싱  
  > 렌더링 엔진은 HTML 문서를 파싱하고 "콘텐츠 트리" 내부에서 태그를 DOM 노드로 변환한다  
  > 그 다음 외부 CSS 파일과 함께 포함된 스타일 요소도 파싱한다   
  > 스타일 정보와 HTML 표시 규칙은 "렌더 트리"라고 부르는 또 다른 트리를 생성한다  
2. 렌더 트리 구축  
  > 렌더 트리는 색상 또는 면적과 같은 시각적 속성이 있는 사각형을 포함하고 있는데 정해진 순서대로 화면에 표시된다  
3. 렌더 트리 배치  
  > 렌더 트리 생성이 끝나면 배치가 시작되는데 이것은 각 노드가 화면의 정확한 위치에 표시되는 것을 의미한다  
4. 렌더 트리 그리기  
  > 다음은 UI 백엔드에서 렌더 트리의 각 노드를 가로지르며 형상을 만들어 내는 그리기 과정이다  
{: .notice}  

일련의 과정들이 점진적으로 진행된다는 것을 아는 것이 중요하다  
렌더링 엔진은 좀 더 나은 사용자 경험을 위해 가능하면 빠르게 내용을 표시하는데 모든 HTML을 파싱할 때까지 기다리지 않고 배치와 그리기 과정을 시작한다. <ins>네트워크로부터 나머지 내용이 전송되기를 기다리는 동시에 받은 내용의 일부를 먼저 화면에 표시하는 것</ins>이다  



* Webkit의 동작 과정  
![webkit main flow](/assets/images/post/200924-5.png)  


### 🎈 그 외 몇가지  
#### TCP / UDP  
![tcp udp](/assets/images/post/200924-1.jpg)  
* 공통점  
포트 번호를 이용하여 주소를 지정한다  
데이터 오류 검사를 위한 checksum이 존재한다  
* 차이점  
  - TCP  
  > 신뢰성 있는 데이터 전송  
  > 연결이 성공해야 통신이 가능하다(연결형 프로토콜)  
  > 데이터의 경계를 구분하지 않음(Byte-Stream service)  
  > 일 대 일(Unicast) 통신  
  - UDP  
  > 비신뢰성 데이터 전송  
  > 비연결형 프로토콜(연결없이 통신 가능)  
  > 데이터의 경계를 구분함(Datagram Service)  
  > 일 대 일(Unicast), 일 대 다(Broadcast), 다 대 다(Multicast) 통신  
{: .notice}  



#### DOM  

DOM : `문서 객체 모델(Document Object Model)`의 준말이다  
이것은 HTML 문서의 객체 표현이고 외부를 향하는 자바스크립트와 같은 HTML 요소의 연결 지점이다. 트리의 최상위 객체는 문서이다  


*파싱 트리*는 DOM 요소와 속성 노드의 트리로서 출력트리가 된다  
DOM은 마크업과 1:1의 관계를 맺는다  
예를 들면 이런 마크업이 있다고 하자  
```html
 <html>
  <body>
   <p>Hello World</p>
   <div><img src="example.png" /></div>
  </body>
</html>  
```
<br />

위의 마크업은 아래와 같은 DOM 트리로 변환할 수 있다  
<br />

![마크업의 DOM 트리](https://d2.naver.com/content/images/2015/06/helloworld-59361-8.png)  


---
### 참고  
[HTTP](https://ko.wikipedia.org/wiki/HTTP)  
[TCP vs. UDP](https://microchipdeveloper.com/tcpip:tcp-vs-udp)  
[TCP 와 UDP 차이를 자세히 알아보자](https://velog.io/@hidaehyunlee/TCP-%EC%99%80-UDP-%EC%9D%98-%EC%B0%A8%EC%9D%B4#%EA%B3%B5%ED%86%B5%EC%A0%90)  
[HTTP Request Message](https://documentation.help/DogeTool-HTTP-Requests-vt/http_request.htm)  
[[web] HTTP 요청-응답 메시지 구조 request-response](https://ychae-leah.tistory.com/82)  
[브라우저는 어떻게 동작하는가?](https://d2.naver.com/helloworld/59361)  
[How Web Browsers Work ?](https://medium.com/@pdster/how-web-browsers-work-6385b9374375)  