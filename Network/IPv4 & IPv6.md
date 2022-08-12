# IPv4&IPv6

## IPv4

### IPv4 Protocol

IPv4 Protocol은 **비신뢰적**이고 **비연결형**인 **최선형** 전송 서비스입니다. 최선형 전송의 의미는 IPv4 패킷이 유실되거나 순서에 맞지 않게 도착할 수 있다는 뜻입니다.

만약 신뢰성이 중요하다면 IPv4는 TCP와 같은 신뢰성 있는 전송 계층 프로토콜과 함께 사용되어야 합니다.

<br/>

### IPv4 Datagram

IPv4의 Datagram은 가변 길이의 패킷으로, Header와 Payload(Data)로 이뤄져 있습니다.

Header는 20에서 60바이트의 길이이며 라우팅과 같은 전송과 관련한 정보를 가지고 있습니다.

![IPv4 Datagram](https://user-images.githubusercontent.com/68716284/184327886-94275d9d-c8d7-45d3-968b-47729305c4f5.png)

#### 출처: https://electronicspost.com/ipv4-datagram-format/

<br/>

IPv4의 각 필드의 기능은 다음과 같습니다.
| 필드 | 기능 |
| -- | -- |
| Version | IP 프로토콜의 버전을 의미하며, IPv4는 4의 값을 가집니다.|
| Header length | IPv4 Datagram은 가변의 Header를 가지며, 수신자는 전체 길이를 확인하기 위하여 필드 값에 4를 곱합니다. |
| Type of service | 8-bit로 구성되며, Datagram이 라우터에 의해 어떻게 처리되어야 할지 정의합니다. |
| datagram length (bytes) | 16-bit의 필드는 IP Datagram의 전체 바이트 크기를 의미합니다.|
| 16-bit Identifier, Flags, 13-bit Fragmentation offset | 이 세 필드는 Datagram의 크기가 하부 네트워크가 처리할 수 있는 크기보다 클 경우에 필요한 IP Datagram의 단편화와 관련이 있습니다.|
| Time-to-live | Datagram이 방문할 수 있는 최대 라우터의 수를 의미합니다. |
| Upper-layer Protocol | Datagram이 목적지에 도착하면 어느 프로토콜로 Payload가 전달되어야 할지 알려줍니다. |
| Header checksum | IP는 Header를 검사하기 위한 checksum 필드를 사용하고 있습니다. |
| 32-bit Source IP address, 32-bit Destination IP address | 이 필드는 32-bit 길이의 출발지, 목적지 주소를 의미합니다. |
| Options (if any) | Datagram Header는 40바이트까지 옵션을 가질 수 있습니다. 옵션은 필수가 아니지만, 옵션을 사용할 때 이 필드가 사용됩니다. |
| Data | 이 필드는 Payload(Data)를 의미힙니다. |

<br/>

### IPv4 주소

IPv4 주소는 오늘날 대중적으로 사용되는 IP 주소입니다.

| 진법       | IP Address                          |
| ---------- | ----------------------------------- |
| 2진법      | 11000000 10101000 00000000 00000001 |
| **10진법** | **192.168.0.1**                     |
| 16진법     | C0 A8 00 01                         |

IPv4 주소는 32-bit 주소 체계로 위와 같이 표기할 수 있는데, 일반적으로 10진수와 '.'를 이용하여 표현합니다.

10진수로 표기할 경우 8비트씩 끊어서 사용하므로 0~255 범위의 숫자를 사용하여 표현할 수 있습니다.

<br/>

### IPv4 특수 주소

IPv4에는 특수 용도로 사용되는 주소들이 있습니다.

| 특수 주소         | 기능                                                                                                                                                                                                                                |
| ----------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Loopback          | 127.0.0.8/8 주소는 루프백 주소입니다. 이 주소를 가진 패킷은 호스트를 떠나지 않고, 호스트에 남게 됩니다. 테스트 용도로 많이 사용되며 그 중 127.0.0.1 주소는 테스트에 가장 많이 사용되는 주소입니다.                                  |
| Limited-broadcast | 255.255.255.255/32 주소는 제한된 브로드캐스트 주소입니다. 이 주소를 통해 호스트나 라우터가 네트워크 상의 모든 장치로 데이터를 보낼 수 있습니다. 하지만 대부분의 라우터에는 이러한 패킷을 차단하기 때문에 외부로는 보낼 수 없습니다. |
| This-host         | 0.0.0.0/32 주소는 디스-호스트 주소입니다. 이 주소는 현재 네트워크를 의미하며, 자신의 주소를 모를 때 사용되는 주소입니다.                                                                                                            |
| Private           | 10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/16 주소는 사설 주소로 지정되어 있습니다. 이러한 주소들은 공공 인터넷에 존재하지 않으며 사설로만 사용이 가능합니다.                                                                           |
| Multicast         | 224.0.0.0/4 주소는 멀티캐스트 용도로 사용되는 주소입니다.                                                                                                                                                                           |

## IPv6

### IPv6 Protocol

IPv6 Protocol은 IPv4의 주소 고갈, IP 패킷의 형태 변경, ICMP와 같은 몇몇 보조 프로토콜의 수정을 위해 고안된 새로운 버전의 프로토콜입니다.

<br/>

### IPv6 Datagram

IPv6 Datagram은 Header와 Payload로 구성됩니다.

기본 Header는 40바이트를 차지하며, 확장 Header와 상위 계층 Data는 65,535바이트까지의 정보를 가질 수 있습니다.

![IPV6](https://user-images.githubusercontent.com/68716284/184328060-61086d63-0a04-42a5-9bcf-6fccda07b098.png)

#### 출처: https://electronicspost.com/ipv6-datagram-format/

<br/>

IPv4의 각 필드의 역할은 다음과 같습니다.
| 필드 | 기능 |
| -- | -- |
| Version | IP의 버전 전호를 의미합니다. IPv6에서는 6의 값을 가집니다.|
| Traffic class | IPv4의 Type of service 필드와 유사합니다. |
| Flow label | Data의 특정한 프름을 위한 특별한 처리를 제공하기 위해 사용됩니다. |
| Payload length | 기본 Header를 제외한 IP Datagram의 길이를 의미합니다. IPv6의 기본 Header의 길이는 40바이트로 고정되어 있기 때문에 페이로드의 길이만 정의하면 됩니다. |
| Next hdr | Next hdr 필드는 첫 확장 Header의 종류를 정의하거나 Datagram의 기본 Header를 뒤따르는 Header를 의미합니다. 이 필드는 IPv4의 Upper-layer Protocol 필드와 유사합니다.|
| Hop limit | IPv4의 Time-to-live 필드와 같은 목적으로 사용됩니다. |
| Source address(128bits), Destination address(128bits) | 출발지, 목적지 주소를 식별하기 위해 사용됩니다. |
| Data | IPv4와 달리 IPv6의 Data(Payload) 필드는 다른 형태와 의미를 가집니다. |

<br/>

### IPv6 Datagram Payload(Data 필드)

![IPv6 Datagram Payload](https://user-images.githubusercontent.com/68716284/184328117-54ab142f-ac7a-4b22-8399-166dc528f5da.png)

#### 출처: https://www.cisco.com/en/US/technologies/tk648/tk872/technologies_white_paper0900aecd8054d37d.html

<br/>

IPv6 패킷은 기본 Header와 확장 Header로 구성됩니다. 기본 Header의 길이는 40바이트로 고정되어 있지만, 부가적인 기능을 제공하기 위해 기본 Header 뒤에 6개까지 확장 Header를 붙일 수 있습니다.

- Hop-by-hop
- Destination
- Source Routing
- Frgmentation
- Authentication
- ESP

<br/>

### Ipv6 주소

IPv6는 IP 주소가 부족해짐에 따라 새롭게 고안된 IP 주소 체계입니다. 기존의 32-bit의 주소는 128-bit로 확장되었습니다.

오늘날 네트워크 기능을 가지는 장비(L3 Router, L4 Switch, PC, Server 등)들의 대부분은 IPv4 주소와 IPv6 주소를 모두 지원하고 있으며, 천천히 IPv6 주소 체계로 전환을 하고 있습니다.

| 진법       | IP Address                                  |
| ---------- | ------------------------------------------- |
| 2진법      | 1111111011110110 ... 1111111100000000       |
| **16진법** | **FEF6:BA98:7654:46E0:AFFF:F210:1124:00F1** |

16진법으로 표현된 IPv6 주소는 많은 수의 0을 포함하고 있어 매우 긴 형태를 보이고 있습니다.

이러한 경우 앞 부분의 0을 생략하고 축약된 표현을 사용할 수 있습니다. 0082는 82, 0FFE는 FFE, 0000은 0으로 표현할 수 있습니다.

연속되는 영역이 0으로만 구성되어 있다면, 더 많은 축약이 가능합니다.

> FFFF:0:0:0:EEEE:AAAA:BBBB -> FFFF::EEEE:AAAA:BBBB

위 축약은 주소당 한 번만 가능하며, 0만을 가지는 연속된 영역이 두 개 이상 존재할 경우 한 부분만 축약이 가능합니다.
