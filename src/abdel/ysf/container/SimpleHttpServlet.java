package abdel.ysf.container;

public class SimpleHttpServlet {

    public void init(){
        System.out.println("abdel.ysf.container.SimpleHttpServlet Init default impl ...");
    }


    public void service(SimpleRequest request , SimpleResponse response){
            String method = request.getMethod();

            if("GET".equals(method)){
                this.doGet(request,response);
            }else if("POST".equals(method)){
                this.doPost(request,response);
            }else{
                throw  new RuntimeException("the method of this request is not supported yet");
            }

    }

    protected  void doGet(SimpleRequest request , SimpleResponse response){
        System.out.println("abdel.ysf.container.SimpleHttpServlet doGet default impl ...");

    }

    protected  void doPost(SimpleRequest request , SimpleResponse response){
        System.out.println("abdel.ysf.container.SimpleHttpServlet doPost default impl ...");

    }
}
