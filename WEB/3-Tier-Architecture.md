# 3 Tier Architecture

## 장점

- 각 계층은 독립된 OS, Server에서 실행될 수 있습니다.
- 특정 계층을 독립적으로 확장할 수 있습니다.
- 특정 계층의 장애가 다른 계층에 크게 영향을 끼치지 않습니다.
- Presentation 계층과 Data 게층은 직접 통신할 수 없으므로 중간의 Application 계층이 방화벽의 역할을 수행하여 악의적인 행위를 방지할 수 있습니다.

## Presentation 계층 (Web Server)

- Presentation 계층은 사용자가 애플리케이션과 상호작용하는 사용자 인터페이스 및 통신 계층입니다.
- 주 기능은 정보를 표시하고 사용자로부터 정보를 수집하는 것입니다.
- 클라이언트로부터 접속 요청(HTTP Request)을 직접 받아서 그 처리를 뒷단의 Application 계층에 넘기고 그 결과를 클라이언트에 반환합니다.
- Web Application Server와 웹 브라우저와의 중간 다리 역할입니다.
- 일반적으로 HTML, CSS Javascript로 개발됩니다.
- 관련 소프트웨어로는 Apache, IIS, Nginx 등이 있습니다.

## Application 계층 (Web Application Server)

- Presentation 계층에서 수집된 정보로 비즈니스 로직을 수행합니다.
- Presentation 계층으로부터의 요청을 처리하고, 필요하면 Data 계층에 접속해서 데이터를 추출하고 이를 가공한 결과를 Presentation 계층으로 전달합니다.
- Java, Javascript, Python, PHP, Ruby 등 다양한 언어로 개발됩니다.
- 관련 소프트웨어로는 Tomcat, WebLogic, Websphere 등이 있습니다.

## Data 계층 (Database Server)

- 애플리케이션이 처리하는 정보가 관리됩니다.
- 관련 소프트웨어로는 MySQL, PostgreSQL, Oracle 등의 RDBMS와 MongoDB와 같은 NoSQL이 있습니다.
