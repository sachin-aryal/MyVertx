package AuthenticationHandlerEx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * Author: SACHIN
 * Date: 4/13/2016.
 */
public class AuthHandler extends AbstractVerticle{
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new AuthHandler());

    }

    public void start(){
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);


    }


    public void auth(){
        /*JsonObject config = new JsonObject().put("properties_path", "classpath:test-auth.properties");

        AuthProvider provider = ShiroAuth.create(vertx, ShiroAuthRealmType.PROPERTIES, config);

        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        AuthHandler basicHandler = (AuthHandler) BasicAuthHandler.create(provider);


// All requests to paths starting with '/private/' will be protected
//        router.route("/private*//*").handler(routingContext);

        router.route("/someotherpath").handler(routingContext -> {

            // This will be public access - no login required

        });

        router.route("/private/somepath").handler(routingContext -> {

            // This will require a login

            // This will have the value true
            boolean isAuthenticated = routingContext.user() != null;

        });
*/
    }

}
