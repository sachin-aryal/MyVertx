package com.vchat;

/**
 * Author: SACHIN
 * Date: 4/15/2016.
 */
public class StartServer{
    public static void main(String[] args) {
        /*Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new RequestVerticle());*/
        iTest my = System.out::println;
        my.test("My Name is Sachin");
        String name="MY Name";
        StartServer server = new StartServer();
        /*server.doSomething(a->{
            System.out.println("Hello All");
        });*/
        server.doSomething(server::methodReference);
    }

    public void doSomething(iTest test){
        test.test("Sachin");
    }

    public void methodReference(String myName){
        System.out.println(myName);
    }


    interface iTest{
        public void test(String mes);
    }
    interface  iTest2{
        void myTest();
        void myTest(String all);
    }
}
