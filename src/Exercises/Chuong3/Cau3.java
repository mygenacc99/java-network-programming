package Exercises.Chuong3;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
public class Cau3 {
    public static void main(String[] args) {
        System.out.println("Host name:");
        Scanner _sc = new Scanner(System.in);
        String aName = _sc.nextLine();
        InetAddress i;
        try{
            i=InetAddress.getLocalHost();
            System.out.println("The localhost is: "+ i.getHostAddress());
            i=InetAddress.getByName(aName);
            System.out.println("The Host address is: "+i.getHostName());
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
