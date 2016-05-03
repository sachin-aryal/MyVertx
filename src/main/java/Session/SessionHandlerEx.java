package Session;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.ClusteredSessionStore;
import io.vertx.ext.web.sstore.SessionStore;

/**
 * Author: SACHIN
 * Date: 4/11/2016.
 */
public class SessionHandlerEx extends AbstractVerticle {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SessionHandlerEx());
    }

    public void start(){
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        router.route().handler(CookieHandler.create());

        SessionStore store = ClusteredSessionStore.create(vertx);

        SessionHandler sessionHandler = SessionHandler.create(store);

        router.route().handler(sessionHandler.setNagHttps(true));

        router.route("/test").handler(routingContext -> {
           Session session =  routingContext.session();
            String name = routingContext.request().getParam("name");
            session.put("name",name);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.end("Saved To Session");
        });

        router.route("/getName").handler(routingContext -> {
            Session session =  routingContext.session();
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            System.out.println("Get name is "+session.get("name"));
//            response.end("Getname is "+String.valueOf(session.get("name")));
        });

    }

}
