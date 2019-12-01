package Exercises.Chuong2;

public class Cau5 extends Thread {
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static Prime prime = new Prime();

    public static void main(String[] args) {
        Cau5 findPrime = new Cau5();
        findPrime.start();

        SumPrime lSum = new SumPrime(prime);
        lSum.start();
    }

    @Override
    public void run() {
        for (int i = 2; i <= 1000; i++) {
            if (isPrime(i) || i == 1000) {
                prime.setPrime(i);
            }
        }
    }
}

class Prime {
    int num;
    boolean ban = false;

    public Prime() {
        num = 0;
    }

    public synchronized void setPrime(int i) {
        if (ban) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ban = true;
        num = i;
        notify();
    }

    public synchronized int getPrime() {
        if (!ban) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ban = false;
        notify();
        return num;
    }
}

class SumPrime extends Thread {
    int sum = 0;
    Prime prime;

    public SumPrime(Prime prime) {
        sum = 0;
        this.prime = prime;
    }

    @Override
    public void run() {
        while (true) {
            if (prime.getPrime() == 1000) {
                System.out.println("Sum until now:" + sum);
                break;
            }
            sum += prime.getPrime();
        }
    }

}