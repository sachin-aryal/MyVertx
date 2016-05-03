package SockJSDemo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.*;

/**
 * Author: SACHIN
 * Date: 4/13/2016.
 */
public class SockJsDemo extends AbstractVerticle{
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SockJsDemo());
    }

    @Override
    public void start(){

        Router router = Router.router(vertx);

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);


        BridgeOptions options = new BridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddress("some.address"))
                .addOutboundPermitted(new PermittedOptions().setAddress("some.address"));


        sockJSHandler.bridge(options);

        router.route("/eventbus/*").handler(rtx->{
            System.out.println("Method Called webroot");
        });


        sockJSHandler.bridge(options, be -> {
            System.out.println("Receiving Message");
            System.out.println(be.rawMessage());
//            if (be.type() == BridgeEvent.Type.PUBLISH.PUBLISH || be.type() == BridgeEvent.Type.RECEIVE.RECEIVE) {
////                if (be.getRawMessage().getString("body").equals("armadillos")) {
////                    // Reject it
////                    be.complete(false);
////                    return;
////                }
//            }
            be.complete(true);
        });


//        sockJSHandler.socketHandler(sockJSSocket -> {
//            System.out.println("Method Called");
//            sockJSSocket.handler(sockJSSocket::write);
//        });


    }

    public void simpleSockJs(){
        Router router = Router.router(vertx);

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        SockJSHandlerOptions sockJSHandlerOptions = new SockJSHandlerOptions().setHeartbeatInterval(2000);

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx,sockJSHandlerOptions);

        router.route("/myapp/*").handler(sockJSHandler);

        sockJSHandler.socketHandler(sockJSSocket -> {
            sockJSSocket.handler(sockJSSocket::write);
        });

    }
}