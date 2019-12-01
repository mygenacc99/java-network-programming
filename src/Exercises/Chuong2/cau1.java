package Chuong2.Baitap;

public class cau1 {

    public static class LuongSoLe extends Thread{
        int n;
        LuongSoLe(int n){
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 1; i < n; i++){
                if (i%2 == 1) System.out.println("Số lẻ bé hơn " + n + " là: " +i);
            }
        }
    }

    public static class LuongSoChan extends Thread{
        int n;
        LuongSoChan(int n){
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 1; i < n; i++){
                if (i%2 == 0) System.out.println("Số chẵn bé hơn " + n + " là: " + i);
            }
        }
    }

    public static class LuongSoTuNhien extends Thread{
        int n;
        LuongSoTuNhien(int n){
            this.n = n;
        }

        @Override
        public void run() {
            for (int i = 1; i <= n; i++){
                if (i%2 == 0) System.out.println("Số bé hơn hoặc bằng " + n + " là: " + i);
            }
        }
    }

    public static class InASCII extends Thread{

        @Override
        public void run() {
            for (char c = 'A'; c <= 'Z'; c++){
                System.out.println("Ký tự ASCII: " + c);
            }
        }
    }

    public static void main(String[] args) {
        int n;
        LuongSoLe l1 = new LuongSoLe(100);
        LuongSoChan l2 = new LuongSoChan(100);
        LuongSoTuNhien l3 = new LuongSoTuNhien(100);
        InASCII l4 = new InASCII();
        l1.start();
        l2.start();
        l3.start();
        l4.start();
    }
}
