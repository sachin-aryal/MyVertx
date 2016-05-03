import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * Author: SACHIN
 * Date: 4/6/2016.
 */

public class ServerI extends AbstractVerticle{
    private HttpServerResponse response;
    private Router router;
    @Override
    public void start() {
        router = Router.router(vertx);
        System.out.println("Server Deployed Successfully");
        vertx.createHttpServer().listen(8000);

        router.route().handler(routingContext -> {

        });
                /*.requestHandler(req -> {
            response = req.response();
            System.out.println(req.path());
            String file = req.path().equals("/") ? "index.html" : req.path();
            String message = req.getParam("message");
            try{
                if(file.contains("startChat")){
                    response.headers().add("Access-Control-Allow-Origin","*").add("Content-Length",String.valueOf(message.length()));
                    response.write(message);
                    response.end();
                }else{
                    response.sendFile("webroot/" +file);
                }
            }catch (Exception er){
                er.printStackTrace();
                System.out.println("Some error occurred");
            }
        }).listen(8080);*/
    }
}
