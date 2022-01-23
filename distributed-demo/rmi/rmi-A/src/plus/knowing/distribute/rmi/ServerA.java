package plus.knowing.distribute.rmi;

import javax.naming.InitialContext;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerA {

    private static final int RMI_PORT = 9999;

    private static final String RMI_NAME = "plus.knowing.distribute.rmi.ServerService";

    private static final String RMI_URL = "rmi://localhost:" + RMI_PORT + "/" + RMI_NAME;

    public static void main(String[] args) throws Exception {
        ServerService serverService;

        // 查找，三种方式
        serverService = (ServerService) LocateRegistry.getRegistry(RMI_PORT).lookup(RMI_NAME);
//        serverService = (ServerService) Naming.lookup(RMI_URL);
//        serverService = (ServerService) new InitialContext().lookup(RMI_URL);

        // 调用，查看结果
        System.out.println(serverService.method1());
        System.out.println(serverService.method2("123"));
    }
}
