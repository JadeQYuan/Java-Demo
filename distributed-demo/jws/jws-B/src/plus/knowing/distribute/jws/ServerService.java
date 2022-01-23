package plus.knowing.distribute.jws;

import java.rmi.RemoteException;

public interface ServerService {

    String method1() throws RemoteException;

    DTO method2(String str) throws RemoteException;
}
