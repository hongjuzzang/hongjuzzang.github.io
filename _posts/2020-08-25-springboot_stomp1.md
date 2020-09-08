---
title:  "[Springboot, Vue.js] STOMP를 사용한 SpringBoot RestAPI 채팅 - 1"
excerpt: "stomp를 활용한 채팅 하기 - 인트로, STOMP란 무엇일까"
toc: true
toc_sticky: true
categories:
  - SpringBoot
tags:
  - SpringBoot
  - STOMP
  - chat
  - Java
---
## stomp를 활용한 채팅 하기 - 인트로, STOMP란 무엇일까  
unithis 프로젝트에서 실시간 채팅을 구현하는데에 많은 구글링을 해야했다(ㅜㅜ)  
프로젝트 구현 내용과 완전히 일치하지는 않지만  
STOMP를 사용하고, springboot, mysql, vue를 활용해 구현하는 방법에 대해 정리해보려 한다  
**프로젝트를 뜯어보기 전에 STOMP 공식 문서를 정리해보자**  
※ 일부 발번역이 섞여있음 ㅠㅠ  


### STOMP  
![stomp](https://stomp.github.io/images/project-logo.png)  
`STOMP(Streaming Text Oriented Messaging Protocol)`  
**STOMP**는 간단한 텍스트 기반 메세징 프로토콜이다  
STOMP 클라이언트는 모든 STOMP 메세지 브로커와 통신할 수 있으며, 언어, 플랫폼 간에 광범위한 상호 운용적인 통신을 제공한다



#### 클라이언트  
클라이언트와 서버는 스트림을 통해 전송된 STOMP 프레임을 사용해서 통신하는데, 프레임은 다음과 같은 구조를 가지고 있다  
```
COMMAND
header1:value1
header2:value2

Body^@
```

클라이언트는 `SEND, SUBSCRIBE, UNSUBSCRIBE, BEGIN, COMMIT, ABORT, ACK, NACK, DISCONNECT`의 프레임을 보낼 수 있는데  
내 프로젝트는 SEND, SUBSCRIBE만 사용해서 진행한다  



##### SEND  
SEND 프레임은 메세지 시스템의 대상을 메세지를 보낸다  
```
SEND
destination:/queue/a
content-type:text/plain

hello queue a
^@
```
본문이 있는 경우 *content-length* 헤더와  *content-type*헤더를 포함해야 한다  
또한 어디로 보낼지에 대한 destination은 필수 헤더다  
이 경우에 "/queue/a"로 메세지가 전송된다  



##### SUBSCRIBE  
SEND와 마찬가지로 클라이언트가 구독하려는 대상을 나타내는 헤더가 필요하다  
```
SUBSCRIBE
id:0
destination:/queue/foo
ack:client

^@
```
클라이언트가 다음과같은 구조의 프레임을 전달하면, 다른 클라이언트가 SEND로 */queue/foo*로 보낸 메세지들을 받을 수 있다  



##### UNSUBSCRIBE, BEGIN, COMMIT, ABORT, ACK, NACK, DISCONNECT  
* UNSUBSCRIBE  
  기존의 SUBSCRIBE를 제거하는데 사용한다  
* BEGIN  
  트랜잭션을 시작할 때 사용한다  
* COMMIT  
  진행중인 트랜잭션의 변경사항을 저장하는데 사용한다  
* ABORT  
  트랜잭션의 변경사항을 변경 이전으로 되돌릴 때 사용한다  
* ACK  
  대상 클라이언트에서 메세지를 사용 여부를 확인하는데 사용한다  
* NACK  
  대상 클라이언트가 메세지를 사용하지 않았음을 서버에 알리는데 사용한다  
* DISCONNECT  
  클라이언트가 서버에서 연결이 끊어졌을 때 사용한다


#### 서버  
서버는 경우에 따라 `MESSAGE, RECEIPT, ERROR`를 클라이언트에게 보낸다  


##### MESSAGE  
MESSAGE 프레임은 구독한 클라이언트에게 메세지를 전달할때 사용한다  
```
MESSAGE
subscription:0
message-id:007
destination:/queue/a
content-type:text/plain

hello queue a^@
```
destination은 SEND프레임에서 사용된 헤더랑 동일해야 구독한 클라이언트가 받을 수 있다  


##### RECEIPT, ERROR  
* RECEIPT  
클라이언트 프레임이 서버에 의해 처리되었음을 확인하는 프레임이다  
* ERROR  
서버에서 문제가 있을 시 전달하는 프레임이다  

### Springboot에서 STOMP 사용 시 장점  
스프링 프레임워크와 스프링 시큐리티에서 WebSocket을 사용하는 것보다 더 풍부한 프로그래밍을 할 수 있다  
* 메세징 프로토콜과 메세징 형식을 개발할 필요가 없다  
* STOMP 클라이언트는 Java 클라이언트를 포함해서 사용할 수 있다  
* 메세지 브로커를 사용하면 구독을 관리하고 메세지를 broadcast하는데 사용할 수 있다  


### Publisher / Subscriber  
스프링에서 WebSocket과 함께 STOMP 프로토콜을 사용하는 방법을 간단하게 그림으로 알아보자  
![pubsub1](/assets/images/post/200825-1.png)  


분홍토끼는 `/rabbits`를 구독했다  
고양이는 `/cats`를 구독했다  
이때 갈색토끼가 `/rabbits`로 메세지를 보낸다  
메세지 브로커(WebSocketMessageBroker)에서 갈색토끼가 보낸 메세지의 목적지인 `/rabbits`를 구독한 대상(분홍토끼)에게 보낸다  
분홍토끼는 `/rabbits`를 구독했기 때문에 서버로부터 메세지를 받는다  


![pubsub2](/assets/images/post/200825-2.png)  


두번째 그림은 분홍토끼와 고양이 모두 `/animals`를 구독했다  
갈색토끼가 `/animals`로 메세지를 보냈을 때 둘다 구독중이므로 메세지를 받을 수 있다  


전체 소스 : [Github Repository](https://github.com/hongjuzzang/springboot-stomp.git)
#### 참고  
[STOMP BACKGROUND](https://stomp.github.io/stomp-specification-1.2.html#Background)  
[STOMP Over WebSocket](http://jmesnil.net/stomp-websocket/doc/)  
[Web on Servlet Stack - 4.4. STOMP](https://docs.spring.io/spring/docs/5.0.4.RELEASE/spring-framework-reference/web.html#websocket-stomp)