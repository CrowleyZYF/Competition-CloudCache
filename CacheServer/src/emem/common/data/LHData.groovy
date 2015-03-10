package emem.common.data
/**
 * Created by hello on 14-11-20.
 */
class LHData {

    static def listToString(list) {
        list.collect {
            it.replaceAll(',', '\\\\,')
        }.join(',')
    }

    static def listFromString(String str) {
        str.replaceAll('\\\\,', '\\\\\0').split(',').collect {
            it.replaceAll('\\\\\0', ',')
        }
    }

    static def hashToString(hash) {
        listToString hash.entrySet().collect {
            def key = it.key.replaceAll(':', '\\\\:')
            def value = it.value.replaceAll(':', '\\\\:')
            "$key:$value"
        }
    }

    static def hashFromString(str) {
        def hash = [:]
        listFromString(str).each {
            it = it.replaceAll('\\\\:', '\\\\0')
            def tokens = it.split(':')
            if(tokens.length <= 1) throw new SyntaxException();
            hash[tokens[0].replaceAll('\\\\0', '\\:')] = tokens[1].replaceAll('\\\\0', '\\:')
        }
        hash
    }

}
