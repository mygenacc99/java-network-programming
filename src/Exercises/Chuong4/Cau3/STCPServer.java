package Exercises.Chuong4.Cau3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class STCPServer {
    public static int DEFAULT_PORT = 6969;
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(DEFAULT_PORT);
        String msg1 = "";
        String msg2 = "";
        while (true){
            System.out.println("Server is waiting...");
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            BufferedWriter bWrite = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
            String mess = bReader.readLine();
            if(!mess.isEmpty()){
                if(mess.charAt(1) == '1'){ // If the user1's message
                    System.out.println("Got connected to user1");

                    Date date = new Date(); // this object contains the current date value
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    msg1 += "["+ formatter.format(date) + "] " + mess + "@";

                    if(!msg2.isEmpty()){
                        bWrite.write(msg2 + "\n");
                        bWrite.flush();
                        System.out.println("The user2' message was sended to user1");
                        msg2 = "";
                    }
                }
                else{
                    System.out.println("got connected to user2");
                    Date date = new Date(); // this object contains the current date value
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    msg2 += "["+ formatter.format(date) + "] " + mess +"@";

                    if(!msg1.isEmpty()){
                        bWrite.write(msg1 + "\n");
                        bWrite.flush();
                        System.out.println("The user1' message was sended to user2");
                        msg1="";
                    }
                }
            }

            s.close();
        }
    }
}
