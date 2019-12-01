package Exercises.Chuong4.Cau1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static int DEFAULT_PORT = 9999;
    public static String DEFAULT_HOSTNAME = "localhost";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so nguyen:");
        String mes = sc.nextLine();
        try {
            Socket s = new Socket(DEFAULT_HOSTNAME, DEFAULT_PORT);
            System.out.println("Da ket noi");
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(mes+"\n");
            bw.flush();
            String rs = br.readLine();
            System.out.println(rs);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
