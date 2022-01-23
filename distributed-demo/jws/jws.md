
## 实现步骤
1. 添加服务端服务接口，并实现。实现类使用javax.jws.@WebService注解。
2. 使用javax.jws.Endpoint发布。
3. 访问发布地址 + “?wsdl”，查看根据注解生成的WSDL。
4. 根据WSDL，使用jdk工具生成代码。 wsimport -encoding UTF-8 wsdl地址。
5. 客户端使用生成的代码访问服务端。先创建Service，通过service创建实现类。使用实现类直接调用。
