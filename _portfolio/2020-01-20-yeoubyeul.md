---
title: "여우별"
excerpt: "새벽 시간에만 글을 작성할 수 있는 감성 공유 SNS"
header:
  teaser: /assets/images/port/yeoubyeol/teaser.png
toc: true
toc_sticky: true
# thumbnail : 210 * 140
gallery:
  - url: assets/images/port/yeoubyeol/zip-home-feed.png
    image_path: assets/images/port/yeoubyeol/th-home-feed.png
    alt: "메인 화면, 프로필"
  - url: assets/images/port/yeoubyeol/zip-trend.png
    image_path: assets/images/port/yeoubyeol/th-trend.png
    alt: "트렌드"
  - url: assets/images/port/yeoubyeol/zip-write.png
    image_path: assets/images/port/yeoubyeol/th-write.png
    alt: "피드 작성"
---
새벽 시간에만 글을 작성할 수 있는 감성 공유 SNS  
## 프로젝트 소개  
### 팀 구성  
 4인 팀 프로젝트
### 사용기술 및 언어    
  python 3.7.x  
  Django 3.0.x  
  MYSQL 8.0.x  
  Vue.js + vuetify  
### 개발 기간  
2020-01-20 ~ 2020-02-21(약 5주)


## 프로젝트 내용
### 주요 기능
- 새벽시간에만 글을 작성, 수정, 삭제할 수 있음  
- 텍스트 마이닝, 형태소 분석을 이용한 해쉬태그 추천  
- 날씨api에 따른 글 작성 시 배경화면 변경  
- 팔로우, 팔로워, 게시글 좋아요 기능  
- 해쉬태그 워드클라우드 기능 제공(트렌드)  
- 일정 수 좋아요 받은 게시물은 명예의 전당 등극  
- 알림센터 기능, 메인피드(무한스크롤)  

{% include gallery %}


## 담당
### 역할
front-end
### 구현
- frontend 화면 구성 및 컴포넌트 제작(유저 프로필, 게시글, 댓글, 검색 등) 
- 글 작성 페이지에서 지역 날씨(openweather api)에 따른 배경 변경  
- 알림센터, 워드클라우드, 유저 프로필, 게시글 상세 페이지 등 전체적인 프론트 구성  
- wireframe 제작
### 코드
- layout
<script src="https://gist.github.com/hongjuzzang/522c95a5feb58841796de700dd72adac.js"></script>
주로 사용했던 레이아웃으로 가운데 정렬도 되고 반응형으로 하기 편해서 대부분 저 형식을 사용했다


- api 호출(댓글 작성)  
  comment.vue
{% gist 2fc7a3c84602632d12b22e9ed439d840 %}
  CommentApi.js
{% gist ceae12e841fe7a6a0f766804bf6db2ef %}
vue 코드 하단에 **script** 부분 말고 api들은 따로 분리해서 다시 넘겨주는 방식으로 작성했다.