package simpleVertx;

import io.vertx.core.AbstractVerticle;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class EventBusSenderVerticle extends AbstractVerticle{

    @Override
    public void start(){
        vertx.eventBus().publish("thisAddress","New Published Message");
        vertx.eventBus().send("thisAddress","Send Message");
    }
}
