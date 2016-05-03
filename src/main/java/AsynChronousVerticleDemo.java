import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

/**
 * Author: SACHIN
 * Date: 4/20/2016.
 */
public class AsynChronousVerticleDemo extends AbstractVerticle{
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new AsynChronousVerticleDemo());
        System.out.println("Deployed Successfully");
    }

    public void start(Future<Void> startFuture){
        for(int i=0;i<1000;i++){
            System.out.println(i);
        }
        vertx.close();
    }
}
