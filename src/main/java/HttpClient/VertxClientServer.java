package HttpClient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;

/**
 * Author: SACHIN
 * Date: 4/5/2016.
 */
public class VertxClientServer extends AbstractVerticle{

    @Override
    public void start(){

        System.out.println("Getting Request for every one second");

        HttpClient httpClient = vertx.createHttpClient();

        httpClient.getNow(9000, "localhost", "/", httpClientResponse -> {
            System.out.println("Response received");
            httpClientResponse.handler(buffer -> {
                System.out.println(httpClientResponse.getHeader("Content-Length"));
                System.out.println("Response (" + buffer.length() + "): ");
                System.out.println(buffer.getString(0, buffer.length()));
            });
//            httpClientResponse.bodyHandler(buffer -> {
//                System.out.println("Response (" + buffer.length() + "): ");
//                System.out.println(buffer.getString(0, buffer.length()));
//            });
        });
    }

}
