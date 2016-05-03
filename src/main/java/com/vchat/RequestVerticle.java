package com.vchat;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.PermittedOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;

/**
 * Author: SACHIN
 * Date: 4/15/2016.
 */
public class RequestVerticle extends AbstractVerticle {

    @Override
    public void start(){
//        startServer();
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);


        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);
        BridgeOptions options = new BridgeOptions();
        options.addInboundPermitted(new PermittedOptions().setAddress("client-to-server"));
        options.addOutboundPermitted(new PermittedOptions().setAddress("server-to-client"));
        sockJSHandler.bridge(options);


        router.route("/message").handler(sockJSHandler);

        sockJSHandler.bridge(options);

        EventBus eb = vertx.eventBus();

        eb.consumer("client-to-server",objectMessage -> {
            System.out.println("Sending Message "+objectMessage.body());
            eb.send("server-to-client",objectMessage.body());
        });


        router.route("/").handler(rtx->{
            System.out.println("Default Request");
//            String path = rtx.request().path().equals("/") ? "login.html" : rtx.request().path();
           rtx.response()
                   .putHeader("content-type", "text/html")
                   .sendFile("webroot/login.html");
        });


    }

    public void startServer(){
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
