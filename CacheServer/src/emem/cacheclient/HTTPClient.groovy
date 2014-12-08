package emem.cacheclient

/**
 * Created by Hello on 2014/12/7.
 */
class HTTPClient {

    def host

    def port

    def path

    def method

    def parameters

    def request() {
        def url = "http://$host:$port$path"
        url = new URL(url)
        HttpURLConnection conn = url.openConnection()
        conn.setRequestMethod(method)

        conn.setDoOutput(true)
        conn.setDoInput(true)

        try {
            conn.connect()

            def output = conn.getOutputStream()
            def data = parameters.collect { key, value -> "$key=$value"} .join('&')
            output.write(data.getBytes('US-ASCII'))
            output.close()

            def input = conn.getInputStream()
            input.getText()
        } catch(e) {
            def response
            try {
                response = "code: ${response.getResponseCode()}, message:${conn.getResponseCode()}"
                System.err.println(response)
            } catch(e1) {}
            throw new RuntimeException(response, e)
        } finally {
            conn.disconnect()
        }
    }

}
