package plus.knowing.distribute.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerService extends Remote {

    String method1() throws RemoteException;

    DTO method2(String str) throws RemoteException;
}
