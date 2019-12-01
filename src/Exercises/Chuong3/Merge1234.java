package Exercises.Chuong3;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.net.*;

public class Merge1234 {
    public static void Cau1() {
        Scanner scan = new Scanner(System.in);
        String temp = null;
        URL url = null;
        do {
            try {
                System.out.println("URL: ");
                url = new URL(scan.nextLine());

                System.out.println("URL is " + url);
                System.out.println("The protocol part is " + url.getProtocol());
                System.out.println("The port part is " + url.getPort());
                System.out.println("The host part is " + url.getHost());
                System.out.println("The file part is " + url.getFile());

                System.out.println("Continue on? Y/N");
                temp = scan.nextLine();
            } catch (MalformedURLException e) {
                System.out.println(e);
            }
        }
        while (temp.equals("y") || temp.equals("Y"));
    }

    public static void Cau2() {
        int i;
        InputStream bis;
        //Nhap URL
        Scanner _sc = new Scanner(System.in);
        System.out.print("Nhap URL :");
        String url = _sc.nextLine();

        try {
            URL u = new URL(url);
            bis = (InputStream) u.getContent();
            while ((i = bis.read()) > 0)
                System.out.print((char) i);
            System.out.println();
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void Cau3() {
        System.out.println("Host name:");
        Scanner _sc = new Scanner(System.in);
        String aName = _sc.nextLine();
        InetAddress i;
        try {
            i = InetAddress.getLocalHost();
            System.out.println("The localhost is: " + i.getHostAddress());
            i = InetAddress.getByName(aName);
            System.out.println("The Host address is: " + i.getHostName());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void Cau4() throws IOException {
        System.out.println("Host name:");
        Scanner _sc = new Scanner(System.in);
        String aName = _sc.nextLine();
        aName = "https://" + "google.com";
        URL u = new URL(aName);
        URLConnection uc = u.openConnection();
        long ngayTao = uc.getDate();
        long ngaySua = uc.getLastModified();
        long ngayHH = uc.getExpiration();
        System.out.println("Ngay tao: " + (ngayTao == 0 ? "Khong ro!" : (new Date(ngayTao))));
        System.out.println("Ngay chinh sua sau cung: " + (ngaySua == 0 ? "Khong ro!" : (new Date(ngaySua))));
        System.out.println("Ngay het han:" + (ngayHH == 0 ? "Khong ro!" : (new Date(ngayHH))));
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Chon cau (1,2,3,4): ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Cau1();
                break;
            case 2:
                Cau2();
                break;
            case 3:
                Cau3();
                break;
            case 4:
                Cau4();
                break;
        }
    }
}
