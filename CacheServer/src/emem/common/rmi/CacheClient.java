package emem.common.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**这是缓存连接的RMI远程接口
 * 缓存数据约束中, 键必须是字符串, 值包括字符串和hash两种类型.
 * 其中hash是一套键值对集合, 键和值都是字符串类型. 可以操作整套的hash数据, 也可以对hash数据中单个的索引进行操作.
 * 缓存没有提供remove键的操作, 但对每个数据进行了过期设置, 例如给定过期设置expire=10, 则10秒后数据会从缓存中清除.
 * 每个数据操作接口会给出两种版本, 一种是不含过期参数, 另一种是含有的. 例如set方法:
 *      set(String key, String value) 和 set(String key, String value, int expire)
 * 两种. 另外, 针对普通HTTP协议的jar封装, 虽然获取具体的CacheClient实例的方法不同, 但是CacheClient所含的接口方法
 * 是一致的, 完全可以参照本接口的方法定义来对照出相关的方法定义.
 *
 * Created by Hello on 2014/12/5.
 */
public interface CacheClient extends Remote {

    /**存储单纯的字符串类型的键值对
     *
     * @param key
     * @param value
     */
    void set(String key, String value) throws RemoteException;

    /**返回绑定到制定键的字符串类型的值
     *
     * @param key
     * @return
     */
    String get(String key) throws RemoteException;

    /**设置hash类型的完整数据, 其中hash值用一个字符串到字符串的映射map表示
     *
     * @param key
     * @param map
     */
    void hashSetAll(String key, Map<String, String> map) throws RemoteException;

    /**返回hash类型的完整数据
     *
     * @param key
     * @return
     */
    Map<String, String> hashGetAll(String key) throws RemoteException;

    /**设置hash类型的数据, 仅仅对其中的某个索引进行操作.
     *
     * @param key
     * @param index hash数据中的某个索引
     * @param value
     */
    void hashSet(String key, String index, String value) throws RemoteException;

    /**返回hash类型的数据, 仅仅对其中的某个索引进行操作.
     *
     * @param key
     * @param index hash数据中的某个索引
     * @return
     */
    String hashGet(String key, String index) throws RemoteException;

    /**可以针对hash数据中的某个索引进行删除
     *
     * @param key
     * @param index
     */
    void hashRemove(String key, String index) throws RemoteException;

    /**返回的是hash数据中索引的个数
     *
     * @param key
     * @return
     */
    long hashSize(String key) throws RemoteException;

    /**存储对象类型的数据, 该对象需要实现Serializable接口.
     *
     * @param key
     * @param obj
     * @throws RemoteException
     */
    void setObject(String key, Serializable obj) throws RemoteException;

    /**返回对象类型的数据, 该对象需要实现Serializable接口.
     *
     * @param key
     * @return
     * @throws RemoteException
     */
    Serializable getObject(String key) throws RemoteException;


    void set(String key, String value, int expire) throws RemoteException;

    String get(String key, int expire) throws RemoteException;

    void hashSetAll(String key, Map<String, String> map, int expire) throws RemoteException;

    Map<String, String> hashGetAll(String key, int expire) throws RemoteException;

    void hashSet(String key, String index, String value, int expire) throws RemoteException;

    String hashGet(String key, String index, int expire) throws RemoteException;

    void hashRemove(String key, String index, int expire) throws RemoteException;

    long hashSize(String key, int expire) throws RemoteException;

    void setObject(String key, Serializable obj, int expire) throws RemoteException;

    Serializable getObject(String key, int expire) throws RemoteException;

}
