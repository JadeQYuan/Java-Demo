package plus.knowing.distribute.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;

public class ServerHandler implements ServerService, Serializable {

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
