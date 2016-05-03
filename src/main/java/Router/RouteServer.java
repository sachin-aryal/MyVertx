package Router;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Author: SACHIN
 * Date: 4/8/2016.
 */
public class RouteServer extends AbstractVerticle{
    Router router;
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new RouteServer());
    }

    @Override
    public void start(){
        System.out.println("Server Deployed Successfully");

        router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);


        router.route().handler(BodyHandler.create());
        router.route("/").handler(routingContext -> {
           /* HttpServerResponse response = routingContext.response();
            response.sendFile("webroot/index.html");*/
            vertx.deployVerticle(new SecondReouteServer());
        });

        router.route("/getData").handler(routingContext -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("name","sachin");
            jsonObject.put("rollNo",0211);
            String jsobBody = jsonObject.toString();
           HttpServerResponse response = routingContext.response();
            response.putHeader("content-type","json").putHeader("Access-Control-Allow-Origin","*").putHeader("content-length",String.valueOf(jsobBody.length()));
            response.write(jsobBody);
            response.end();
        });

    }
}
