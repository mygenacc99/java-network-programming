package Exercises.Chuong4.Cau2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static String DEFAULT_HOSTNAME = "localhost";
    public static int DEFAULT_PORT = 9999;
    public static String Standardlize(String mess){

//        mess = mess.trim().replaceAll(" +", " ");
        String[] s = mess.split(" ");
        return s[1] + " " + s[0] + " " + s[2];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào biểu thức:");
        String mess = sc.nextLine();
        try {
            Socket s = new Socket(DEFAULT_HOSTNAME, DEFAULT_PORT);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));

            mess = Standardlize(mess); // Chuẩn hóa theo cú pháp nhận của server.
            mess = mess.trim().replaceAll(" +", " ");

            System.out.println("Gửi đến server chuối: " + mess);
            bWrite.write(mess+"\n");
            bWrite.flush();

            String rs = bReader.readLine();
            System.out.println("Kết quả trả về từ server:" + rs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
