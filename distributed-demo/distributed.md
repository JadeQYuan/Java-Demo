
## 分布式系统
分布式系统是由一组通过网络进行通信、为了完成共同的任务而协调工作的计算机节点组成的系统。
分布式系统的出现是为了用廉价的、普通的机器完成单个计算机无法完成的计算、存储任务。其目的是利用更多的机器，处理更多的数据。

分布式系统分为分布式计算与分布式存储。
计算与存储是相辅相成的，计算需要数据，要么来自实时数据，要么来自存储的数据；而计算的结果也需要存储。

## 发展历程
- 1988年，ONE RPC被定义为标准的RPC规范。（不支持对象的传递）
- 1989年，万维网。
- 1991年，OMG发布CORBA 1.0。（太复杂）
- 1996年，HTTP/1.x版本。
- 1997年，OMG发布CORBA 2.0。
- 1999年，SOAP发布。
- 2000年，RESTful架构。
- 2002年，ZeroC ice 发布。
- 2008年，Google开源Protocol Buffer。
- 2008年，Facebook开源Thrift。
- 2015年，HTTP/2.0发布。
- 2015年，Google开源gRPC。

## 实现
序列化 + 网络传输

- RMI
- WebService
- CORBA
- DCOM 微软发布的，只能用在微软的语言下。
- RPC： Dubbo、Thrift、Hessian

JDK中java.rmi实现RMI机制，javax.jws实现WebService机制。

## RMI
只能用于Java平台下。
使用Java内建的序列化功能，效率不是很高。
网络传输JRMP（Java Remote Method Protocol）协议。

## WebService
基于Http + XML，可以跨语言。
三要素：SOAP + WSDL + UDDI。
SOAP（Simple Object Access Protocol），是一种基于XML的协议，使应用程序通过Http来交换信息。
WSDL（Web Service Description Language），基于XML的语言，用于描述Web Services以及如何对它们进行访问。
UDDI（Universal Description，Discovery and Integration），通用的描述、发现以及整合。

## CORBA
Common Object Request Broker Architecture。公共对象请求代理结构。
组成：IDL + ORB + IIOP。
IDL（Interface Description Language）。
ORB（Object Request Broker）。
IIOP（Internet Inter-ORB Protocol）。
