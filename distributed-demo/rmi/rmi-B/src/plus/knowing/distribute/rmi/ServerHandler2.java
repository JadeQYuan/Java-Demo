package plus.knowing.distribute.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerHandler2 extends UnicastRemoteObject implements ServerService {

    protected ServerHandler2() throws RemoteException {
        super();
    }

    public String method1() throws RemoteException {
        return "this is serverB method1 ...";
    }

    @Override
    public DTO method2(String str) throws RemoteException {
        DTO dto = new DTO();
        dto.setName(str);
        dto.setCount(str.length());
        return dto;
    }
}
