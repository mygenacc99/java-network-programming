package Exercises.Chuong4.Cau3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PTCPServer {
    public static int DEFAULT_PORT = 6969;


    public static void main(String[] args) {
        try {
            //Tạo socket cho server
            ServerSocket ss = new ServerSocket(DEFAULT_PORT);
            while (true) {
                try {
                    Socket s = ss.accept(); // Lắng nghe các yêu cầu kết nối
                    // Tạo phần xử lý
                    RequestProcessing rp = new RequestProcessing(s);
                    rp.start(); // Khởi động phần xử lý cho Client hiện tại
                } catch (IOException e) {
                    System.out.println("Connection Error: " + e);
                }
            }
        } catch (IOException e) {
            System.err.println("Create Socket Error: " + e);
        }
    }

}

class RequestProcessing extends Thread {

    Socket channel;

    public RequestProcessing(Socket s) {
        channel = s;
    }

    public void run() {
        try {
            InputStream is = channel.getInputStream();
            OutputStream os = channel.getOutputStream();

            BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader bRead = new BufferedReader(new InputStreamReader(is));
            Thread.sleep(5000);
            // Doc chuoi duoc gui tu Client

        } catch (
                IOException | InterruptedException e) {
            e.printStackTrace();

        }
    }
}

