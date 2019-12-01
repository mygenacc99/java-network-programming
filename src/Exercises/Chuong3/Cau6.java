package Exercises.Chuong3;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.util.Scanner;

public class Cau6 {

    public void Cau1(){

    }

    public static void main(String[] args) throws IOException {
        Scanner _sc = new Scanner(System.in);
        System.out.print("Nhap URL :");
        String ad = _sc.nextLine();
        URL url = new URL(ad);
        String hostN = url.getHost();
        InetAddress ia = InetAddress.getByName(hostN);
        String ipN = ia.getHostAddress();


        boolean check = false;

        BufferedReader br = new BufferedReader(new FileReader("/home/colab/IdeaProjects/Baitap/src/Chuong3/cau6.data"));
        String host;
        String ip;
        int i = 0;
        while (((host = br.readLine()) != null) && (ip = br.readLine()) != null) {
            i++;
            if ((host.equals(hostN)) && (ip.equals(ipN))) check = true;
        }

        br.close();
        if (!check)
            try {
                //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
                File f = new File("/home/colab/IdeaProjects/Baitap/src/Chuong3/cau6.data");
                FileWriter fw = new FileWriter(f,true);
                //Bước 2: Ghi dữ liệu
                fw.write(hostN + "\n" + ipN + "\n");
                //Bước 3: Đóng luồng
                fw.close();

                System.out.println("Da cap nhat danh sach");
            } catch (IOException ex) {
                System.out.println("Loi ghi file: " + ex);
            }
        else{
            System.out.println("Trang web da duoc luu");
        }
    }
}
