package Exercises.Chuong2;
import java.util.Random;

public class Cau3 {

    /**
     * @param args the command line arguments
     */
    static public int i;

    static class ThreadA extends Thread {

        @Override
        public void run() {
            while (true) {
                Random gen = new Random();
                synchronized (this) {
                    i = gen.nextInt(21);
                    System.out.println("Thread A: " + i);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
                System.out.println("Thread B: " + Math.pow(i, 2));

            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new ThreadA().start();
        new ThreadB().start();
    }
}
