package abdel.ysf.container;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SimpleRequest {

    private BufferedReader in ;
    private String method;
    private String path;
    private Map<String,String> headers = new HashMap<>();
    private Map<String,String> requestParameters = new HashMap<>();


    public SimpleRequest(BufferedReader in ){
        this.in = in;

    }
    // parsing the request into corresponding variables
    public boolean parse() throws IOException {
        String line = this.in.readLine();//-> example of the first line of the req (2 structures possible)
                                    //  GET /login?user=abdel&pwd=abdel HTTP/1.1
                                    //  GET /login HTTP/1.1
        String[] firstLineArray = line.split(" ");
        if(firstLineArray.length != 3)  return false; // cannot handle this req

        method= firstLineArray[0];
        String url = firstLineArray[1];
            int queryStringIndex = url.indexOf("?");
            // if the it contains parameters
         if( queryStringIndex >-1){
                path = url.substring(0,queryStringIndex);
                parseRequestParameters(url.substring(queryStringIndex+1,url.length()-1));
         }else{
             path=url;
         }

         while (!line.isEmpty()){
             line =in.readLine(); // request headers
             if(!"".equals(line.trim())){
                 String[] headerPair = line .split(":");
                 headers.put(headerPair[0],headerPair[1]);
             }
         }

         if("POST".equals(method)){
             StringBuilder  requestBody = new StringBuilder();
             while(in.ready()){
                 requestBody.append((char) in.read());
             }
           //  System.out.println(requestBody.toString());
             parseRequestParameters( requestBody.toString());
         }

         // TODO : parse POST request body into parameters
         return true;
    }

    // get the value of a request parameter
    public  String getRequestParameter(String parameterName){
        return  requestParameters.get(parameterName);
    }

    //queryString : user=abdel&pwd=abdel
    private void parseRequestParameters(String quesryString){

        for(String pair: quesryString.split("&")){
            String[] pairArr = pair.split("=");
            requestParameters.put(pairArr[0],pairArr[1]);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }
}
