---
title:  "jekyll blog theme(minimal-mistakes)에 utterances 댓글기능 추가하기"
excerpt: "minimal-mistakes에 utterances 댓글기능 추가하기 A to Z"
toc: true
toc_sticky: true
categories:
  - HowTo
tags:
  - GithubBlog
  - Jekyll
  - HowTo
---
## minimal-mistakes에 utterances 댓글기능 추가하기 A to Z  
보통 disqus를 많이쓰는데 github issue를 이용한 utterances를 이용해 댓글 기능을 추가해보자  
github issue에 저장되고 가볍다는 장점이 있다.  

### utterances 적용  
[https://github.com/apps/utterances](https://github.com/apps/utterances) 에서 install을 한다.  
이때 전체 리파짓토리 대상, 선택한 리파짓토리 대상으로 할 수 있다  
![img](/assets/images/post/200625-1.JPG)

### 옵션 설정  
install 후 이동하는 페이지에서는 옵션들을 선택하고 
밑에 *Enable Utteraces*에서 script를 복사해 post의 layout에 넣어주는 거지만,  
minimal-mistakes 테마에서는 이 때 옵션들을 config.yml에 넣어주면 된다.  
상단 하늘색상자에는 이슈와 페이지를 url, pathname, title같이 어떤방식으로 연결지을건지 선택하는 부분이다.  


![img](/assets/images/post/200625-2.JPG)

선택한 옵션을 기반으로 만들어진 script부분에서(이미지 하단 보라색상자) issue-term, theme을 기억해서 config.yml에 넣자  
```
<script src="https://utteranc.es/client.js"
        repo="[ENTER REPO HERE]"
        issue-term="pathname"
        theme="github-light"
        crossorigin="anonymous"
        async>
</script>
```

### config.yml 수정  
* repository 등록하기  
```
repository               : "hongjuzzang/hongjuzzang.github.io" # GitHub username/repo-name e.g. "mmistakes/minimal-mistakes"
```

* comments.provider 이용하기  
_config.yml에서 repository 하단에 조금 내리다보면 *utterances*를 설정하는 부분이 있다.  
이곳에 아까 script생성되었던 옵션들을 넣어주면 된다.    
```
comments:
  provider: "utterances"
  utterances:
    theme: "github-light" # "github-dark"
    issue_term: "pathname"
```


### pathname 시 유의사항    
pathname으로 지정을 해줬는데, pathname은 포스트 md파일의 이름으로 연결됨  
즉, <code>2020-07-23-postname.md</code>가 내가 새로 작성한 포스트라면,  
블로그에서 해당 포스트의 주소는 <code>hongjuzzang.github.io/postname</code>으로 연결된다  
그렇기때문에 이미 발행한 포스트md파일의 이름을 다른이름으로 바꾸면, pathname과 댓글이 연결되어있으므로  
전에 달렸던 댓글은 사라짐 !(ㅠㅠ)  -> blog repository에서 issue 변경해야함  


## 참고  
[Jekyll 블로그에 utterances로 댓글 기능 추가하기](https://madplay.github.io/post/jekyll-blog-comments-with-utterances)  
[Configuration](https://mmistakes.github.io/minimal-mistakes/docs/configuration/#utterances-comments)