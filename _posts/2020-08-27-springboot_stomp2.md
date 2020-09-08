---
title:  "[Springboot, Vue.js] STOMP를 사용한 SpringBoot RestAPI 채팅 - 2"
excerpt: "stomp를 활용한 채팅 하기 - springboot 코드 뜯어보기"
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
## stomp를 활용한 채팅 하기 - springboot 코드 뜯어보기  
Springboot에서 stomp를 사용하기 위한 준비부터 클라이언트와 프레임을 주고 받는 과정을 정리해보자  
(채팅방, 메세지 객체 등 다른 부가적인 api구현보다는 STOMP 중점)

### 0. 개발 환경  
springboot 2.3.3 , java 1.8 (STS)  
vue CLI 4.5.3 (VS code)  
mySQL 8.0  

### 1. WebSocketConfig.java  
`WebSocketConfig.java`파일은 stomp의 엔드포인트, 그리고 메세지 프레임의 라우팅 방향을 지정한다  
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/pub"); // publish 
		registry.enableSimpleBroker("/sub"); // subscribe
	}
}
```
* @EnableWebSocketMessageBroker  
WebSocket을 통해 브로커 메시징을 활성하기 위해 사용한다  
* implements WebSocketMessageBrokerConfigurer  
WebSocket 클라이언트로 받은 메세지 핸들링을 구성하는 메소드들이 정의할때 사용한다  
* registerStompEndpoints  
클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트를 등록한다  
엔드 포인트 구성에 withSockJS ()를 사용한다  
SockJS는 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는 데 사용된다  

* `/ws`  
*/ws*는 WebSocket 클라이언트가 Handshake를 위해 연결해야하는 end-point의 url이다  
프론트에서 websocket에 접속할 때 sockJS를 사용한다면 다음과 같이 연결하게 된다  
```javascript
let socket = new SockJS('http://localhost:8080/ws')
this.stompClient = Stomp.over(socket)
```
* configureMessageBroker  
메세지 브로커 옵션을 설정한다  
* `/pub`  
*/pub*는 destination이 `/pub`으로 된 메세지를 `@Controller`의 `@MessageMapping`으로 라우팅한다  
* `/sub`  
*/sub*는 /sub로 시작하는 목적지를 가진 메세지가 메세지브로커로 라우팅한다


### 2. MessageController.java  
`MessageController.java`는 전달받은 메세지를 처리하는 컨트롤러다  


```java
@RequiredArgsConstructor
@RestController
public class MessageController {
	private final IMessageService messageService;
	private final SimpMessagingTemplate template;

	@MessageMapping("/message")
	public void sendMessage(@Payload Message chatMessage) {
		messageService.insertMessage(chatMessage); //db 저장
		template.convertAndSend("/sub/" + chatMessage.getChatroomId(), chatMessage);
	}
}
```

전달받은 메세지 프레임의 데이터(payload)는 내가 미리 선언해둔 *Message*객체로 받아온다  
받은 메세지의 형식이 다음과 같다고 할때, */sub/14*를 구독한 클라이언트에게 전달된다  
```
chatroomId : 14
content : Hi~
senderId : 2
```

### 정리  
1. 클라이언트가 `http://localhost:8080/ws`에 연결되고 WebSocket 연결이 설정되고나면 STOMP 프레임이 공급된다  
2. 클라이언트가 `/sub/message`로 SUBSCRIBE 프레임을 보낸다  
전달 및 디코드된 메세지는 *clientInboundChanner*로 전송되고 클라이언트의 구독을 보관하는 메세지 브로커로 라우팅된다  
3. 클라이언트는 SEND 프레임을 "/pub/message"로 보낸다
"/pub"접두사는 *MessageController*로, 접두사가 제거 된 후 남은 "/message"는 *@MessageMapping*에 맞춰 라우팅된다
4. *MessageController*에서 리턴된 값은 데이터(payload)와 Spring `Message`로 변환한다  
(이때 데이터(payload)는 `/sub/message`의 목적지(destination)과 리턴 값에 기반한다)  
결과 메세지는 *brokerChanner*에 보내지고 메세지브로커가 처리한다
5. 메시지 브로커는 목적지(destination)과 구독주소가 일치하는 모든 구독자를 찾고, 메시지는 STOMP 프레임으로 인코딩되어 WebSocket 연결로 전송하는 *clientOutboundChannel*을 통해 전송한다

### + Interception  
`ChannelInterceptor`등록을 통해 모든 메세지를 직접 가로챌 수 있다  
*WebSocketConfig.java*에서 하단에 메서드를 추가로 작성한다  
(spring5.0.4 docs에서 setInterceptors로 나와있는데 사용되지않는다고 나와서 대체)
```java
	@Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new MyChannelInterceptor());
    }
```
*MyChannelInterceptor*클래스 파일을 따로 작성하지않고 사용할 수 있지만 따로 분리했다  

```java
public class MyChannelInterceptor implements ChannelInterceptor{

	@Override
	public Message preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		StompCommand command = accessor.getCommand();
		if (command.compareTo(StompCommand.SUBSCRIBE) == 0) {
			String destination = accessor.getDestination();
			System.out.println("구독 주소 : " + destination);
			System.out.println(message);
		} else if (command.compareTo(StompCommand.CONNECT) == 0) {
			System.out.println("사용자 연결");
		} else if (command.compareTo(StompCommand.DISCONNECT) == 0) {
			System.out.println("사용자 연결 해제");
		}
		return message;
	}
}
```
(이또한 extends ChannelInterceptorAdapter에서 implements ChannelInterceptor로 변경)  
HeaderAccessor나 SimpMessageHeaderAccessor로 메세지 정보에 접근할 수 있다  


내 프로젝트에서는 필요하다고 생각하지않아서 출력문만 확인하는 용으로 두었다  
이때 command는 어떤 메세지 프레임인지 enum형태로 정의되어있다  
그래서 같은지 확인하기 위해 `compareTo()`를 사용했다  

다른예제들을 보면 Interceptor하는 방법보단,  
클라이언트에서 메세지를 송신할때 "type"같은 값을 넣어줘서 메세지 컨트롤러에서 주로 처리를 했다  

사실 인터셉터에서 어떻게 뭘 처리할 수 있는지, 무엇에 쓰이는 용도인지는 잘 모르겠다  
preSend외에 postSend, afterSendCompletion, preReceive등 다른 메소드들도 있다   



전체 소스 : [Github Repository](https://github.com/hongjuzzang/springboot-stomp.git)


#### 참고  
[vue spring boot 웹소켓 채팅 만들기](https://velog.io/@skyepodium/vue-spring-boot-stomp-%EC%9B%B9%EC%86%8C%EC%BC%93)  
[Spring WebSocket 소개](https://supawer0728.github.io/2018/03/30/spring-websocket/)  
[[Spring]Springboot + websocket 채팅[1]](https://ratseno.tistory.com/71)  
