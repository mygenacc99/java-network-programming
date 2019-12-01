package Exercises.Chuong2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Cau4 {
    static ArrayList<Integer> list = new ArrayList<Integer>();
    static int a = 0, b = 0;

    public static class XuLy extends Thread {
        int start, end;

        XuLy(int a, int b) {
            start = a;
            end = b;
        }

        synchronized boolean checkPrime(int n) {
            if (n < 2) return false;
            else {
                for (int i = 2; i * i <= n; i++) {
                    if (n % i == 0) return false;
                }
            }
            return true;
        }
//        synchronized void addList(int i){
//            list.add(i);
//        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (checkPrime(i)) {
                    synchronized (list) {
                        list.add(i);
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        a = scan.nextInt();
        b = scan.nextInt();
        int mid = (a + b) / 2;
        XuLy l1 = new XuLy(a, mid);
        XuLy l2 = new XuLy(mid + 1, b);
        l1.start();
        l2.start();
        while (l1.isAlive() || l2.isAlive()) {
        }

        Collections.sort(list);

        try {
            Writer file = new FileWriter("Cau4Data.txt");
            for (int i : list) {
                System.out.println(i);
                file.write(String.valueOf(i));
            }

            System.out.println("success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
