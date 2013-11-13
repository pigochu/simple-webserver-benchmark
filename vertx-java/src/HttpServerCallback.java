import org.vertx.java.core.http.HttpServerRequest;


public interface HttpServerCallback {
	public abstract void handle(HttpServerRequest req);
}
