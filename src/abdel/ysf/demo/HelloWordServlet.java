package abdel.ysf.demo;

import abdel.ysf.container.SimpleHttpServlet;
import abdel.ysf.container.SimpleRequest;
import abdel.ysf.container.SimpleResponse;

import java.io.PrintWriter;

public class HelloWordServlet extends SimpleHttpServlet {

    @Override
    public void init() {
        System.out.println("init() HelloWorldservlet called ....");

    }

    @Override
    protected void doGet(SimpleRequest request, SimpleResponse response) {
        PrintWriter printWriter = response.getPrintWriter();
        printWriter.println("<html><body>");
        printWriter.println("<h1>do get in hello world servlet </h1>");
        printWriter.println("</body></html>");
    }

    @Override
    public void destroy() {

        System.out.println("clean up resources in hello destroy() called .....");
    }
}
