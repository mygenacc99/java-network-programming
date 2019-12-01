package Exercises.Chuong4.Cau4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static String DEFAULT_HOSTNAME = "localhost";
    public static int DEFAULT_PORT = 6969;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Login \n2. Register \nIf you had an account, select 1. Else select 2");

        int select = sc.nextInt();
        sc.nextLine();
        String username = "";
        String password = "";

        boolean finish = false;

        while (!finish){

            if(select == 1){
                System.out.println("=========================== LOGIN =============================");
            }
            else{
                System.out.println("========================= REGISTER ========================");
            }
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();

            String request = select +"|" + username +"|" + password;

            Socket socket = new Socket(DEFAULT_HOSTNAME, DEFAULT_PORT);


            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            PrintWriter writer = new PrintWriter(os, true);
            writer.println(request);
            String reply = reader.readLine();
            System.out.println(reply);
            if(reply.contains("successfully")) finish = true;
            socket.close();
        }

    }
}
