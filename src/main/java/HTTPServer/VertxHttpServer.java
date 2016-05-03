package HTTPServer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class VertxHttpServer extends AbstractVerticle{
    private HttpServer httpServer = null;
    private HttpServerResponse response;


    @Override
    public void start(Future<Void> fut){
        httpServer = vertx.createHttpServer();
        System.out.println("Deployed Successfully");
        httpServer.requestHandler(httpServerRequest -> {
            System.out.println("Requested to http server");

            /*if(httpServerRequest.method()== HttpMethod.POST){
                httpServerRequest.handler(new Handler<Buffer>() {
                    @Override
                    public void handle(Buffer buffer) {

                    }
                });

                httpServerRequest.endHandler(buffer -> {
                    // here you can access the
                    // fullRequestBody Buffer instance.
                });
            }*/

            response = httpServerRequest.response();
            response.setStatusCode(200);
            response.headers()
                    .add("Content-Length", String.valueOf(57))
                    .add("Content-Type", "text/html");
            response.write("Vert.x is alive!");
            response.end("Response Closed");
            /*httpServerRequest.uri();
            httpServerRequest.path();*/
//            System.out.println(httpServerRequest.getParam("P1"));
        });

        httpServer.listen(9000, result -> {
            if (result.succeeded()) {
                System.out.println("Completed");
                fut.complete();
            } else {
                System.out.println("Failed");
                fut.fail(result.cause());
            }
        });

//        httpServer.close();
    }
}
