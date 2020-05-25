---
title:  "pandas Dataframe을 mysql에 저장하기"
excerpt: "Jupyter에서 DataFrame을 mysql에 넣기"
toc: true
toc_sticky: true
categories:
  - TIL
tags:
  - jupyter
  - pandas
  - mysql
  - TIL
last_modified_at: 2020-05-25
---
## Jupyter에서 DataFrame을 mysql에 넣기
### 필요 라이브러리 설치 
```python
!pip install pymysql
!pip install sqlalchemy
```
**아나콘다 사용 시**  
pymysql 설치 오류가 난다면, 아나콘다 프롬프트를 실행한 다음  
```
$ conda install -c anaconda pymysql
```
다음 명령어로 설치를 해줘야 한다.  
 * 아나콘다 환경에서 커널목록에 conda env가 없는 경우  
(base) 상태에서 <code>conda install nb_conda_kernels</code>  
python -m ipykernel install --user --name [env이름] --display-name [보여질 주피터커널이름]  

### 데이터베이스 연결  
```python  
from sqlalchemy import create_engine
import pymysql
import pandas as pd
db_connection_str = 'mysql+pymysql://[db유저이름]:[db password]@[host address]/[db name]'
db_connection = create_engine(db_connection_str)
conn = db_connection.connect()
```
### 데이터베이스 저장  
데이터프레임(df)을 mysql에 저장하기 !  
```python
df.to_sql(name='db의 테이블이름', con=db_connection, if_exists='append',index=False)  
```
* con : con에 연결되는 <code>db_connection</code>은 <code>create_engine()</code>를 담은 변수를 써주면 된다.  
* if_exists : [fail, replace, append] -> **테이블이 이미 존재하는 경우** 어떻게 할지에 대한 옵션  
  fail : ValueError 발생  
  append : 존재하는 테이블에 값 저장  
  append의 경우는 기존에 있는 테이블에 저장을 하는 것이여서 저장할 데이터프레임의 길이나 타입이 맞지않는다면 오류가 난다.  
  replace : 테이블 지우고 새로 생성 후 값 저장  
  replace는 새로 데이터프레임 타입에 맞춰 테이블이 생성되므로 데이터프레임 타입이 object일땐 mysql에서는 TEXT로 생겼다.  
  dtype옵션을 줘서 타입을 맞춰줄 수 있다.  
  ```python
  dtypesql = {'exclusive':sqlalchemy.types.VARCHAR(10), 
            'cost':sqlalchemy.types.VARCHAR(10), 
            'contractedAt':sqlalchemy.Date(), 
            'createdAt':sqlalchemy.DateTime(), 
}
df.to_sql(name='building', con=db_connection, if_exists='append', index=False,dtype=dtypesql)
```



## 참고
[[에러] ModuleNotFoundError: No module named 'pymysql'](https://abc2080.tistory.com/entry/에러-ModuleNotFoundError-No-module-named-pymysql)  
[[anaconda] anaconda 환경에서 jupyter 실행 시 kernel 목록에 env 목록이 보이지 않는 경우](https://redcarrot.tistory.com/228)  
[pandas.DataFrame.to_sql](https://pandas.pydata.org/pandas-docs/stable/reference/api/pandas.DataFrame.to_sql.html)
