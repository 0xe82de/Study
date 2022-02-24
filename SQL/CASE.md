# 참고

- SQL 첫걸음

# Contents

- [CASE 문](#CASE-문)
- [또 하나의 CASE 문](#또-하나의-CASE-문)
- [CASE를 사용할 경우 주의사항](#CASE를-사용할-경우-주의사항)

# CASE

## CASE 문

```sql
# CASE 문법
CASE
    WHEN condition1 THEN expression1
#   [WHEN condition2 THEN expression2 ...]
#   [ELSE expression3]
END
```

`WHEN` 절에서는 참과 거짓을 반환하는 조건식을 명시한다. 해당 조건을 만족하여 참이 되는 경우는 `THEN` 절에 명시한 식이 처리된다. 그 어떤 조건식도 만족하지 못한 경우 `ELSE` 절에 명시한 식이 처리된다. `ELSE`는 생략할 수 있으며, 생략된 경우 `ELSE NULL`로 간주된다.

```sql
# age 값이 NULL이면 0으로 반환하고, NULL이 아니면 age 값 자체를 반환한다.
SELECT
	CASE WHEN age IS NULL THEN 0
		ELSE age
	END AS age
FROM tb_table;
```

`COALESCE` 함수로도 `NULL` 처리를 할 수 있다.

```sql
# age가 NULL이면 0을 반환하고, 그렇지 않으면 age 값 자체를 반환한다.
SELECT COALESCE(age, 0)
FROM tb_table;
```

## 또 하나의 CASE 문

```sql
# 단순 CASE 문법
CASE expression1
    WHEN expression2 THEN expression3
    [WHEN expression4 THEN expression5]
    [ELSE expression6]
END
```

위 문법에서는 `expression1`의 갑이 `WHEN` 절의 `expression2`의 값과 동일한지 비교해서 같다면 `expression3`의 값이 반환된다.

```sql
# 검색 CASE
# gender_code에 따라서 남자, 여자, 미지정으로 표시한다.
SELECT
	CASE
		WHEN gender_code = 1 THEN '남자'
        when gender_code = 2 THEN '여자'
        ELSE '미지정'
	END AS "성별"
FROM tb_table;
```

```sql
# 단순 CASE
# gender_code에 따라서 남자, 여자, 미지정으로 표시한다.
SELECT
	CASE gender_code
		WHEN 1 THEN '남자'
        WHEN 2 THEn '여자'
        ELSE '미지정'
	END AS "성별"
FROm tb_table;
```

## CASE를 사용할 경우 주의사항

- `CASE` 문은 `SELECT` 구를 포함하여 어디에서나 사용될 수 있다. `WHERE` 구에서 조건식의 일부로 사용될 수 있고 `ORDER BY` 구나 `SELECT` 구에서도 사용할 수 있다.
- `ELSE`를 생략하면 `ELSE NULL`이 되는 것에 주의해야 한다. 대체로 `ELSE`를 생략하지 않고 지정하는 편이 낫다.
- 단순 `CASE`에서는 `WHEN` 뒤에 1개의 상수값을 지정하는 경우가 많다. 이 때 데이터가 `NULL`인 경우를 고려하여 `WHEN NULL THEN '데이터 없음'`과 같이 지정해도 처리되지 않는다. 왜냐하면 `NULL`은 `=` 연산자로 비교할 수 없기 때문이고, 단순 `CASE`에서는 상수값을 `=` 연산자로 비교한다. 따라서, 데이터가 `NULL` 값인지를 판정하기 위해서는 단순 `CASE`가 아닌 검색 `CASE`를 사용해야 한다.
  ```sql
  CASE
      WHEN gender_code = 1 THEN '남자'
      WHEN gender_code = 2 THEN '여자'
      WHEN gender_code IS NULL THEN '데이터 없음'
      ELSE '미지정'
  END
  ```
- `Oracle`에는 디코드를 수행하는 `DECODE` 함수가 존재하는데, 이 함수는 `CASE` 문과 같은 용도로 사용할 수 있다. 하지만 `Oracle`에서만 지원하는 함수이다. `CASE` 문의 경우 표준 `SQL`로 규정되었기 때문에 많은 데이터베이스 제품에서 지원한다. 또한 `NULL` 값을 변환하는 함수로 `Oracle`은 `NVL` 함수, `SQL Server`은 `ISNULL` 함수를 지원한다. 하지만 이 함수들은 특정 데이터베이스 제품에서만 지원하는 함수이므로 `NULL` 값을 변환할 때는 표준 `SQL`로 규정된 `COALESCE` 함수를 사용하자.
