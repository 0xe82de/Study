# 참고

- SQL 첫걸음

# Contents

- [수치 연산](#수치-연산)
- [문자열 연산](#문자열-연산)
- [날짜 연산](#날짜-연산)

# Calculate(연산)

`SQL`은 연산 기능을 포함하고 있다.

## 수치 연산

### 사칙 연산

| 연산자 | 예시         |
| ------ | ------------ |
| +      | 1 + 2 -> 3   |
| -      | 1 - 2 -> -1  |
| \*     | 1 \* 2 -> 2  |
| /      | 1 / 2 -> 0.5 |
| %      | 1 % 2 -> 1   |

각 연산자의 우선순위는 다음과 같다. 같은 그룹 내에서의 우선순위는 동일하다. 우선순위가 같은 경우 식의 왼쪽에서 오른쪽으로 계산한다.

```text
1. - / %
2. + -
```

### SELECT 구 연산

`SELECT` 구에 연산식을 지정할 수 있다. 이 때 열의 별명을 변경할 수 있는데, `AS` 키워드를 사용하면 된다. `ASCII` 문자 이외의 것을 사용할 경우 `"` 더블쿼트로 묶어서 지정해야 한다. 예를 들면 한글이 있다.

```sql
# 가격과 수량을 곱하여 표시한다.
# 이 때 열의 이름을 "금액" 으로 표시한다.
SELECT price * quantity AS "금액"
FROM tb_table;
```

### WHERE 구 연산

`WHERE` 구에서도 연산식을 이용할 수 있다.

```sql
# 가격와 수량을 곱한 금액이 10000 이상인 행을 표시한다.
SELECT price * quantity AS amount
FROM tb_table
WHERE price * quantity >= 10000;
# WHERE amount >= 10000; # 에러가 발생한다.
```

위 `SQL`의 `WHERE` 구에서 별명 `amount`를 사용하면 에러가 발생하는데, 그 이유는 데이터베이스 서버 내부에서 `WHERE` 구를 처리하고 `SELECT` 구를 처리하기 때문이다. 별명은 `SELECT` 구를 처리할 때 지정되므로 `WHERE` 구를 처리할 때는 별명이 아직 지정되지 않은 것이다. 따라서, 에러가 발생한다.

### NULL 값의 연산

`SQL` 에서는 `NULL + 1` 연산 결과가 `NULL`이다. 또한, 다음 연산들의 결과도 모두 `NULL`이다.

- `NULL + 1`
- `1 + NULL`
- `1 + 2 * NULL`
- `1 / NULL`

### ORDER BY 구 연산

`ORDER BY` 구에서 연산을 수행하고 결과값으로 정렬할 수 있다. `WHERE` 구 연산과 다른 점은 `ORDER BY` 구에서는 별명을 사용할 수 있다는 점이다. `ORDER BY`는 내부적으로 가장 마지막에 처리된다. 따라서 `SELECT` 구가 처리되었으므로 지정된 별명 `amount`를 사용할 수 있다.

```sql
# ORDER BY 구에서 가격과 수량을 곱하고 반환된 결과값으로 정렬한다.
SELECT price * quantity AS amount
FROM tb_table
ORDER BY amount DESC;
```

### 함수

연산자 외에 함수를 사용해 연산할 수 있다.

```sql
# 모든 행의 price를 더한 값을 표시한다.
SELECT SUM(price)
FROM tb_table;
```

```sql
# 원본 amount 값과 소수점 첫 번째 자리까지 반올림한 amount 값을 표시한다.
SELECT amount, ROUND(amount, 1)
WHERE tb_table;
```

```sql
# 원본 amount 값과 100의 자리수까지 반올림한 amount 값을 표시한다.
SELECT amount, ROUND(amount, -2)
WHERE tb_table;
```

## 문자열 연산

### 문자열 결합

| 연산자/함수 | 데이터베이스             |
| ----------- | ------------------------ |
| +           | SQL Server               |
| \|\|        | Oracle, DB2, PostgreSSQL |
| CONCAT      | MySQl                    |

```sql
# 수량과 수량 단위를 결합하여 표시한다.
SELECT CONCAT(quantity, unit)
FROM tb_table;
```

위 `SQL`에서 `quantity`는 `INTEGER`형이지만 문자열형과 결합할 경우 문자열형이 된다.

### SUBSTRING 함수

```sql
# 4자리 연 정보만 표시한다.
SELECT SUBSTRING('20220224', 1, 4); # 2022
```

### TRIM 함수

문자열 앞뒤로 여분의 공백이 있을 경우 이를 제거한다.

```sql
# 문자열 앞뒤로 있는 공백을 제거하고 표시한다.
SELECT TRIM('   WORD   '); # -> WORD
```

### CHARACTER_LENGTH 함수

문자열의 길이를 계산해서 반환한다.

```sql
# 주소의 길이를 계산해서 표시한다.
SELECT CHARACTER_LENGTH(address)
FROM tb_table;
```

## 날짜 연산

날짜, 시간을 저장하는 방법은 데이터베이스 제품에 따라 달라진다. 날짜와 시간을 모두 저장하는 자료형을 지원하거나 날짜와 시간을 각각 저장하는 자료형을 지원하기도 한다.

### SQL에서의 날짜

```sql
# 현재 시간을 표시한다.
SELECT CURRENT_TIMESTAMP;
```

### 날짜의 덧셈과 뺄셈

날짜시간형 데이터는 기간형 수치데이터와 덧벰 및 뺄셈을 할 수 있다.날짜시간형 데이터에 기간형 수치데이터를 더하거나 빼면 날짜시간형 데이터가 반환된다.

```sql
# 현재 날짜에서 하루 뒤 날짜를 표시한다.
SELECT CURRENT_DATE + INTERVAL 1 DAY;
```

```sql
# 두 날짜 데이터 간에 뺄셈을 수행하고 결과를 표시한다.
SELECT DATEDIFF('2022-02-24', '2022-02-01');
```
