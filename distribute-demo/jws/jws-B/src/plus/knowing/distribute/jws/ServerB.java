package plus.knowing.distribute.jws;

import javax.xml.ws.Endpoint;

public class ServerB {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/jws", new ServerHandler());
    }
}
