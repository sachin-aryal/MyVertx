package AllRouter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;

import java.util.Set;

/**
 * Author: SACHIN
 * Date: 4/9/2016.
 */
public class Start extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Start());
    }

    public void start(){
            fileUpload();
//        mimeType();
//        allCombine();
//        nextRouting();
//        withBlocker();
//        withParameter();
//        withMethods();// Router is executed in order they are in the code or we can also define the order of the router
// request handle
//        subRouters();
//        localization();
//        errorHanding();

    }


    public void cookieHandler(){
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        router.route().handler(CookieHandler.create());

        router.get("/cookie").handler(rtx->{
            Cookie cookie = rtx.getCookie("myCookie");
            String val = cookie.getValue();
            rtx.addCookie(Cookie.cookie("othercookie", "somevalue"));
        });
    }

    public void fileUpload(){
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        router.route().handler(BodyHandler.create());

        router.get("/upload/file").handler(rtx->{
            Set<FileUpload> uploads = rtx.fileUploads();
        });


    }

    public void errorHanding(){

        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);


        Route route = router.get("/somepath");

        route.handler(rtx->{
            rtx.fail(504);
        });

//        route.failureHandler(frc->{
//
//            // This will be called for failures that occur
//            // when routing requests to paths starting with
//            // '/somepath/'
//            System.out.println("Error");
//
//        });

    }

    public void localization(){

        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);

        Route route = router.get("/localized").handler(rc->{
            // although it might seem strange by running a loop with a switch we
            // make sure that the locale order of preference is preserved when
            // replying in the users language.

//            rc.acc

            // we do not know the user language so lets just inform that back:
//            rc.response().end("Sorry we don't speak: " + rc.preferredLocale());
        });
    }

    public void subRouters(){

        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);

        Router restAPI = Router.router(vertx);

        restAPI.get("/products/:productID").handler(rc-> {

            // TODO Handle the lookup of the product....
//            rc.response().write(productJSON);

        });

        restAPI.put("/products/:productID").handler(rc-> {

            // TODO Add a new product...
            rc.response().end();

        });

        restAPI.delete("/products/:productID").handler(rc-> {

            // TODO delete the product...
            rc.response().end();

        });


        Router mainRouter = Router.router(vertx);
        mainRouter.mountSubRouter("/productsAPI", restAPI);//accessible from /productsAPI/products/product1234


// Handle static resources
        mainRouter.route("/static/*").handler(ctx->{

        });

        mainRouter.route(".*\\.templ").handler(ctx->{

        });


    }

    public void allCombine(){
        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);

        Route route = router.route(HttpMethod.PUT, "myapi/orders").consumes("application/json").produces("application/json");
        route.handler(routingContext-> {

            // This would be match for any PUT method to paths starting with "myapi/orders" with a
            // content-type of "application/json"
            // and an accept header matching "application/json"

        });


    }

    public void mimeType(){
        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);

        router.route().produces("application/json").handler(routingContext -> {
            System.out.println(routingContext.getAcceptableContentType());
        });
    }

    public void withMethods(){
        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);


        Route route = router.route("/").method(HttpMethod.GET);//Works only for get method
        route.handler(routingContext -> {

        });

        Route route1 = router.route("/api").method(HttpMethod.GET);//Works only for get method with path /api
        route.handler(routingContext -> {

        });

        router.post("/api").handler(routingContext -> {  //POST method for /api url

        });

        router.route().method(HttpMethod.GET).method(HttpMethod.POST).handler(routingContext -> {});//Works for POST and GET Method



    }

    public void withParameter(){

        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);

        Route route = router.route(HttpMethod.GET,"/param/:id/:name");//localhost:8080/param/211/sachin
        route.handler(routingContext -> {
            System.out.println("Name is: "+routingContext.request().getParam("name"));
            System.out.println("Id is: "+routingContext.request().getParam("id"));
        });

    }


    public  void withBlocker(){
        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);

        router.route().blockingHandler(routingContext -> {
            System.out.println("Task that take a lot of time");//Doing it synchronously
        });
    }

    public void nextRouting(){
        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router::accept).listen(8080);

        Route route1 = router.route("/");
        route1.handler(routingContext->{
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            System.out.println("Routing webroot");
            response.write("Routing Number webroot");
            routingContext.next();
            routingContext.put("name","Sachin");
//            routingContext.vertx().setTimer(5000,tid->{
//                System.out.println("Requesting after some delay");
//            });
        });

        Route route3 = router.route("/another");
        route3.handler(routingContext->{
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            System.out.println("Routing 3");
            response.write("Routing Number 3");
            routingContext.response().end();
//            routingContext.vertx().setTimer(5000,tid->{
//                System.out.println("Requesting after some delay");
//                routingContext.next();
//            });
        });

        Route route2 = router.route("/");
        route2.handler(routingContext->{
            HttpServerResponse response = routingContext.response();
            System.out.println("Routing 2");
            System.out.println((String) routingContext.get("name"));
            response.write("Routing Number 2");
//            routingContext.reroute();
        });

//        route2.order(0);//Second
//        route1.order(webroot);//Third
//        route3.order(-webroot);//First
    }



}
