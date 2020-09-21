package abdel.ysf.container;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SimpleServletContainer {

    private final int port;
    private final String configFileName;
    // servlet mapping
    public final Map<String, SimpleHttpServlet> handlers= new HashMap<>();

    public SimpleServletContainer(int port,String configFileName){
        this.configFileName = configFileName;
        this.port = port;
    }

    // the entry point of the application
    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(this.port);

        // keep listening to client requests
        while(true){


                 Socket socket = serverSocket.accept();
               // allocating new thread to each client while the main thread keeps listening to client's connections
               Thread  socketHandler =  new SocketHandler(socket,handlers);
               socketHandler.start();
        }
    }

    public  void  loadPropertiesFile() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.configFileName);
        if(inputStream==null){
           throw new RuntimeException("cannot find the config file");
        }
        Properties  properties =  new Properties();
        properties.load(inputStream);
        properties.forEach((key,value)-> {
            SimpleHttpServlet httpServlet = getServletInstance((String)value);
            //calling the init method of the servlet
            httpServlet.init();// normally we should pass the servletConfig as the spec mentions , but this is just a simple container
            handlers.put((String)key,httpServlet);
        });
    }

    public SimpleHttpServlet getServletInstance(String className){
        try {
            Class<?> aClass =  Class.forName(className);
            return (SimpleHttpServlet) aClass.newInstance();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
