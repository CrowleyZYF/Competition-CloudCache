package emem.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**RMI远程接口, 从远程RMI服务器根据token获取CacheClient实例
 *
 * Created by Hello on 2014/12/5.
 */
public interface CacheClientFactory extends Remote {

    CacheClient getCacheClient(String token) throws RemoteException;

}
