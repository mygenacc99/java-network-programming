package Exercises.Chuong4.Cau4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class STCPServer {
    public static int DEFAULT_PORT = 6969;
    public static String fileName = "/home/colab/IdeaProjects/Chuong4/src/Exercises/Chuong4/Cau4/accounts.txt";
    public static Map<String, String> accounts = null;

    /**
     * @return Map<String, String>: the pair of username and password.
     * @throws IOException
     */
    public static Map<String, String> getDataFile() throws IOException {
        Map<String, String> accounts = new HashMap<>();

        BufferedReader bRead = new BufferedReader(new FileReader(fileName));
        String line = "";
        while ((line = bRead.readLine())!=null) {
            if(line.isEmpty()) continue;
            String splited[] = line.split("\\|");
            accounts.put(splited[0], splited[1]);
        }
        bRead.close();
        return accounts;
    }

    public static void addAccount(String username, String password) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(username+ "|" + password +"\n");
        accounts.put(username, password);
        fileWriter.close();
    }

    public static String requestProcessing(String request) throws IOException {
        String reply = "";
        String[] slipted = request.split("\\|");
        System.out.println(slipted.length);
        // Check login.
        if (slipted[0].equals("1")) {
            if (accounts.get(slipted[1]) != null) {
                if (accounts.get(slipted[1]).equals(slipted[2])) reply = "Login successfully!";
                else reply = "The username or password is wrong! Please check it again!";
            } else reply = "The username or password is wrong! Please check it again!";
        } else { // Register
            if (accounts.containsKey(slipted[1])) {
                reply = "The username already exists. Please use a different username!";
            } else {
                addAccount(slipted[1], slipted[2]);
                reply = "Register successfully!";
            }
        }
        return reply;
    }

    public static void main(String[] args) throws IOException {
        accounts = getDataFile();
        System.out.println("Loaded Data");
        ServerSocket ss = new ServerSocket(DEFAULT_PORT);

        while (true) {
            System.out.println("Server is waiting to connect...");
            Socket socket = ss.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            PrintWriter writer = new PrintWriter(os, true);
            String request = reader.readLine();
            String reply = requestProcessing(request);
            writer.println(reply);
        }

    }
}
