import java.io.PrintWriter;

public class ThreadSend extends Thread{
    private PrintWriter pr;
    private String message;
    public ThreadSend(PrintWriter pr, String message){
        this.pr = pr;
        this.message = message;
    }
    @Override
    public void run() {
        pr.println(message);
    }
}
