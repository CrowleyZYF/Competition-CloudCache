package emem.cacheserver.rmi

import emem.common.rmi.CacheClient

import java.rmi.RemoteException

/**
 * Created by Hello on 2014/12/5.
 */
class CacheClientImpl implements CacheClient {

    private final cacheClient

    CacheClientImpl(cacheClient) {
        this.cacheClient = cacheClient
    }

    @Override
    void set(String key, String value) {
        cacheClient.operate {
            it.set(key, value)
        }
    }

    @Override
    String get(String key) {
        def value
        cacheClient.operate {
            value = it.get(key)
        }
        value
    }

    @Override
    void hashSetAll(String key, Map<String, String> map) {
        cacheClient.operate {
            it.hashSetAll(key, map)
        }
    }

    @Override
    Map<String, String> hashGetAll(String key) throws RemoteException {
        def map
        cacheClient.operate {
            map = it.hashGetAll(key)
        }
        map
    }

    @Override
    void hashSet(String key, String index, String value) throws RemoteException {
        cacheClient.operate {
            it.hashSet(key, index, value)
        }
    }

    @Override
    String hashGet(String key, String index) throws RemoteException {
        def value
        cacheClient.operate {
            value = it.hashGet(key, index)
        }
        value
    }

    @Override
    void hashRemove(String key, String index) throws RemoteException {
        cacheClient.operate {
            it.hashRemove(key, index)
        }
    }

    @Override
    long hashSize(String key) throws RemoteException {
        def size
        cacheClient.operate {
            size = it.hashSize(key)
        }
        size
    }

    @Override
    void set(String key, String value, int expire) {
        cacheClient.operate {
            it.set(key, value)
            it.expire(key, expire)
        }
    }

    @Override
    String get(String key, int expire) {
        def value
        cacheClient.operate {
            value = it.get(key)
            it.expire(key, expire)
        }
        value
    }

    @Override
    void hashSetAll(String key, Map<String, String> map, int expire) {
        cacheClient.operate {
            it.hashSetAll(key, map)
            it.expire(key, expire)
        }
    }

    @Override
    Map<String, String> hashGetAll(String key, int expire) throws RemoteException {
        def map
        cacheClient.operate {
            map = it.hashGetAll(key)
            it.expire(key, expire)
        }
        map
    }

    @Override
    void hashSet(String key, String index, String value, int expire) throws RemoteException {
        cacheClient.operate {
            it.hashSet(key, index, value)
            it.expire(key, expire)
        }
    }

    @Override
    String hashGet(String key, String index, int expire) throws RemoteException {
        def value
        cacheClient.operate {
            value = it.hashGet(key, index)
            it.expire(key, expire)
        }
        value
    }

    @Override
    void hashRemove(String key, String index, int expire) throws RemoteException {
        cacheClient.operate {
            it.hashRemove(key, index)
            it.expire(key, expire)
        }
    }

    @Override
    long hashSize(String key, int expire) throws RemoteException {
        def size
        cacheClient.operate {
            size = it.hashSize(key)
            it.expire(key, expire)
        }
        size
    }
}
