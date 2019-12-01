package Exercises.Chuong4.Cau2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int DEFAULT_PORT = 9999;

    public static String toNum(String mess) {
        String[] s = mess.split(" ");
        try {
            int a = Integer.parseInt(s[1]);
            int b = Integer.parseInt(s[2]);
            switch (s[0]){
                case "+": return Integer.toString(a+b);
                case "-": return Integer.toString(a - b);
                case "*": return Integer.toString(a * b);
                case "/": return Double.toString((double)a/b);
            }
        }
        catch (NumberFormatException e){
            return "Biểu thức nhập không đúng!";
        }
        return "Lỗi";
    }

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(DEFAULT_PORT);
            while (true) {
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(os));
                BufferedReader bReader = new BufferedReader(new InputStreamReader(is));

                String mess = bReader.readLine();
                System.out.println("Nhận được yêu cầu từ client: " + mess);
                String rs = toNum(mess);
                bWrite.write( rs + "\n");
                bWrite.flush();
                System.out.println("Gửi về cho client: " + rs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
