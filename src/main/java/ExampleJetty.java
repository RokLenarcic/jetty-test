import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.BlockingArrayQueue;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

public class ExampleJetty {

    private static QueuedThreadPool createThreadpool() {
        return new QueuedThreadPool(50, 8, 60000, new BlockingArrayQueue<>(8, 8, Integer.MAX_VALUE));
    }

    private static Connector createConnector(Server server, int port) {
        HttpConfiguration httpConfig = new HttpConfiguration();
        httpConfig.setSendDateHeader(true);
        httpConfig.setOutputBufferSize(32768);
        httpConfig.setRequestHeaderSize(8192);
        httpConfig.setResponseHeaderSize(8192);
        httpConfig.setSendServerVersion(false);

        HttpConnectionFactory factory = new HttpConnectionFactory(httpConfig);
        ServerConnector connector = new ServerConnector(server, factory);
        connector.setPort(port);
        connector.setHost(null);
        connector.setIdleTimeout(200000);
        return connector;
    }

    private static Server createServer(int port) {
        Server server = new Server(createThreadpool());
        server.addConnector(createConnector(server, port));
        return server;
    }

    private static void runServer() throws Exception {
        Server server = createServer(4003);
        server.setHandler(new Handler());
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            server.stop();
            throw e;
        }

    }
    public static void main(String[] args) throws Exception {
        runServer();
    }
}
