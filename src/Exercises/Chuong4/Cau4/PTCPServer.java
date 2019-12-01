package Exercises.Chuong4.Cau4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class PTCPServer {
    private int DEFAULT_PORT = 6969;
    public static String fileName = "/home/colab/IdeaProjects/Network Programming/src/Exercises/Chuong4/Cau4/accounts.txt";
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

    public void addAccount(String username, String password) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(username+ "|" + password +"\n");
        synchronized (accounts) {
            accounts.put(username, password);
        }
        fileWriter.close();
    }

    public String requestProcessing(String request) throws IOException {
        String reply = "";
        String[] slipted = request.split("\\|");
        System.out.println(slipted.length);
        // Check login.
        synchronized (accounts){
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
        }
        return reply;
    }

    public void execute() throws IOException {

        ServerSocket ss = new ServerSocket(DEFAULT_PORT);

        while (true) {
            System.out.println("Server is waiting to connect...");
            Socket socket = ss.accept();

            ProcessingThread pt = new ProcessingThread(socket, this);
            pt.start();
        }
    }

    public static void main(String[] args) throws IOException {
        accounts = getDataFile();
        System.out.println("Loaded Data");
        PTCPServer server = new PTCPServer();
        server.execute();

    }
}


class ProcessingThread extends Thread{
    Socket socket;
    PTCPServer server;

    ProcessingThread(Socket socket, PTCPServer serverSocket){
        this.socket = socket;
        this.server = serverSocket;
    }

    @Override
    public void run() {
        InputStream is = null;
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            is = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            PrintWriter writer = new PrintWriter(os, true);
            String request = reader.readLine();
            String reply = server.requestProcessing(request);
            writer.println(reply);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}