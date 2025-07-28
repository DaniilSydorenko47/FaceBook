import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

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

        switch (choice){
            case 1:
                User user = new User(login,password);
                out.writeObject(user);
                out.flush();
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
