import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = new ServerSocket(8345);
        ExecutorService pool = Executors.newCachedThreadPool();


        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Accept");
                pool.submit(() -> handleClient(socket));



            }catch (Exception e){
                e.getMessage();
            }

        }
    }
    public static void handleClient(Socket socket) {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

             ) {
            Memory memory = new Memory();
            //Получение пользователя
            User user = (User) in.readObject();

            System.out.println("Получен пользователь: " + user);
            //Чтение выбора


            // проверка пользователя
            boolean exists = memory.checkUser(user);// пример проверки
            String message;
            if(exists){
                message = "User already exists";
            } else{
                message="User added";
                memory.add(user);
            }
            memory.print();
            // Отправляем ответ
            out.writeObject(message);
            out.flush();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
