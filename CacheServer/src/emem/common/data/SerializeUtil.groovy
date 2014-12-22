package emem.common.data

/**
 * Created by Hello on 2014/12/22.
 */
class SerializeUtil {

    static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } finally {
            oos?.close()
            baos?.close()
        }
    }

    static Object deserialize(byte[] bytes) {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } finally {
            ois?.close()
            bais?.close()
        }
    }

    static String objectToString(object) {
        byte[] bytes = serialize(object)
        return bytes.encodeBase64()
    }

    static Object stringToObject(string) {
        byte[] bytes = string.decodeBase64()
        return deserialize(bytes)
    }
}
