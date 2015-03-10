package emem.cacheclient

import emem.common.data.LHData
import emem.common.data.SerializeUtil

/**
 * Created by Hello on 2014/12/7.
 */
class HTTPCacheClient {
    private final host
    private final port
    private final token

    HTTPCacheClient(String host, int port, String token) {
        this.host = host
        this.port = port
        this.token = token
    }

    void set(String key, String value) {
        new HTTPClient(
                host: host,
                port: port,
                path: '/set',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value]
        ).request()
    }

    String get(String key) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/get',
                method: 'POST',
                parameters: ['token': token, 'key': key]
        ).request()
        return value?:null
    }

    void hashSetAll(String key, Map<String, String> hash) {
        def value = LHData.hashToString(hash)
        new HTTPClient(
                host: host,
                port: port,
                path: '/hash/setAll',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value]
        ).request()
    }

    Map<String, String> hashGetAll(String key) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/hash/getAll',
                method: 'POST',
                parameters: ['token': token, 'key': key]
        ).request()
        return value?LHData.hashFromString(value):null
    }

    void hashSet(String key, String index, String value) {
        new HTTPClient(
                host: host,
                port: port,
                path: '/hash/set',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index, 'value': value]
        ).request()
    }

    String hashGet(String key, String index) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/hash/get',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index]
        ).request()
        return value?:null
    }

    void hashRemove(String key, String index) {
        new HTTPClient(
                host: host,
                port: port,
                path: '/hash/remove',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index]
        ).request()
    }

    int hashSize(String key) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/hash/size',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value]
        ).request()
        return value.toInteger()
    }

    void set(String key, String value, int expire) {
        new HTTPClient(
                host: host,
                port: port,
                path: '/set',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value, 'expire': expire]
        ).request()
    }

    String get(String key, int expire) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/get',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'expire': expire]
        ).request()
        return value?:null
    }

    void hashSetAll(String key, Map<String, String> hash, int expire) {
        def value = LHData.hashToString(hash)
        new HTTPClient(
                host: host,
                port: port,
                path: '/hash/setAll',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value, 'expire': expire]
        ).request()
    }

    Map<String, String> hashGetAll(String key, int expire) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/hash/getAll',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'expire': expire]
        ).request()
        return value?LHData.hashFromString(value):null
    }

    void hashSet(String key, String index, String value, int expire) {
        new HTTPClient(
                host: host,
                port: port,
                path: '/hash/set',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index, 'value': value, 'expire': expire]
        ).request()
    }

    String hashGet(String key, String index, int expire) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/hash/get',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index, 'expire': expire]
        ).request()
        return value?:null
    }

    void hashRemove(String key, String index, int expire) {
        new HTTPClient(
                host: host,
                port: port,
                path: '/hash/remove',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index, 'expire': expire]
        ).request()
    }

    int hashSize(String key, int expire) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: '/hash/size',
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value, 'expire': expire]
        ).request()
        return value.toInteger()
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

}
