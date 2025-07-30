import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to FaceBook!");
        System.out.println("1. Create an account");
        System.out.println("2. Sign in");
        int choice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your email: ");
        String login = sc.nextLine();
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        Socket socket = null;
        try {
            socket = new Socket("localhost", 8345);
        } catch(ConnectException e){
            e.getMessage();
        }

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        switch (choice){
            case 1:
                User user = new User(login,password);
                out.writeObject(user);
                out.flush();
                CompletableFuture.runAsync(() -> {
                    try {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println("Ответ сервера: " + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // Чтобы клиент не закрылся сразу
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                break;
            case 2:
                User user1 = new User(login,password);
                out.writeObject(user1);
                out.flush();
                CompletableFuture.runAsync(() -> {
                    try {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println("Ответ сервера: " + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

                break;
        }


//        switch (choice){
//            case 1:
//                User user = new User(login,password);
//                out.writeObject(user);
//                out.flush();
//                break;
//            case 2:
//                status = "join";
//                User user1 = new User(login,password);
//                out.writeObject(user1);
//                out.flush();
//
//                try {
//                    User getUser = (User) in.readObject();
//                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
//                }




        sc.close();
        socket.close();
        out.close();

    }
}
