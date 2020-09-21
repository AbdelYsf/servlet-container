package abdel.ysf.container;

import java.io.IOException;

public class Main {


    public static  void  main(String args[]) throws IOException {

        SimpleServletContainer container = new SimpleServletContainer(8888,"config.properties");
        container.loadPropertiesFile();
//        container.handlers.forEach((url,Servlet)->{
//            System.out.println(url);
//            Servlet.doGet();
//        } );
        container.start();
    }
}
