//package SudhantDai;
//
//import io.vertx.core.AbstractVerticle;
//import io.vertx.core.Vertx;
//import io.vertx.core.http.HttpClientOptions;
//import io.vertx.core.http.HttpClientRequest;
//
// * @author <a href="http://tfox.org">Tim Fox</a>
//
//
//public class Client extends AbstractVerticle {
//
//    // Convenience method so you can run it in your IDE
//    public static void main(String[] args) {
//        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(new Client());
//    }
//
//    @Override
//    public void start() throws Exception {
// System.out.println("something somethi");
//      HttpClientOptions httpClientOptions = new HttpClientOptions();
//      httpClientOptions.setDefaultHost("asm.deerwalk.com").setDefaultPort(7071);
//      HttpClient client = vertx.createHttpClient();
//
//
//String host = "localhost";
//      Integer port = 8080;
//      String queryString = "/test?something=test";
//
//      String host = "asm.deerwalk.com";
//      Integer port = 7071;
//      String queryString = "/assessmentService/assessment/test?q=hello";
//
//      HttpClientRequest request = client.get(port, host,queryString, resp -> {
//                  System.out.println("Got response " + resp.statusCode());
//                  resp.bodyHandler(body -> {
//                      System.out.println("Got data " + body.toString("ISO-8859-1"));
//                  });
//              });
//      request.headers().add("Content-Type", "plain/text");
//      request.setChunked(true);
//
//     request.write("{'test':'test'}");
//      JsonObject jo = new JsonObject();
//      jo.put("test","something");
//
//      request.end();
//
//
//JsonObject jo = new JsonObject();
//      jo.put("username","imuser");
//      jo.put("password","imuser123");
//      jo.put("hostUrl","customer.yala.deerwalk.com");
//
//        HttpClientRequest request = vertx.createHttpClient(new HttpClientOptions().setSsl(true).setTrustAll(true)).post(8444, "login.deerwalk.com", "/cas/v1/tickets/TGT-5-pWmaAueJ6J0igFeFDmueeqO75hafmHOX6Mz1Rn1BFRNmReM7P7-cas", resp -> {
//            System.out.println("Got response " + resp.statusCode());
//            resp.bodyHandler(body -> {
//                System.out.println("Got data " + body.toString("ISO-8859-1"));
//            });
//        });
//        request.headers().add("Content-Type", "application/x-www-form-urlencoded");
//        request.headers().add("Accept","plain/text");
//        //  request.setChunked(true);
//
//        //request.write("'service':'https://asm.deerwalk.com:8447/receptor','username':'imuser','password':'imuser123','hostUrl':'customer.yala.deerwalk.com'");
//        // System.out.println(URLEncoder.encode(jo.toString(),"UTF-8"));
//        // request.write(URLEncoder.encode(jo.toString(),"UTF-8"));
//
//        // request.end("username=imuser&password=imuser123&hostUrl=customer.yala.deerwalk.com");
//
//        request.end("service=https://notification.deerwalk.com:8446/");
//
//
//
//
//
//
//
//     this is working
//
//
// HttpPost httpPost = new HttpPost("https://login.deerwalk.com:8444/cas/v1/tickets");
//      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//      nvps.add(new BasicNameValuePair("username", "imuser"));
//      nvps.add(new BasicNameValuePair("password", "imuser123"));
//      nvps.add(new BasicNameValuePair("hostUrl", "customer.yala.deerwalk.com"));
//
//      httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
//       org.apache.http.client.HttpClient httpClient = getHttpClient();
//      HttpResponse response = httpClient.execute(httpPost);
//
//      String entity = EntityUtils.toString(response.getEntity());
//
//      System.out.println(entity);
//
//
//
//
//      service ticket working approach
//
//
//
//HttpPost httpPost = new HttpPost("https://login.deerwalk.com:8444/cas/v1/tickets/TGT-2-wNJBGIb7KIVls7MxmtQjCgFz9NX50Lz1g5hMa3Paltjd7GUjKX-cas");
//      List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//      nvps.add(new BasicNameValuePair("service", "https://notification.deerwalk.com:8446/"));
//
//      httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
//      org.apache.http.client.HttpClient httpClient = getHttpClient();
//      HttpResponse response = httpClient.execute(httpPost);
//
//      String entity = EntityUtils.toString(response.getEntity());
//
//      System.out.println(entity);
//
//
//
//
//        //ST-3-s4cZFSgdTBI9sube3A6b-cas
//
//
//
//    }
//
////    static org.apache.http.client.HttpClient getHttpClient() throws Exception {
////
////        SSLContext sslContext = SSLContext.getInstance("SSL");
////        sslContext.init(null, null, null);
////
////        /// sslContext.init(null, [nullTrustManager as X509TrustManager] as TrustManager[], null)
////
////        SSLSocketFactory sf = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
////
////        Scheme httpsScheme = new Scheme("https", 443, sf);
////        SchemeRegistry schemeRegistry = new SchemeRegistry();
////        schemeRegistry.register(httpsScheme);
////
////        // apache HttpClient version >4.2 should use BasicClientConnectionManager
////        BasicClientConnectionManager cm = new BasicClientConnectionManager(schemeRegistry);
////        org.apache.http.client.HttpClient httpClient = new DefaultHttpClient(cm);
////
////        return httpClient;
////    }
//}
