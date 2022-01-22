package plus.knowing.distribute.jws;

public class ServerA {
    public static void main(String[] args) {
        ServerHandlerService serverHandlerService = new ServerHandlerService();
        ServerHandler serverHandlerPort = serverHandlerService.getServerHandlerPort();

        System.out.println(serverHandlerPort.method1());
        System.out.println(serverHandlerPort.method2("web service ..."));
    }
}
