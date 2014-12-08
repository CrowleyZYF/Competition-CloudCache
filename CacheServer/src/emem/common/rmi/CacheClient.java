package emem.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Created by Hello on 2014/12/5.
 */
public interface CacheClient extends Remote {

    void set(String key, String value) throws RemoteException;

    String get(String key) throws RemoteException;

    void hashSetAll(String key, Map<String, String> map) throws RemoteException;

    Map<String, String> hashGetAll(String key) throws RemoteException;

    void hashSet(String key, String index, String value) throws RemoteException;

    String hashGet(String key, String index) throws RemoteException;

    void hashRemove(String key, String index) throws RemoteException;

    long hashSize(String key) throws RemoteException;

}
