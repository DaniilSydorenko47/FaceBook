import java.io.*;
import java.net.Socket;

public class ThreadSendResponse {
    private Socket socket;
    public ThreadSendResponse(Socket socket){
        this.socket=socket;
    }
    public void handleClient(){
        try(ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            User user = (User)in.readObject();
            Memory memory = new Memory();
            System.out.println("Получен пользователь: " + user);
            boolean exists = memory.checkUser(user);// пример проверки
            String message;
            if(exists){
                message = "User already exists";

            } else{
                message="User added";
                memory.add(user);
            }
            pw.println(message);
            pw.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
