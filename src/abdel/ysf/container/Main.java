package abdel.ysf.container;

import java.io.IOException;

public class Main {


    public static  void  main(String args[]) throws IOException {

        SimpleServletContainer container = new SimpleServletContainer(8888,"config.properties");
        container.loadPropertiesFile();

        // calling the destroy() method  to clean up resources before shutting down
        Runtime.getRuntime().addShutdownHook(new Thread(){

            @Override
            public void run() {
                System.out.println("hiii");
                container.handlers.forEach((key,servlet)-> {
                    servlet.destroy();
                });
            }
        });

        container.start();

    }
}
