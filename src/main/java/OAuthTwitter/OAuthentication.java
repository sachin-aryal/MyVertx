package OAuthTwitter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.*;
import java.net.URI;

/**
 * Author: SACHIN
 * Date: 4/29/2016.
 */
public class OAuthentication extends AbstractVerticle{

    private final String consumerKey = "8n4Fm4FVUX289HbhwIavKV59V";
    private final String consumerSecret = "vVkl6RfKPEgsff3Kz8SngDOaQ9gDfmnyk93EPwwmpJJgffhrOw";
    private final String accessToken = "725705915190517762-MfNtIJZuRTyfJz9mmWLAm1pZr1N4Y0U";
    private final String accessTokenSecret = "wlCYwpsXpYpmBM1H3OPiQdnZVBC0dPohBdVwsOC6TtBwY";
    Twitter twitter=null;
    RequestToken requestToken = null;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new OAuthentication());
    }

    public void start(){
        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
        router.route("/auth").handler(this::oAuth);
        router.route("/result").handler(this::oResult);


    }

    public void oAuth(RoutingContext routingContext){

        vertx.executeBlocking(future -> {

            twitter = TwitterFactory.getSingleton();
            String returnUrl = "http://localhost:8080/result";
            twitter.setOAuthConsumer(consumerKey, consumerSecret);
            try {
                requestToken = twitter.getOAuthRequestToken(returnUrl);
                try {
                    Desktop desktop = java.awt.Desktop.getDesktop();
                    URI oURL = new URI(requestToken.getAuthorizationURL());
                    desktop.browse(oURL);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (TwitterException e) {
                e.printStackTrace();
            }
            future.complete("Success");
        }, res -> {
            System.out.println("The result is: " + res.result());
        });

    }

    public void oResult(RoutingContext routingContext){
        System.out.println("Redirecting to the logged in url");

        vertx.executeBlocking(future -> {

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(consumerKey)
                    .setOAuthConsumerSecret(consumerSecret)
                    .setOAuthAccessToken(requestToken.getToken())
                    .setOAuthAccessTokenSecret(requestToken.getTokenSecret());
            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            User user = null;
            try {
                user = twitter.verifyCredentials();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
            future.complete("The User Screen Name is "+user.getScreenName());
        },res->{
            System.out.println(res.result());
        });


        routingContext.response().setChunked(true);
        routingContext.response().sendFile("webroot/oauth.html");
    }
}
