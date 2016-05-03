package HttpClient;

import io.vertx.core.AbstractVerticle;

/**
 * Author: SACHIN
 * Date: 4/6/2016.
 */
public class VertxTimer extends AbstractVerticle{
    @Override
    public void start(){

        vertx.setPeriodic(60_00, timerID -> vertx.deployVerticle(new VertxClientServer()));
    }
    }
