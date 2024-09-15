import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.IOException;
import java.io.PrintWriter;

public class Handler extends ServletHandler {
    @Override
    public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AsyncContext asyncContext = baseRequest.startAsync();
        asyncContext.setTimeout(0);
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            try {
                Thread.sleep(15);
            } catch (InterruptedException ignored) {
            }
            PrintWriter writer = response.getWriter();
            writer.println("A very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very very long response");
            writer.close();
            asyncContext.complete();
        } finally {
            baseRequest.setHandled(true);
        }
    }
}
