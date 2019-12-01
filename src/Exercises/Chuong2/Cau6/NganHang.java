
package Exercises.Chuong2.Cau6;

public class NganHang {
    private double balance;
    boolean ban = false;
    void NganHang(){
        balance = 0.0;
    }
    public synchronized void setBalance(double amount){
        if(ban)
            try{
                wait();
            }
            catch(InterruptedException e){
                System.out.println();
            }
        balance = amount;
        ban = true; //khoa khong cho ghi de
        notify();
    }
    public synchronized double getBalance(){
        if(!ban)
            try{
                wait();
            }
            catch(InterruptedException e){
                System.out.println();
            }
        ban = false; // mo khoa de cap nhat
        notify();
        return balance;
    }
}
class XuLy2 implements Runnable {
    NganHang tk;
    double st;
    String mag;
    int l;
    Thread t;
    public XuLy2(NganHang x, double y, String str){
        tk = x;
        st = y;
        mag = str;
        t = new Thread(this);
        t.start();
    }
    public void run(){
        tk.setBalance(st);
        System.out.println(" So tien cua tai khoan "+mag+" la: "+tk.getBalance());
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            return;
        }

    }
    public static void main(String[] args){
        NganHang tkh = new NganHang();
        XuLy2 t1 = new XuLy2(tkh,15,"Hoa");
        XuLy2 t2 = new XuLy2(tkh,30,"Hong");
        XuLy2 t3 = new XuLy2(tkh,45,"Hue");
    }
}

