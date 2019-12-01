package Exercises.Chuong3;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;



public class Cau1 {
public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String temp = null;
        URL url = null;
        do {
            try {
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
}
