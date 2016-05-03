package simpleVertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class EventBusReceiverVerticle extends AbstractVerticle{

    public void start(Future<Void> startFuture) {

        vertx.eventBus().consumer("thisAddress", message -> {
            System.out.println("R1 received message.body() = "
                    + message.body());
        });
    }
}
