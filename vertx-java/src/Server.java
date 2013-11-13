import java.util.HashMap;
import java.util.Map;
import org.vertx.java.core.json.JsonObject;

import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.platform.Verticle;

public class Server extends Verticle {

	private HashMap<String,HttpServerCallback> routes = new HashMap();
	
	
	public void start() {
		HttpServer server = vertx.createHttpServer();
		routes.put("/helloworld" , new HelloWorldCallback());
		routes.put("/sendfile1" , new Sendfile1Callback());
		routes.put("/sendfile2" , new Sendfile2Callback());
		routes.put("/arrayjson" , new ArrayJsonCallback());
		
		server.requestHandler(new Handler<HttpServerRequest>() {
			public void handle(HttpServerRequest req) {
				
				// System.out.println(req.path());
				String path = req.path();
				
				if(routes.containsKey(path)) {
					routes.get(path).handle(req);
				} else {
					// no register route , do something for default ....
					req.response().end();
				}

			}
		});

		server.listen(8080);
	}
	
	
	private class HelloWorldCallback implements HttpServerCallback {
		@Override
		public void handle(HttpServerRequest req) {
			// req.response().headers().set("Content-Type", "text/plain");
	        req.response().end("Hello World!");
			
		}
	}
	
	
	private class Sendfile1Callback implements HttpServerCallback {
		@Override
		public void handle(HttpServerRequest req) {
			req.response().sendFile("../../files/sendfile1.html");
		}
	}
	
	private class Sendfile2Callback implements HttpServerCallback {
		@Override
		public void handle(HttpServerRequest req) {
			req.response().sendFile("../../files/sendfile2.html");
		}
	}
	
	
	private class ArrayJsonCallback implements HttpServerCallback {
		@Override
		public void handle(HttpServerRequest req) {
			int[] arr1 = new int[100];
			int[] arr2 = new int[100];
			String[] arr3 = new String[100];
			for(int i=0; i<100; i++) {
				arr1[i] = i;
				arr2[i] = i*(-1);
				arr3[i] = "s : " + i;
			}
			HashMap map = new HashMap();
			map.put("a" , arr1);
			map.put("b" , arr2);
			map.put("c" , arr3);
			
			JsonObject jsonObjectJacky = new JsonObject(map);
			req.response().end(jsonObjectJacky.toString());
			
		}
	}
	
}