package HTTPServer;

import io.vertx.core.Vertx;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class Demo {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VertxHttpServer());
    }
}
