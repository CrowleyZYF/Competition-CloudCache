package emem.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Hello on 2014/12/5.
 */
public interface CacheClientFactory extends Remote {

    CacheClient getInstance(String token) throws RemoteException;

}
