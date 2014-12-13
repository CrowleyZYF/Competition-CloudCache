package emem.common.data
/**
 * Created by hello on 14-11-20.
 */
class LHData {

    static def listToString(list) {
        list.join(',')
    }

    static def listFromString(str) {
        str.split(',')
    }

    static def hashToString(hash) {
        listToString hash.entrySet().collect {
            "${it.key}:${it.value}"
        }
    }

    static def hashFromString(str) {
        def hash = [:]
        listFromString(str).each {
            def tokens = it.split(':')
            hash[tokens[0]] = tokens[1]
        }
        hash
    }

}
