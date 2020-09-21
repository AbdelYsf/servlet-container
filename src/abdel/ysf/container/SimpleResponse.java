package abdel.ysf.container;

import java.io.OutputStream;
import java.io.PrintWriter;

public class SimpleResponse {

    private OutputStream out;
    private PrintWriter printWriter;

    public SimpleResponse(OutputStream out){
        this.out = out;
        this.printWriter = new PrintWriter(out);
    }

    public OutputStream getOutputStream() {
        return out;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}
