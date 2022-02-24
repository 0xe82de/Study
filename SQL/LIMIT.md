# 참고

- SQL 첫걸음

# Contents

- [정렬 후 행수 제한](#정렬-후-행수-제한)
- [SQL Server, Oracle의 행수 제한](#SQL-Server-Oracle의-행수-제한)
- [OFFSET 지정](#OFFSET-지정)

# LIMIT

`LIMIT` 구로 결과로 반환되는 행을 제한할 수 있다.

```sql
SELECt *
FROM tb_table
LIMIT 행수 [OFFSET 시작행]
```

`LIMIT` 구는 표준 `SQL`이 아니다. `MySQL`과 `PostgreSQL`에서 사용할 수 있는 문법이다.

## 정렬 후 행수 제한

정렬 후에 행수를 제한할 수도 있다.

```sql
# 내림차순으로 정렬한 후에 상위 3개 행만 표시한다.
SELECT *
FROM tb_table
ORDER BY seq DESC
LIMIT 3;
```

## SQL Server, Oracle의 행수 제한

`SQL Server`에서는 행수 제한을 위해 `TOP`을 사용할 수 있다.

```sql
SELECT TOP 3 *
FROM tb_table;
```

`Oracle`에서는 `ROWNUM`을 `WHERE` 구로 지정하여 행수를 제한할 수 있다. 단, `ROWNUM`으로 행수를 제한할 때는 `WHERE` 구로 지정하므로 정렬하기 전에 처리되어 `LIMIT` 구로 행수를 제한한 경우와 결과가 다를 수 있다.

```sql
SELECT *
FROM tb_table
WHERE ROWNUM <= 3;
```

## OFFSET 지정

`LIMIT`, 구와 `OFFSET`을 사용하여 `Pagination`을 구현할 수 있다.

```sql
# 첫 번째 데이터부터 3개의 행을 표시한다.
SELECT *
FROM tb_table
LIMIT 3 OFFSET 0;

# 세 번째 데이터부터 3개의 행을 표시한다.
SELECT *
FROM tb_table
LIMIT 3 OFFSET 3;
```
