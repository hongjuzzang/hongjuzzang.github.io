---
title:  "브라우저 동작 방법 - 1"
excerpt: "URL을 입력한 후 화면에 보이기까지"
toc: true
toc_sticky: true
categories:
  - Web
tags:
  - Web Browser
---
## URL을 입력한 후 화면에 보이기까지   
웹상에서 화면에 보이기까지의 과정을 정리해보자   
구글 웹사이트에 들어가는 과정을 단순하게 정리하자면,  


1. 브라우저 주소창에 www.google.com을 입력한다  
2. 구글 홈페이지 화면이 나온다  

사용자 입장에서는 정말 간단하지만 눈에 안보이는 과정은 더 있다  

```
1. 브라우저 주소창에 www.google.com을 입력한다  
2. DNS를 통해 IP주소를 획득한다  
3. 획득한 IP주소에 있는 서버와 TCP 3 Way Handshake를 진행한다  
4. 통신을 맺은 서버에 Http Request를 한다  
5. 서버에서 보낸 Http Response를 통해 HTML 파일을 받는다  
6. 브라우저가 HTML을 분석해서 화면으로 그린다  
```
순서대로 알아보자 ~~~ 
(이 포스트는 1 ~ 3까지만 있음)

---
### 🎈1. 브라우저 주소창에 www.google.com을 입력한다  
#### 브라우저(Web Browser) ??
**웹 브라우저 또는 브라우저(영어: web browser 또는 browser)**는 웹 서버에서 이동하며 쌍방향으로 통신하고 HTML 문서나 파일을 출력하는 그래픽 사용자 인터페이스 기반의 응용 소프트웨어이다. 주요 웹 브라우저로는 모질라 파이어폭스, 구글 크롬, 인터넷 익스플로러/마이크로소프트 엣지, 오페라, 사파리가 있다.  

#### 브라우저의 주요 기능  
***브라우저의 주요 기능은 사용자가 선택한 자원을 서버에 요청하고 브라우저에 표시하는 것***이다.  
자원은 보통 HTML 문서지만 PDF나 이미지 또는 다른 형태일 수 있다. 자원의 주소는 URI(Uniform Resource Identifier)에 의해 정해진다.

브라우저는 HTML과 CSS 명세에 따라 HTML 파일을 해석해서 표시하는데 이 명세는 웹 표준화 기구인 W3C(World Wide Web Consortium)에서 정한다. 과거에는 브라우저들이 일부만 이 명세에 따라 구현하고 독자적인 방법으로 확장함으로써 웹 제작자가 심각한 호환성 문제를 겪었지만 최근에는 대부분의 브라우저가 표준 명세를 따른다.

브라우저의 사용자 인터페이스는 일반적으로 <ins>주소 표시줄, 이전버튼, 다음버튼, 북마크, 새로고침 버튼, 정지버튼, 홈 버튼 등</ins>이 있다  

#### 브라우저 구조  
![high level structure of a browser](/assets/images/post/200923-0.png)  

* 사용자 인터페이스(User Interface)  
  주소 표시줄, 이전/다음 버튼, 북마크 메뉴 등. 요청한 페이지를 보여주는 창을 제외한 나머지 모든 부분이다  
* 브라우저 엔진(Browser Engine)  
  사용자 인터페이스와 렌더링 엔진 사이의 동작을 제어  
* 렌더링 엔진(Rendering Engine)  
  요청한 콘텐츠를 표시. 예를 들어 HTML을 요청하면 HTML과 CSS를 파싱하여 화면에 표시함  
* 통신(Networking)  
  HTTP 요청과 같은 네트워크 호출에 사용됨. 이것은 플랫폼 독립적인 인터페이스이고 각 플랫폼 하부에서 실행됨  
* UI 백엔드(UI Backend)  
  콤보 박스와 창 같은 기본적인 장치를 그림. 플랫폼에서 명시하지 않은 일반적인 인터페이스로서, OS 사용자 인터페이스 체계를 사용  
* 자바스크립트 해석기(JavaScript Interpreter)  
  자바스크립트 코드를 해석하고 실행  
* 자료 저장소(Data Persistence)  
  이 부분은 자료를 저장하는 계층이다. 쿠키를 저장하는 것과 같이 모든 종류의 자원을 하드 디스크에 저장할 필요가 있다. HTML5 명세에는 브라우저가 지원하는 '웹 데이터 베이스'가 정의되어 있다  
{: .notice}


---
### 🎈2. DNS를 통해 IP주소를 획득한다  
#### DNS(Domain Name System) ??  
**도메인 네임 시스템(Domain Name System, DNS)**은 호스트의 도메인 이름을 호스트의 네트워크 주소로 바꾸거나 그 반대의 변환을 수행할 수 있도록 하기 위해 개발되었다.  
특정 컴퓨터(또는 네트워크로 연결된 임의의 장치)의 주소를 찾기 위해, 사람이 이해하기 쉬운 도메인 이름을 숫자로 된 식별 번호(IP 주소)로 변환해 준다. 도메인 네임 시스템은 흔히 "전화번호부"에 비유된다.  
인터넷 도메인 주소 체계로서 TCP/IP의 응용에서, www.example.com과 같은 주 컴퓨터의 도메인 이름을 192.168.1.0과 같은 IP 주소로 변환하고 라우팅 정보를 제공하는 분산형 데이터베이스 시스템이다.  

#### DNS 동작과정  


