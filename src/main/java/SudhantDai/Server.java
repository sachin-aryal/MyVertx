package SudhantDai;

import io.netty.handler.codec.http.QueryStringDecoder;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Server extends AbstractVerticle {

  // Convenience method so you can run it i\  \\  n your IDE
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Server());
  }

  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    vertx.createHttpServer().requestHandler(router::accept).listen(8080);

    router.route().handler(BodyHandler.create());
    router.route("/").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response
              .putHeader("content-type", "text/html")
              .end("<h1>Hello from my first Vert.x 3 application</h1>");
    });

    router.route("/test").handler(this::test);

    router.route("/something").handler(new Handler<RoutingContext>() {
      @Override
      public void handle(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","text/html")
                .end("<h1>this is a test with anonymous class</h1>");
      }
    });

  }

  private void test(RoutingContext routingContext){
    HttpServerResponse response = routingContext.response();

    if(routingContext.request().query()!=null) {
      System.out.println("querystring");
      QueryStringDecoder queryStringDecoder = new QueryStringDecoder(routingContext.request().query(), false);
      System.out.println(queryStringDecoder.parameters());
    }
    System.out.println(routingContext.request().formAttributes());

    if(routingContext.getBodyAsString() !=null && !routingContext.getBodyAsString().trim().isEmpty()){
      System.out.println("body");
      System.out.println(routingContext.getBodyAsJson());
    }
    response
            .putHeader("content-type", "text/html")
            .end("<h1>accessing url test</h1>");

  }
}