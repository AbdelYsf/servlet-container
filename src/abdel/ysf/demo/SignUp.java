package abdel.ysf.demo;

import abdel.ysf.container.SimpleHttpServlet;
import abdel.ysf.container.SimpleRequest;
import abdel.ysf.container.SimpleResponse;

import java.io.PrintWriter;

public class SignUp extends SimpleHttpServlet {

    @Override
    public void init() {
        System.out.println("init() signup called ....");

    }

    @Override
    protected void doGet(SimpleRequest request, SimpleResponse response) {
        PrintWriter printWriter = response.getPrintWriter();
        printWriter.println("<html><body>");
        printWriter.println("<h1>signup</h1>");
        printWriter.println("<form method=\"post\">\n" +
                "  <label for=\"fname\">First name:</label><br>\n" +
                "  <input type=\"text\" id=\"fname\" name=\"fname\"><br>\n" +
                "  <label for=\"lname\">Last name:</label><br>\n" +
                "  <input type=\"text\" id=\"lname\" name=\"lname\">\n" +
                "<br><input type=\"submit\" value=\"Submit\">\n"+
                "</form>");
        printWriter.println("</body></html>");
    }

    @Override
    protected void doPost(SimpleRequest request, SimpleResponse response) {
        PrintWriter printWriter = response.getPrintWriter();
        printWriter.println("<html><body>");

        printWriter.println("<h1>your parameters are: </h1>");
        printWriter.println("firstName: "+request.getRequestParameter("fname"));
        printWriter.println("<br>lastName: "+request.getRequestParameter("lname"));
        printWriter.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("clean up resources in signup destroy() called .....");
    }
}
