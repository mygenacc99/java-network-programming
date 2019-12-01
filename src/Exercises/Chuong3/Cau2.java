package Exercises.Chuong3;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Cau2 {
    public static void main(String arg[ ])
    {
    int i;
    InputStream bis;
    //Nhap URL
    Scanner _sc = new Scanner(System.in);
    System.out.print("Nhap URL :");
    String url = _sc.nextLine();

    try
    {
        URL u = new URL(url);
        bis = (InputStream)u.getContent();
        while((i=bis.read())>0)
            System.out.print((char)i);
        System.out.println();
    }
    catch(MalformedURLException  e){
        System.out.println(e);
    }
    catch (IOException e)
    {
      System.out.println(e);
    }
    }

}