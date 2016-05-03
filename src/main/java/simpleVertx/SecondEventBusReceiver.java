package simpleVertx;

import io.vertx.core.AbstractVerticle;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class SecondEventBusReceiver extends AbstractVerticle{

    @Override
    public void start(){
        vertx.eventBus().consumer("thisAddress",message->{
            System.out.println("R2 received message.body() = "
                    + message.body());
                });
    }

}
