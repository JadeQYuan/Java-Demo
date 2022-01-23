
使用Java自带RMI API实现远程调用。

## 功能
ServerA项目通过RMI调用ServerB上的方法，ServerB完成调用并返回结果。

## 模型
Client <---> Stub <---> Socket（客户端） <--|-Internet-|--> Socket（服务端） <---> Skeleton <---> Server

在Java SE 1.4及以前的版本需要手动建立Stub对象，通过运行rmic命令来生成远程对象实现类的Stub对象，Java SE 5.0之后可以通过动态代理来完成，不需要手动创建了。
在J2SE 1.2之前，Stub与Skeleton对话，在J2SE 1.2之后，Skeleton被合并到Server中，Stub与Server对话。

## 结构
### 注册表
注册表有两种实现方式，第一种是使用 JDK/bin/rmiregistry 程序启动，第二种是编程通过LocateRegistry.createRegistry(port)实现。
有了注册表，可以通过LocateRegistry的bind和lookup来实现绑定和查找远程对象。

### 绑定服务
绑定是将服务通过url的形式，绑定到注册表上。使用LocateRegistry的bind方法。
其它方式：（内部调用了LocateRegistry.bind方法）
1. 调用Naming.bind静态方法。
2. 使用Context.bind方法。Context可以使用javax.naming.InitialContext。

### 查找服务
查找有两种，一种是将注册表上的服务全列出来，使用LocateRegistry.list方法，另一种是通过url查找指定的服务，使用LocateRegistry.lookup方法。
其它方式：（内部调用了LocateRegistry.lookup方法）
1. 使用Naming.lookup静态方法。
2. 使用Context.lookup方法。Context可以使用javax.naming.InitialContext。

## 实现
1. 定义远程接口。添加接口，接口需集成java.rmi.Remote接口，接口方法需声明抛出RemoteException。是一种约定，相当于其它方式实现的协议。
2. 实现远程接口（服务端）。继承UnicastRemoteObject类，或实现Serializable接口并手动export。（UnicastRemoteObject构造方法调用了export）
3. 实现注册表。
4. 绑定远程服务。
5. 客户端调用。

## 理解
- 如果需要传递对象，则对象需要实现Serializable接口，且定义serialVersionUID，并使不同服务定义相同的serialVersionUID，否则无法反序列化。
- 继承UnicastRemoteObject会自动创建线程，并阻塞该线程。默认情况下主线程结束子线程不受影响。
- 服务端需要调用UnicastRemoteObject.export()方法才能使用，继承UnicastRemoteObject会自动调用。
- 并不是无感知使用，还需要处理RemoteException。
- 三种方式的区别（host和port都可以使用缺省值，host缺省为localhost，port缺省为1099）：
  - LocateRegistry方式，需要先使用host和port获取对象，通过对象调用方法，参数为name。
  - Naming方式，传递的参数为 host + port + name 组成的uri。
  - Context方式，使用JNDI，参数需要schema，即rmi:。
- 总共有3个服务，客户端A，注册器，服务端B，最终需要客户端A调用服务端B，而服务端B所开远程监听线程的端口客户端并不需要知道（客户端调用是无感知的，不需要处理网络部分），而注册器的端口及host却是“well-known”的，所以使用export将服务端B的信息保存到注册器，客户端A通过注册器，获取到连接服务端B的信息，从而进行通信。
- bind操作将name绑定到registry中，如果registry是单独启动的，则会使用动态代理，通过网络将数据放在registry中。
- export内部实现中开启了ServerSocket，等待客户端通过registry获取的信息连接，export可以指定端口。
- 一旦客户端获取到服务端的信息，那么注册器就不需要了。
- 如果单独启动registry（rmiregistry），需要在编译后的classes路径启动，否则会报ClassNotFoundException。
- 如果在服务端中创建registry，create内部实现中开启了ServerSocket。与export只会开启一个。

## 扩展
- JNDI， Java Naming and Directory Interface。Java命名和目录接口。
