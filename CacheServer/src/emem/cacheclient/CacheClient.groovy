package emem.cacheclient

import emem.common.data.LHData

/**
 * Created by Hello on 2014/12/7.
 */
class CacheClient {
    private final host = 'localhost'
    private final port = 8080
    private final token = 'a'

    CacheClient(String host, int port, String token) {
        this.host = host
        this.port = port
        this.token = token
    }

    void set(String key, String value) {
        new HTTPClient(
                host: host,
                port: port,
                path: url('/set'),
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value]
        ).request()
    }

    String get(String key) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: url('/get'),
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
                path: url('/hash/setAll'),
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value]
        ).request()
    }

    Map<String, String> hashGetAll(String key) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: url('/hash/getAll'),
                method: 'POST',
                parameters: ['token': token, 'key': key]
        ).request()
        return value?LHData.hashFromString(value):null
    }

    void hashSet(String key, String index, String value) {
        new HTTPClient(
                host: host,
                port: port,
                path: url('/hash/set'),
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index, 'value': value]
        ).request()
    }

    String hashGet(String key, String index) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: url('/hash/get'),
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index]
        ).request()
        return value?:null
    }

    void hashRemove(String key, String index) {
        new HTTPClient(
                host: host,
                port: port,
                path: url('/hash/remove'),
                method: 'POST',
                parameters: ['token': token, 'key': key, 'index': index]
        ).request()
    }

    int hashSize(String key) {
        def value = new HTTPClient(
                host: host,
                port: port,
                path: url('/hash/size'),
                method: 'POST',
                parameters: ['token': token, 'key': key, 'value': value]
        ).request()
        return value.toInteger()
    }

    def url(path) {
        return "/data$path"
    }

}
