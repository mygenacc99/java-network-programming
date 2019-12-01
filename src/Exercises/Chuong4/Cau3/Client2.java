package Exercises.Chuong4.Cau3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2{
    public static String DEFAULT_HOSTNAME = "localhost";
    public static int DEFAULT_PORT = 6969;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String msg = "";
        while(true){

            Socket s = new Socket(DEFAULT_HOSTNAME, DEFAULT_PORT);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));

//            if(!msg.isEmpty()){
                bWrite.write("C2: " + msg +"\n");
                bWrite.flush();
//            }

            String rs = bReader.readLine();
            if(rs != null){
                String str[] = rs.split("@");
                for (String i:str){
                    System.out.println(i);
                }
            }

            msg = sc.nextLine();
            s.close();
        }

    }
}
