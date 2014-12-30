package emem.cacheserver.rmi

import emem.cacheserver.core.ServerConfig
import emem.common.data.SerializeUtil
import emem.common.rmi.CacheClient

import java.rmi.RemoteException

/**
 * Created by Hello on 2014/12/5.
 */
class CacheClientImpl implements CacheClient {

    private final cacheClient
    private final token

    CacheClientImpl(cacheClient, token) {
        this.cacheClient = cacheClient
        this.token = token
    }

    @Override
    void set(String key, String value) {
        log('set', key)
        cacheClient.operate {
            it.set(key, value)
        }
    }

    @Override
    String get(String key) {
        log('get', key)
        def value
        cacheClient.operate {
            value = it.get(key)
        }
        value
    }

    @Override
    void hashSetAll(String key, Map<String, String> map) {
        log('hash/setAll', key)
        cacheClient.operate {
            it.hashSetAll(key, map)
        }
    }

    @Override
    Map<String, String> hashGetAll(String key) throws RemoteException {
        log('hash/getAll', key)
        def map
        cacheClient.operate {
            map = it.hashGetAll(key)
        }
        map
    }

    @Override
    void hashSet(String key, String index, String value) throws RemoteException {
        log('hash/set', key)
        cacheClient.operate {
            it.hashSet(key, index, value)
        }
    }

    @Override
    String hashGet(String key, String index) throws RemoteException {
        log('hash/get', key)
        def value
        cacheClient.operate {
            value = it.hashGet(key, index)
        }
        value
    }

    @Override
    void hashRemove(String key, String index) throws RemoteException {
        log('hash/remove', key)
        cacheClient.operate {
            it.hashRemove(key, index)
        }
    }

    @Override
    long hashSize(String key) throws RemoteException {
        log('hash/size', key)
        def size
        cacheClient.operate {
            size = it.hashSize(key)
        }
        size
    }

    @Override
    void set(String key, String value, int expire) {
        log('set', key)
        cacheClient.operate {
            it.set(key, value)
            it.expire(key, expire)
        }
    }

    @Override
    String get(String key, int expire) {
        log('get', key)
        def value
        cacheClient.operate {
            value = it.get(key)
            it.expire(key, expire)
        }
        value
    }

    @Override
    void hashSetAll(String key, Map<String, String> map, int expire) {
        log('hash/setAll', key)
        cacheClient.operate {
            it.hashSetAll(key, map)
            it.expire(key, expire)
        }
    }

    @Override
    Map<String, String> hashGetAll(String key, int expire) throws RemoteException {
        log('hash/getAll', key)
        def map
        cacheClient.operate {
            map = it.hashGetAll(key)
            it.expire(key, expire)
        }
        map
    }

    @Override
    void hashSet(String key, String index, String value, int expire) throws RemoteException {
        log('hash/set', key)
        cacheClient.operate {
            it.hashSet(key, index, value)
            it.expire(key, expire)
        }
    }

    @Override
    String hashGet(String key, String index, int expire) throws RemoteException {
        log('hash/get', key)
        def value
        cacheClient.operate {
            value = it.hashGet(key, index)
            it.expire(key, expire)
        }
        value
    }

    @Override
    void hashRemove(String key, String index, int expire) throws RemoteException {
        log('hash/remove', key)
        cacheClient.operate {
            it.hashRemove(key, index)
            it.expire(key, expire)
        }
    }

    @Override
    long hashSize(String key, int expire) throws RemoteException {
        log('hash/size', key)
        def size
        cacheClient.operate {
            size = it.hashSize(key)
            it.expire(key, expire)
        }
        size
    }

    void setObject(String key, Serializable obj) {
        def value = SerializeUtil.objectToString(obj)
        set(key, value)
    }

    void setObject(String key, Serializable obj, int expire) {
        def value = SerializeUtil.objectToString(obj)
        set(key, value, expire)
    }

    Serializable getObject(String key) {
        def value = get(key)
        return SerializeUtil.stringToObject(value)
    }

    Serializable getObject(String key, int expire) {
        def value = get(key, expire)
        return SerializeUtil.stringToObject(value)
    }

    def log(key, op) {
        ServerConfig.statDB[token].insert(['token': token, 'key': key, 'op': op, 'time': System.currentTimeMillis()])
    }
}
