# Scanner & BufferedReader

## Scanner

- 데이터의 형변환을 자동으로 해주므로 편리하다.
- 사용하기 간편하다.
- 대량의 데이터를 처리할 때 수행시간이 비효율적이다.

### Scanner Example

```Java
import java.util.Scanner;

private static void scannerTest() {

	Scanner sc = new Scanner(System.in);

	// 정수 1개 입력 (구분자 -> 없음)
	System.out.print("정수 1개 입력 (구분자 -> 없음) | ex) 1 : ");
	int in = sc.nextInt();

	// 정수 n개 입력 (구분자 -> 없음) (범위 -> 0~9)
	System.out.print("정수 n개 입력 (구분자 -> 없음) (범위 -> 0~9) | ex) 23 : ");
	char[] chars = sc.next().toCharArray();
	int[] intArr1 = new int[chars.length];
	for (int i = 0; i < intArr1.length; ++i) {
		intArr1[i] = chars[i] - '0';
	}
	sc.nextLine();

	// 정수 n개 입력 (구분자 -> 공백)
	System.out.print("정수 n개 입력 (구분자 -> 공백) | ex) 4 5 : ");
	StringTokenizer st1;
	st1 = new StringTokenizer(sc.nextLine(), " ");
	int[] intArr2 = new int[st1.countTokens()];
	for (int i = 0; i < intArr2.length; ++i) {
		intArr2[i] = Integer.parseInt(st1.nextToken());
	}

	// 실수 1개 입력 (구분자 -> 없음)
	System.out.print("실수 1개 입력 (구분자 -> 없음) | ex) 6.1 : ");
	double db = sc.nextDouble();
	sc.nextLine(); // 끝에 Enter Clear

	// 실수 n개 입력 (구분자 -> 공백)
	System.out.print("실수 n개 입력 (구분자 -> 공백) | ex) 6.2 6.3 : ");
	StringTokenizer st2;
	st2 = new StringTokenizer(sc.nextLine(), " ");
	double[] doubleArr = new double[st2.countTokens()];
	for (int i = 0; i < doubleArr.length; ++i) {
		doubleArr[i] = Double.parseDouble(st2.nextToken());
	}

	// 문자 1개 입력 (구분자 -> 없음)
	System.out.print("문자 1개 입력 (구분자 -> 없음) | ex) a : ");
	char ch = sc.nextLine().charAt(0);

	// 문자 n개 입력 (구분자 -> 없음)
	System.out.print("문자 n개 입력 (구분자 -> 없음) | ex) bc : ");
	char[] charArr1 = sc.nextLine().toCharArray();

	// 문자 n개 입력 (구분자 -> 공백)
	System.out.print("문자 n개 입력 (구분자 -> 공백) | ex) d e : ");
	StringTokenizer st3;
	st3 = new StringTokenizer(sc.nextLine(), " ");
	char[] charArr2 = new char[st3.countTokens()];
	for (int i = 0; i < charArr2.length; ++i) {
		charArr2[i] = st3.nextToken().charAt(0);
	}

	// 문자열 1개 입력 (구분자 -> 없음)
	System.out.print("문자열 1개 입력 (구분자 -> 없음) | ex) fg : ");
	String str = sc.nextLine();

	// 문자열 n개 입력 (구분자 -> 공백, 공백을 포함하지 않는 문자열)
	System.out.print("문자열 n개 입력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) | ex) hi jk : ");
	StringTokenizer st4;
	st4 = new StringTokenizer(sc.nextLine());
	String[] stringArr1 = new String[st4.countTokens()];
	for (int i = 0; i < stringArr1.length; ++i) {
		stringArr1[i] = st4.nextToken();
	}

	// 문자열 n개 입력 (구분자 -> Enter, 공백을 포함하는 문자열)
	System.out.print("문자열 n개 입력 (구분자 -> Enter, 공백을 포함하는 문자열) | ex) 2{입력할 문자열의 개수} (Enter) l m (Enter) n o : ");
	int len = sc.nextInt();
	sc.nextLine(); // 끝에 Enter Clear
	String[] stringArr2 = new String[len];
	for (int i = 0; i < len; ++i) {
		stringArr2[i] = sc.nextLine();
	}

	sc.close();

	System.out.println();

	// 정수 1개 출력 (구분자 -> 없음)
	System.out.println("정수 출력 (구분자 -> 없음) : " + in);

	// 정수 n개 출력 (구분자 -> 없음)
	System.out.print("정수 n개 출력 (구분자 -> 없음) : ");
	for (int i = 0; i < intArr1.length; ++i) {
		System.out.print(intArr1[i] + " ");
	}
	System.out.println();

	// 정수 n개 출력 (구분자 -> 공백)
	System.out.print("정수 n개 출력 (구분자 -> 공백) : ");
	for (int i = 0; i < intArr2.length; ++i) {
		System.out.print(intArr2[i] + " ");
	}
	System.out.println();

	// 실수 1개 출력 (구분자 -> 없음)
	System.out.println("실수 1개 출력  (구분자 -> 없음) : " + db);

	// 실수 n개 출력 (구분자 -> 공백)
	System.out.print("실수 n개 출력 (구분자 -> 공백) : ");
	for (int i = 0; i < doubleArr.length; ++i) {
		System.out.print(doubleArr[i] + " ");
	}
	System.out.println();

	// 문자 1개 출력 (구분자 -> 없음)
	System.out.println("문자 1개 출력 (구분자 -> 없음) : " + "\'" + ch + "\'");

	// 문자 n개 출력 (구분자 -> 없음)
	System.out.print("문자 n개 출력 (구분자 -> 공백) : ");
	for (int i = 0; i < charArr1.length; ++i) {
		System.out.print("\'" + charArr1[i] + "\'" + " ");
	}
	System.out.println();

	// 문자 n개 출력 (구분자 -> 공백)
	System.out.print("문자 n개 출력 (구분자 -> 공백) : ");
	for (int i = 0; i < charArr2.length; ++i) {
		System.out.print("\'" + charArr2[i] + "\'" + " ");
	}
	System.out.println();

	// 문자열 1개 출력 (구분자 -> 없음)
	System.out.println("문자열 1개 출력 (구분자 -> 없음) : " + "\"" + str + "\"");

	// 문자열 n개 출력 (구분자 -> 공백, 공백을 포함하지 않는 문자열)
	System.out.print("문자열 n개 출력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) : ");
	for (int i = 0; i < stringArr1.length; ++i) {
		System.out.print("\"" + stringArr1[i] + "\"" + " ");
	}
	System.out.println();

	// 문자열 n개 출력 (구분자 -> Enter, 공백을 포함하는 문자열)
	System.out.print("문자열 n개 출력 (구분자 -> Enter, 공백을 포함하는 문자열) : ");
	for (int i = 0; i < stringArr2.length; ++i) {
		System.out.print("\"" + stringArr2[i] + "\"" + " ");
	}
}

/* 입출력

정수 1개 입력 (구분자 -> 없음) | ex) 1 : 1
정수 n개 입력 (구분자 -> 없음) (범위 -> 0~9) | ex) 23 : 23
정수 n개 입력 (구분자 -> 공백) | ex) 4 5 : 4 5
실수 1개 입력 (구분자 -> 없음) | ex) 6.1 : 6.1
실수 n개 입력 (구분자 -> 공백) | ex) 6.2 6.3 : 6.2 6.3
문자 1개 입력 (구분자 -> 없음) | ex) a : a
문자 n개 입력 (구분자 -> 없음) | ex) bc : bc
문자 n개 입력 (구분자 -> 공백) | ex) d e : d e
문자열 1개 입력 (구분자 -> 없음) | ex) fg : fg
문자열 n개 입력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) | ex) hi jk : hi jk
문자열 n개 입력 (구분자 -> Enter, 공백을 포함하는 문자열) | ex) 2{입력할 문자열의 개수} (Enter) l m (Enter) n o : 2
l m
n o

정수 출력 (구분자 -> 없음) : 1
정수 n개 출력 (구분자 -> 없음) : 2 3
정수 n개 출력 (구분자 -> 공백) : 4 5
실수 1개 출력  (구분자 -> 없음) : 6.1
실수 n개 출력 (구분자 -> 공백) : 6.2 6.3
문자 1개 출력 (구분자 -> 없음) : 'a'
문자 n개 출력 (구분자 -> 공백) : 'b' 'c'
문자 n개 출력 (구분자 -> 공백) : 'd' 'e'
문자열 1개 출력 (구분자 -> 없음) : "fg"
문자열 n개 출력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) : "hi" "jk"
문자열 n개 출력 (구분자 -> Enter, 공백을 포함하는 문자열) : "l m" "n o"

*/
```

