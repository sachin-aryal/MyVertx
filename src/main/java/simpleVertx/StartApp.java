package simpleVertx;

import io.vertx.core.Vertx;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class StartApp {
    public static void main(String[] args) throws InterruptedException {

        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(new simpleVertx.MyVerticle());

        /*vertx.deployVerticle(new simpleVertx.MyVerticle(), stringAsyncResult -> {
            System.out.println("BasicVerticle deployment complete");
        });*/
        vertx.deployVerticle(new EventBusReceiverVerticle());
        vertx.deployVerticle(new SecondEventBusReceiver());
        System.out.println("Message will sent after 3 second");
        Thread.sleep(3000);
        vertx.deployVerticle(new EventBusSenderVerticle());

    }
}
