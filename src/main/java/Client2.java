import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        Memory memory = new Memory();

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
        Socket socket = new Socket(memory.getHost(), memory.getPort());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        //отправлять инфу на сервер и получать ответ есть ли такой юзер




        // делать проверку в кейсах, на сегодня хватит, я заебался

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);

        String status;
        switch (choice){
            case 1:
                User user = new User(login, password);

                boolean result = memory.checkUser(user);

                if(!result) {

                    out.writeObject(user);
                    out.flush();
                    System.out.println("Account created");
                    status = "created";
                    printWriter.println(status);

                } else {
                    System.out.println("Account already exists");
                }
                break;
            case 2:
                User user1 = new User(login, password);

                boolean result1 = memory.checkUser(user1);
                if (result1){
                    System.out.println("Welcome");
                    status = "entered";
                    printWriter.println(status);
                } else{
                    System.out.println("Error 404 - not found");
                }
                break;
        }
        sc.close();
        socket.close();
        out.close();
        br.close();
        printWriter.close();
    }
}
