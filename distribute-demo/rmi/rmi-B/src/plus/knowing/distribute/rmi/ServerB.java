package plus.knowing.distribute.rmi;

import javax.naming.InitialContext;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerB {

    private static final int RMI_PORT = 9999;

    private static final String RMI_NAME = "plus.knowing.distribute.rmi.ServerService";

    private static final String RMI_URL = "rmi://localhost:" + RMI_PORT + "/" + RMI_NAME;

    public static void main(String[] args) throws Exception {
        Registry registry;
        // 服务与注册器一起启动
        registry = LocateRegistry.createRegistry(RMI_PORT);
        // 注册器单独启动，获取
//        registry = LocateRegistry.getRegistry();

        ServerService serverService;
        // 不继承UnicastRemoteObject，需要单独export
//        serverService = new ServerHandler();
//        UnicastRemoteObject.exportObject(serverService, RMI_PORT);
        // 继承UnicastRemoteObject
        serverService = new ServerHandler2();

        // 绑定, 三种方式
        registry.bind(RMI_NAME, serverService);
//        Naming.bind(RMI_NAME, serverService);
//        new InitialContext().bind(RMI_URL, serverService);
    }
}
