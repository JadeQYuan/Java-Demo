
## 实现
1. 添加idl文件。
2. 生成服务端代码。idlj -pkgTranslate corba plus.knowing.distribute.corba -td corba-B\src -fserver ServerService.idl
3. 生成客户端代码。idlj -pkgTranslate corba plus.knowing.distribute.corba -td corba-A\src -fclient ServerService.idl
...

## 问题
- 依赖于idl文件，需要先了解怎么创建idl文件。
- idlj不能指定编码。
- 生成的服务端代码有问题，不能直接使用。
- Java SE 11 移除了CORBA模块。

## 扩展
Thrift，是一种接口描述和二进制通讯协议，被用来定义和创建阔语言的服务。被当作一个远程过程调用（RPC）框架来使用。
