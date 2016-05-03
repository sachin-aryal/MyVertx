package EventBusTest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * Author: SACHIN
 * Date: 4/27/2016.
 */
public class EventBusDemo extends AbstractVerticle {
    public static void main(String[] args) {
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(new EventBusDemo());
    }

    public void start(){
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        router.route("/message").handler(this::eventBusMet);
        router.route("/message").handler(this::eventBusMet1);
        router.route("/myServer").handler(rtx->{
           rtx.response().sendFile("C:\\Users\\deerwalk\\IdeaProjects\\New VChat");
            rtx.response().end();
        });

        EventBus eventBus  = vertx.eventBus();
        eventBus.consumer("place",mes->{
            System.out.println((JsonObject)mes.body());
            mes.reply("Success");
        });
    }

    public void eventBusMet(RoutingContext context){
        System.out.println("Method 1 Called");
        EventBus eventBus = vertx.eventBus();
        eventBus.send("place",new JsonObject().put("name","Sachin"),reply->{
            context.response().setChunked(true);
            context.response().write(reply.result().body().toString());
            context.next();
//            context.response().end();
        });
    }

    public void eventBusMet1(RoutingContext context){
        System.out.println("Method 2 Called");
        EventBus eventBus = vertx.eventBus();
        eventBus.send("place",new JsonObject().put("name","Sachin"),reply->{
            context.response().setChunked(true);
            context.response().write(reply.result().body().toString());
            String myName = "This is it";
            context.response().end("<h1>My Name is Sachin Aryal</h1>");
        });
    }
}
