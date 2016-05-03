package com.vchat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * Author: SACHIN
 * Date: 4/15/2016.
 */
public class SecondServer extends AbstractVerticle{
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SecondServer());
    }

    public void start(){
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8090);

        router.route("/").handler(rtx->{
           rtx.response().sendFile("webroot/vertx-eventBus.js");
        });
        router.route("/sock").handler(rtx->{
            rtx.response().sendFile("webroo/1/sock-js.js");
        });

        router.route("/hello").handler(rtx->{

        });
    }

}
