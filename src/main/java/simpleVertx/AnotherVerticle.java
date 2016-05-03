package simpleVertx;

import io.vertx.core.AbstractVerticle;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class AnotherVerticle extends AbstractVerticle{
    @Override
    public void start() {
        System.out.println("simpleVertx.MyVerticle started second!");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("simpleVertx.MyVerticle stopped second!");
    }
}
