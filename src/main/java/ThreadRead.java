import java.io.BufferedReader;
import java.io.IOException;


public class ThreadRead extends Thread{
    private BufferedReader in;
    private String message;
    public ThreadRead(BufferedReader in){
        this.in = in;
    }

    @Override
    public void run() {
        try {
            message = in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public String getMessage(){
        return message;
    }
}


