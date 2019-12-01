package Exercises.Chuong4.Cau4;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
   public static int SERVER_PORT = 6969;

    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        InetAddress server = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Login \n2. Register \nIf you had an account, select 1. Else select 2");

        int select = sc.nextInt();
        sc.nextLine();
        String username = "";
        String password = "";

        boolean finish = false;

        while (!finish) {

            if (select == 1) {
                System.out.println("=========================== LOGIN =============================");
            } else {
                System.out.println("========================= REGISTER ========================");
            }
            System.out.print("Username: ");
            username = sc.nextLine();
            System.out.print("Password: ");
            password = sc.nextLine();

            String request = select + "|" + username + "|" + password;

            byte[] data = request.getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length, server,  SERVER_PORT);
            ds.send(dp);
            byte[] buffer = new byte[6000];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            ds.receive(incoming);

            String reply = new String(incoming.getData(), 0, incoming.getLength());
            if( reply.contains("successfully")) finish = true;
            System.out.println(reply);
        }
    }
}
