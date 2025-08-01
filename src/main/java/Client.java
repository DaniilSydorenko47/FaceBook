import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;


public class Client {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to FaceBook!");
        System.out.println("1. Create an account");
        System.out.println("2. Sign in");
        Integer choice = sc.nextInt();
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
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


        switch (choice){
            case 1:
                User user = new User(login,password);

                CompletableFuture.runAsync(() -> {

                    try {
                        //Отправка обьекта
                        out.writeObject(user);
                        out.flush();
                        //Отправка выбора
                        out.writeObject(choice);
                        out.flush();
                        //Получение
                        String line = (String) in.readObject();
                        System.out.println("Ответ сервера: " + line);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                });
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                break;
            case 2:
                User user1 = new User(login,password);

                CompletableFuture.runAsync(() -> {
                    try {
                        //Отправка обьекта
                        out.writeObject(user1);
                        out.flush();
                        //Отправка выбора
                        out.writeObject(choice);
                        out.flush();
                        //Получение
                        String line = (String) in.readObject();
                        System.out.println("Ответ сервера: " + line);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.getMessage();
                    }

                });
                try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
                break;
            default:
                System.out.println("Incorrect choice");
        }

        sc.close();
        socket.close();
        out.close();

    }
}
