package Router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;

/**
 * Author: SACHIN
 * Date: 4/8/2016.
 */
public class SecondReouteServer extends AbstractVerticle{
    @Override
    public void start(){
        System.out.println("Second Server Deployed Successfully");
        vertx.createHttpServer().requestHandler(httpServerRequest -> {
            HttpServerResponse response = httpServerRequest.response();
            response.sendFile("webroo/1/index.html");
        }).listen(8090);

    }
}
