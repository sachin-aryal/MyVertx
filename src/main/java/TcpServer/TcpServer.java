package TcpServer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class TcpServer extends AbstractVerticle{
    @Override
    public void start(){
        NetServer netServer = vertx.createNetServer();

        netServer.connectHandler(netSocket -> {
            System.out.println("Incoming Connection");
            netSocket.handler(buffer -> {
                System.out.println("Incoming Data Length is "+buffer.length());
                String data = buffer.getString(0, buffer.length());
                System.out.println(data);
                Buffer outBuffer = Buffer.buffer();
                outBuffer.appendString("response...");
                netSocket.write(outBuffer);
            });
        });
        netServer.listen(10000);
        netServer.close(voidAsyncResult -> System.out.println("TCP Closed"));
    }
}
