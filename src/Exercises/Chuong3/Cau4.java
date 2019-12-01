package Exercises.Chuong3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Scanner;

public class Cau4 {
    public static void main(String[] args) throws IOException {
        System.out.println("Host name:");
        Scanner _sc = new Scanner(System.in);
        String aName = _sc.nextLine();
        aName = "https://" + "google.com";
        URL u = new URL(aName);
        URLConnection uc = u.openConnection();
        long ngayTao = uc.getDate();
        long ngaySua = uc.getLastModified();
        long ngayHH  = uc.getExpiration();
        System.out.println("Ngay tao: " + (ngayTao == 0 ? "Khong ro!" : (new Date(ngayTao))));
        System.out.println("Ngay chinh sua sau cung: " + (ngaySua == 0 ? "Khong ro!" : (new Date(ngaySua))));
        System.out.println("Ngay het han:" + (ngayHH == 0 ? "Khong ro!" : (new Date(ngayHH))));
    }
}