![img](/assets/images/post/200923-1.jpg)  
* 설명  
1. 사용자가 웹 브라우저를 열어 주소 표시줄에 www.example.com을 입력하고 Enter 키를 누른다  
2. 우선 Local DNS에서 IP가 있는지 확인한다.  
없는 경우, www.example.com에 대한 요청은 일반적으로 케이블 인터넷 공급업체, DSL 광대역 공급업체 또는 기업 네트워크 같은 인터넷 서비스 제공업체(ISP)가 관리하는 **DNS 해석기(DNS Resolver)**로 라우팅된다  
  > DNS 해석기(DNS Resolver)  
  > DNS 서버에 대한 액세스를 수행하는 어플리케이션  
  > 네임서버에 원하는 호스트에 대한 정보를 조회(질의)하고, 그 응답으로부터 필요 정보를 추출하는 DNS 클라이언트용 프로그램  
3. ISP(인터넷 서비스 제공자, Internet Service Provider)의 DNS 해석기는 www.example.com에 대한 요청을 **DNS Root Server**에 전달한다  
4. Root DNS가 IP주소를 모르는 경우 다른 DNS서버에 물어보라고 다른 DNS주소를 알려준다(com 네임서버 목록 + ip주소)  
("com 도메인 관리하는 DNS서버한테 가봐")  
5. ISP의 DNS 해석기는 www.example.com에 대한 요청을 이번에는 .com 도메인의 **TLD server**에게 전달한다  
  > TLD (Top Level Domain, 최상위 도메인)  
  > 도메인 네임에 가장 마지막부분을 말한다  
  > <ins>.com</ins> 같은 일반 최상위 도메인과 <ins>.kr</ins> 같은 국가 코드 최상위 도메인으로 나뉜다  
6. 이번에도 해당정보(IP주소)가 없는 경우 다른 서버에게 물어보라고 한다(example.com 네임서버 목록 + ip주소)  
("example.com 도메인한테 가봐라")  
7. 다시 도전.. www.example.com에 대한 요청을 6번에서 알려준 example.com 도메인의 **Authoritative Server**에게 전달한다  
  > Authoritative Server / Name Server  
  > 디렉터리 서비스 프로토콜을 실행하는 프로그램이나 서버를 통칭한다  
  > 일반적으로 인터넷에서 도메인 이름 서비스를 제공하는 서버를 말한다   
8. ISP의 DNS 해석기가 마침내 사용자에게 필요한 IP 주소를 찾았습니다..  
("니가 찾는 IP는 192.0.2.44야!")  
9. DNS 해석기는 이 값을 웹 브라우저로 반환한다  
DNS 해석기는 다음에 누군가가 example.com을 탐색할 때 좀 더 빠르게 응답할 수 있도록 사용자가 지정하는 일정 기간 동안 example.com의 IP 주소를 local DNS에 캐싱(저장)한다  
{: .notice}  

이와 같이 Local DNS 서버가 여러 DNS 서버를 차례대로 물어봐서 그 답을 찾는 과정을 **Recursive Query**라고 부른다  


---
### 🎈3. 획득한 IP주소에 있는 서버와 TCP 3 Way Handshake를 진행한다  


![3 way handshake](/assets/images/post/200923-2.png)  
TCP / IP 클라이언트 컴퓨터가 서버에 연결하는 방법을 **Three-Way Handshake** 라고 한다  
여기에는 메시지 라고 부르는 세 개의 신호 전송이 포함된다  


1.  SYN  
SYN 데이터 패킷은 클라이언트에서 서버로 연결하기위한 요청이다  
> 친구에게 전화를 걸고 친구가 전화받기를 기다린다  
2.  SYN-ACK  
이는 서버의 포트가 열려 있고 연결할 수 있음을 의미한다  
서버를 사용할 수없는 경우 시간이 초과되고 연결이 설정되지 않는다  
> 친구가 전화를 받고, "여보세요?" 라고 한다  
3. ACK  
클라이언트는 SYN/ACK 데이터 패킷을 수신하고 ACK 메시지로 서버에 다시 응답한다  
> 전화 연결 확인 후 대답한다  
4. ESTABLISHED  
통신 채널이 설정되고 클라이언트는 이제 서버에 연결하여 데이터를 송수신 할 수 있다  
> 친구와 할말을 주고받는다  
{: .notice}  

연결을 종료할 때는 4 way handshake를 진행한다  

---
다음 포스팅에 ...  


### 참고  
[웹브라우저](https://ko.wikipedia.org/wiki/%EC%9B%B9_%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80)  
[How Web Browsers Work ?](https://medium.com/@pdster/how-web-browsers-work-6385b9374375)  
[도메인 네임 시스템](https://ko.wikipedia.org/wiki/%EB%8F%84%EB%A9%94%EC%9D%B8_%EB%84%A4%EC%9E%84_%EC%8B%9C%EC%8A%A4%ED%85%9C)  
[DNS란 무엇입니까?](https://aws.amazon.com/ko/route53/what-is-dns/)  
[Resolver, DNS Resolver, DNS Client   해결자, 리졸버, DNS 해석기, DNS 변환기](http://www.ktword.co.kr/abbr_view.php?m_temp1=1487)  
[DNS 기본 동작 설명](https://www.netmanias.com/ko/post/blog/5353/dns/dns-basic-operation)  
[The TCP Handshake Protocol](https://medium.com/0xcode/the-tcp-handshake-protocol-9c0b54c99f1c)  
[Building Java Client/Server Applications with TCP](https://www.luxoft-training.com/news/building-java-client-server-applications-with-tcp/)  