package abdel.ysf.container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Map;

public class SocketHandler extends Thread {

    private Socket socket;
    private final Map<String, SimpleHttpServlet> handlers;

    public SocketHandler(Socket socket ,Map<String,SimpleHttpServlet> handlers){

        this.socket = socket;
        this.handlers=handlers;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        PrintWriter out =null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            SimpleRequest request = new SimpleRequest(in);
            //if cannot parse the request
            if(!request.parse()){
                out = new PrintWriter(socket.getOutputStream());
                // writing the response header
                out.println("http/1.1 500 Internal Server Error");
                out.println("Content-Type: text/html");
                out.println();// separator \r\n
                out.println("<html><body> Current Time:");
                out.println(" cannot process your request");
                out.println("</body></html>");
                out.flush();
            }else{

                // get the servlet that match the path asked by the client
                SimpleHttpServlet httpServlet = handlers.get(request.getPath());

                if(httpServlet ==null){
                    out = new PrintWriter(socket.getOutputStream());
                    // writing the response header
                    out.println("http/1.1 404 Not Found");
                    out.println("Content-Type: text/html");
                    out.println();// separator \r\n
                    out.println("<html><body> Current Time:");
                    out.println("404 Resources Not Found ");
                    out.println("</body></html>");
                    out.flush();
                }else{
                    SimpleResponse response = new SimpleResponse(socket.getOutputStream());
                    PrintWriter writer= response.getPrintWriter();
                    writer.println("HTTP/1.1 200 OK");
                    writer.println("Content-Type: text/html");
                    writer.println();

                    httpServlet.service(request,response);
                    writer.flush();
                }


            }
//              String line = in.readLine();
//            // displaying the request in the console
//            while(! line.isEmpty()){
//                System.out.println(line);
//                line = in.readLine();
//            }

//            System.out.println("Method :" + request.getMethod());
//            System.out.println("Path :" + request.getPath());
//            request.getRequestParameters().forEach((key,value) -> System.out.println("param "+key+" : "+ value));
//            request.getHeaders().forEach((key,value) -> System.out.println("Header "+key+" : "+ value));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
