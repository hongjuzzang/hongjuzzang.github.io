---
title:  "read mysql table to pandas Dataframe"
excerpt: "Jupyter에서 mysql 데이터읽어서 DataFrame으로 만들기"
toc: true
toc_sticky: true
categories:
  - HowTo
tags:
  - jupyter
  - pandas
  - mysql
  - HowTo
last_modified_at: 2020-05-06
---
## jupyter에서 mysql테이블을 dataframe으로 만들기  


```python  
from sqlalchemy import create_engine
import pymysql
import pandas as pd
db_connection_str = 'mysql+pymysql://[db유저이름]:[db password]@[host address]/[db name]'
db_connection = create_engine(db_connection_str)
conn = db_connection.connect()
df = pd.read_sql('SELECT * FROM api_storereview', con=conn) # 여기서 sql문, 나는 api_storereview 테이블을 dataframe으로 전환
conn.close() # 커넥션 끊기
```


## sqlalchemy(번역)  
**SQLAlchemy**는 python SQL 툴킷이며 sql의 유연성과 큰 힘을 개발자에게 제공하는 객체 관계형 맵퍼이다.  
![structure](https://docs.sqlalchemy.org/en/13/_images/sqla_engine_arch.png)  
Engine : 어느 sqlalchemy application의 시작점  
실제 데이터베이스와 해당 데이터베이스의 DB API를 위한 home base  
datase와 dbapi 조합으로 connection pool과 Dialect를 통해서 sqlalchemy application으로 전달된다 


## 참고
[SQLAlchemy 1.3 Documentation](https://docs.sqlalchemy.org/en/13/core/engines.html)  
[sqlalchemy 시작하기](https://riptutorial.com/ko/sqlalchemy)