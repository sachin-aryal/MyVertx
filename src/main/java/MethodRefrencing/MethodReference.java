package MethodRefrencing;

/**
 * Author: SACHIN
 * Date: 4/26/2016.
 */
public class MethodReference {
    public static void main(String[] args) {
        MethodReference reference = new MethodReference();
        reference.doSomething(reference::handleMethodR,reference::handleMethodR,reference::handleMethodR);
    }

    public void doSomething(iMethod method,iMethod2 method2,iMethod3 method3){
        method.handle();
        System.out.println(method2.handle("My Name"));
        System.out.println(method3.handle(7));
    }

    interface iMethod{
        void handle();
    }

    interface iMethod2{
        String handle(String val);
    }

    interface iMethod3<T>{
        T handle(T elem);
    }

    public void handleMethodR(){
        System.out.println("Void");
    }

    public String handleMethodR(String val){
        return val;
    }

    public <T> T handleMethodR(T elem){
        return elem;
    }



}
