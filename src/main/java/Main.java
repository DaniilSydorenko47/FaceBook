import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException{

        Memory memory = new Memory();


        ServerSocket serverSocket = new ServerSocket(memory.getPort());

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Accept");
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                User user = (User)in.readObject();
                boolean checkUser = memory.checkUser(user);
                if (checkUser){
                    System.out.println("User already exists");
                } else {
                    memory.add(user);
                }
                memory.print();

//                User user = (User) in.readObject();
//                boolean checkUser = memory.checkUser(user);
//                if (checkUser){
//                    System.out.println("User already exists");
//                } else{
//                    memory.add(user);
//                }

//                in.close();
//                socket.close();
//                serverSocket.close();

            }catch (Exception e){
                e.getMessage();
            }

        }
    }
}
