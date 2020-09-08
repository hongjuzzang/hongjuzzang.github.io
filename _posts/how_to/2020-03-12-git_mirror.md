---
title:  "git repository mirroring"
excerpt: "Gitlab에서 github으로 커밋이력까지 함께 복사하기"
toc: true
toc_sticky: true
categories:
  - HowTo
tags:
  - git
  - repository
  - HowTo
last_modified_at: 2020-09-09
---
## Gitlab에서 github으로 커밋이력까지 함께 복사하기  
Gitlab에서 하던 프로젝트를 Github으로 옮겨오는데  
커밋 로그를 포기할 순 없지..  

Gitlab에서 Github으로 옮기는 방법이지만  
Github에서 Github도 가능(아마도)  

### Repository A를 Repository B로 옮기기  
1. git bash를 연다  
2. A를 clone받기  
```
$ git clone --bare [A주소]
```
3. 폴더 이동하고 B에 푸시하기
```
$ cd [A 폴더]
$ git push --mirror [B주소]
```
4. 임시 로컬 저장소A를 지우기
```
$ cd ..
$ rm -rf [A 폴더]
```



### lfs를 이용하여 리파짓토리 미러링하기  
github의 경우 100MB 이상의 커밋은 위의 방법으로 옮길 수 없다  
LFS를 사용해서 용량이 큰 커밋이력을 옮기는 방법은 다음과 같다  


1. git bash를 연다  
2. A를 clone받기  
```
$ git clone --bare https://[A주소]
```
3. 폴더 이동
```
$ cd [A주소.git]
```
4. Pull in the repository's git large file storage objects
```
$ git lfs fetch --all
```
5. 새 리파짓토리로 mirror-push
```
$ git push --mirror https://[B주소]
```
6. 큰 파일 푸시푸시
```
$ git lfs push --all https://[B 주소]
```
7. 이전에 A 폴더 지우기
```
$ cd ..
$ rm -rf [A폴더]
```


### BFG Repo-Cleaner 사용하기
위의 방법에서 `remote rejected`같은 오류가 난다면..  


검색하니까 커밋이력 중에서도 100MB가 넘는 파일이 있기 때문이라고한다(github에 올릴때)  
커밋이력에도 100MB가 넘는 파일이 있는 경우 + 100MB넘는 파일이 있을 때 방법은 다음과 같다  


1. A를 clone을 bare로 받는다
```
$ git clone --mirror [A.git]
```
2. A.git 폴더와 같은 경로에 [BFG RepoCleaner](https://rtyley.github.io/bfg-repo-cleaner/) 을 다운받는다.  
```
$ ls
A.git  bfg-1.13.0.jar
```
3. A.git폴더에서 100mb넘는 것을 제거(저장소 정리)  
```
$ java -jar bfg.jar --strip-blobs-bigger-than 100M [A.git]
```
그런데 위의 방법은 커밋, 브랜지, 태그를 업데이트하지만 물리적으로 삭제하지는 않는다.
실제로 삭제하려면
```
$ cd [A.git]
$ git reflog expire --expire=now --all && git gc --prune=now --aggressive
$ git push # push하면 변경된 상태를 remote repository에도 업데이트
```
4. github에 올리기  
 <code> git remote -v </code>로 해당 리파짓토리에 연결된 원격 저장소를 확인할 수 있다.  
 ```
 $ git remote add [remote이름 : github][올릴 github repository 주소 : https://github.com/hongjuzzang/B.git]
 ```
 원격 저장소를 추가한 다음 소스 밀어넣기
 ```
 $ git push github
 ```



### 참고
[Duplicating a repository](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/duplicating-a-repository)  
[BFG RepoCleaner](https://rtyley.github.io/bfg-repo-cleaner/)  
[Github에 100MB 이상의 파일을 올리는 방법](https://medium.com/@stargt/github%EC%97%90-100mb-%EC%9D%B4%EC%83%81%EC%9D%98-%ED%8C%8C%EC%9D%BC%EC%9D%84-%EC%98%AC%EB%A6%AC%EB%8A%94-%EB%B0%A9%EB%B2%95-9d9e6e3b94ef)