## BufferedReader

- 대량의 데이터를 처리할 때 수행시간이 효율적이다.
- 출력할 데이터의 형변환을 해줘야 한다.
- Scanner보다 사용하기 불편하다.

### BufferedReader Example

```Java
private static void bufferedReaderTest() throws IOException {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	// 정수 1개 입력 (구분자 -> 없음)
	bw.write("정수 1개 입력 (구분자 -> 없음) | ex) 1 : ");
	bw.flush();
	int in = Integer.parseInt(br.readLine());

	// 정수 n개 입력 (구분자 -> 없음) (범위 -> 0~9)
	bw.write("정수 n개 입력 (구분자 -> 없음) (범위 -> 0~9) | ex) 23 : ");
	bw.flush();
	char[] chars = br.readLine().toCharArray();
	int[] intArr1 = new int[chars.length];
	for (int i = 0; i < intArr1.length; ++i) {
		intArr1[i] = chars[i] - '0';
	}

	// 정수 n개 입력 (구분자 -> 공백)
	bw.write("정수 n개 입력 (구분자 -> 공백) | ex) 4 5 : ");
	bw.flush();
	StringTokenizer st1;
	st1 = new StringTokenizer(br.readLine(), " ");
	int[] intArr2 = new int[st1.countTokens()];
	for (int i = 0; i < intArr2.length; ++i) {
		intArr2[i] = Integer.parseInt(st1.nextToken());
	}

	// 실수 1개 입력 (구분자 -> 없음)
	bw.write("실수 1개 입력 (구분자 -> 없음) | ex) 6.1 : ");
	bw.flush();
	double db = Double.parseDouble(br.readLine());

	// 실수 n개 입력 (구분자 -> 공백)
	bw.write("실수 n개 입력 (구분자 -> 공백) | ex) 6.2 6.3 : ");
	bw.flush();
	StringTokenizer st2;
	st2 = new StringTokenizer(br.readLine(), " ");
	double[] doubleArr = new double[st2.countTokens()];
	for (int i = 0; i < doubleArr.length; ++i) {
		doubleArr[i] = Double.parseDouble(st2.nextToken());
	}

	// 문자 1개 입력 (구분자 -> 없음)
	bw.write("문자 1개 입력 (구분자 -> 없음) | ex) a : ");
	bw.flush();
	char ch = br.readLine().charAt(0);

	// 문자 n개 입력 (구분자 -> 없음)
	bw.write("문자 n개 입력 (구분자 -> 없음) | ex) bc : ");
	bw.flush();
	char[] charArr1 = br.readLine().toCharArray();

	// 문자 n개 입력 (구분자 -> 공백)
	bw.write("문자 n개 입력 (구분자 -> 공백) | ex) d e : ");
	bw.flush();
	StringTokenizer st3;
	st3 = new StringTokenizer(br.readLine());
	char[] charArr2 = new char[st3.countTokens()];
	for (int i = 0; i < charArr2.length; ++i) {
		charArr2[i] = st3.nextToken().charAt(0);
	}

	// 문자열 1개 입력 (구분자 -> 없음)
	bw.write("문자열 1개 입력 (구분자 -> 없음) | ex) fg : ");
	bw.flush();
	String str = br.readLine();

	// 문자열 n개 입력 (구분자 -> 공백, 공백을 포함하지 않는 문자열)
	bw.write("문자열 n개 입력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) | ex) hi jk : ");
	bw.flush();
	StringTokenizer st4;
	st4 = new StringTokenizer(br.readLine());
	String[] stringArr1 = new String[st4.countTokens()];
	for (int i = 0; i < stringArr1.length; ++i) {
		stringArr1[i] = st4.nextToken();
	}

	// 문자열 n개 입력 (구분자 -> Enter, 공백을 포함하는 문자열)
	bw.write("문자열 n개 입력 (구분자 -> Enter, 공백을 포함하는 문자열) | ex) 2{입력할 문자열의 개수} (Enter) l m (Enter) n o : ");
	bw.flush();
	int len = Integer.parseInt(br.readLine());
	String[] stringArr2 = new String[len];
	for (int i = 0; i < stringArr2.length; ++i) {
		stringArr2[i] = br.readLine();
	}

	br.close();

	bw.newLine();

	// 정수 1개 출력 (구분자 -> 없음)
	bw.write("정수 1개 출력 (구분자 -> 없음) : " + String.valueOf(in));
	bw.newLine();
	bw.flush();

	// 정수 n개 출력 (구분자 -> 없음)
	bw.write("정수 n개 출력 (구분자 -> 없음) : ");
	for (int i = 0; i < intArr1.length; ++i) {
		bw.write(String.valueOf(intArr1[i]) + " ");
	}
	bw.newLine();
	bw.flush();

	// 정수 n개 출력 (구분자 -> 공백)
	bw.write("정수 n개 출력 (구분자 -> 공백) : ");
	for (int i = 0; i < intArr2.length; ++i) {
		bw.write(String.valueOf(intArr2[i]) + " ");
	}
	bw.newLine();
	bw.flush();

	// 실수 1개 출력 (구분자 -> 없음)
	bw.write("실수 1개 출력 (구분자 -> 없음) : " + String.valueOf(db));
	bw.newLine();
	bw.flush();

	// 실수 n개 출력 (구분자 -> 공백)
	bw.write("실수 n개 출력 (구분자 -> 공백) : ");
	for (int i = 0; i < doubleArr.length; ++i) {
		bw.write(String.valueOf(doubleArr[i]) + " ");
	}
	bw.newLine();
	bw.flush();

	// 문자 1개 출력 (구분자 -> 없음)
	bw.write("문자 1개 출력 (구분자 -> 없음) : " + "\'" + String.valueOf(ch) + "\'");
	bw.newLine();
	bw.flush();

	// 문자 n개 출력 (구분자 -> 없음)
	bw.write("문자 n개 출력 (구분자 -> 없음) : ");
	for (int i = 0; i < charArr1.length; ++i) {
		bw.write("\'" + charArr1[i] + "\'" + " ");
	}
	bw.newLine();
	bw.flush();

	// 문자 n개 출력 (구분자 -> 공백)
	bw.write("문자 n개 출력 (구분자 -> 공백) : ");
	for (int i = 0; i < charArr2.length; ++i) {
		bw.write("\'" + charArr2[i] + "\'" + " ");
	}
	bw.newLine();
	bw.flush();

	// 문자열 1개 출력 (구분자 -> 없음)
	bw.write("문자열 1개 출력 (구분자 -> 없음) : " + "\"" + str + "\"");
	bw.newLine();
	bw.flush();

	// 문자열 n개 출력 (구분자 -> 공백, 공백을 포함하지 않는 문자열)
	bw.write("문자열 n개 출력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) : ");
	for (int i = 0; i < stringArr1.length; ++i) {
		bw.write("\"" + stringArr1[i] + "\"" + " ");
	}
	bw.newLine();
	bw.flush();

	// 문자열 n개 출력 (구분자 -> Enter, 공백을 포함하는 문자열)
	bw.write("문자열 n개 출력 (구분자 -> Enter, 공백을 포함하는 문자열) : ");
	for (int i = 0; i < stringArr2.length; ++i) {
		bw.write("\"" + stringArr2[i] + "\"" + " ");
	}
	bw.flush();

	bw.close();
}

/* 입출력

정수 1개 입력 (구분자 -> 없음) | ex) 1 : 1
정수 n개 입력 (구분자 -> 없음) (범위 -> 0~9) | ex) 23 : 23
정수 n개 입력 (구분자 -> 공백) | ex) 4 5 : 4 5
실수 1개 입력 (구분자 -> 없음) | ex) 6.1 : 6.1
실수 n개 입력 (구분자 -> 공백) | ex) 6.2 6.3 : 6.2 6.3
문자 1개 입력 (구분자 -> 없음) | ex) a : a
문자 n개 입력 (구분자 -> 없음) | ex) bc : bc
문자 n개 입력 (구분자 -> 공백) | ex) d e : d e
문자열 1개 입력 (구분자 -> 없음) | ex) fg : fg
문자열 n개 입력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) | ex) hi jk : hi jk
문자열 n개 입력 (구분자 -> Enter, 공백을 포함하는 문자열) | ex) 2{입력할 문자열의 개수} (Enter) l m (Enter) n o : 2
l m
n o

정수 1개 출력 (구분자 -> 없음) : 1
정수 n개 출력 (구분자 -> 없음) : 2 3
정수 n개 출력 (구분자 -> 공백) : 4 5
실수 1개 출력 (구분자 -> 없음) : 6.1
실수 n개 출력 (구분자 -> 공백) : 6.2 6.3
문자 1개 출력 (구분자 -> 없음) : 'a'
문자 n개 출력 (구분자 -> 없음) : 'b' 'c'
문자 n개 출력 (구분자 -> 공백) : 'd' 'e'
문자열 1개 출력 (구분자 -> 없음) : "fg"
문자열 n개 출력 (구분자 -> 공백, 공백을 포함하지 않는 문자열) : "hi" "jk"
문자열 n개 출력 (구분자 -> Enter, 공백을 포함하는 문자열) : "l m" "n o"

*/
```
