package Exercises.Chuong4.Cau1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int DEFAULT_PORT = 9999;

    public static String DigitToWord(int n) {
        switch (n) {
            case 0:
                return "Không";
            case 1:
                return "Một";
            case 2:
                return "Hai";
            case 3:
                return "Ba";
            case 4:
                return "Bốn";
            case 5:
                return "Năm";
            case 6:
                return "Sáu";
            case 7:
                return "Bảy";
            case 8:
                return "Tám";
            case 9:
                return "Chín";
        }
        return null;
    }

    public static String NumToWord(int n) {
        String replyMess = "";
        if (n == 0) replyMess = "Không";
        else if (n > 1000000000) replyMess = "Too large!";
        else {
            int[] num = new int[3];
            int i = 0;
            while (n != 0) {
                num[i] = n % 1000;
                n /= 1000;
                i++;
            }
            while (i > 0) {
                String temp = "";

                i--;
                int tram = (num[i] / 100) % 10;
                int chuc = (num[i] / 10) % 10;
                int dv = num[i] % 10;

                if (tram == 0 && chuc == 0 & dv == 0) continue;
                if (tram != 0 || !replyMess.isEmpty()) temp += (DigitToWord(tram) + " Trăm ");
                temp += ((chuc == 0 && dv != 0) && !temp.isEmpty()) ? " Lẻ " : "";
                temp += (chuc == 1) ? " Mười " : "";
                temp += (chuc != 0 && chuc != 1) ? DigitToWord(chuc) + " Mươi " : "";
                temp += (dv != 0) ?(chuc!= 0 && dv==5)?"Lăm":DigitToWord(dv) : "";
                temp += (i == 2) ? " Triệu " : "";
                temp += (i == 1) ? " Nghìn " : "";
                replyMess += temp;
            }
        }
        return replyMess;
    }

    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(DEFAULT_PORT);
            while (true) {
                System.out.println("Chua co ket noi!");
                Socket s = ss.accept();
                System.out.println("Ket noi thanh cong!");
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();

                BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(os));
                BufferedReader bRead = new BufferedReader(new InputStreamReader(is));

                // Doc chuoi duoc gui tu Client
                String mes = bRead.readLine();
                System.out.println(mes);
                String replyMess = "";
                try {
                    System.out.println(mes);
                    int n = Integer.parseInt(mes);
                    replyMess = NumToWord(n);
                } catch (NumberFormatException e) {
                    replyMess = "Không phải số nguyên!";
                }

                replyMess = replyMess.trim().replaceAll(" +", " "); // Xoa khoang trong
                bWrite.write(replyMess + "\n");
                System.out.println("Message sent to the client is " + replyMess);
                bWrite.flush();
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
