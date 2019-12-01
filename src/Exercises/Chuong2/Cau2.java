package Exercises.Chuong2;

import java.util.Scanner;

public class Cau2 {
    static class HinhChuNhat{
        private double chieuDai = 0;
        private double chieuRong = 0;
        private double dienTich = 0;
        private double chuVi = 0;

        public double getChieuDai() {
            return chieuDai;
        }

        public void setChieuDai(double chieuDai) {
            this.chieuDai = chieuDai;
        }

        public double getChieuRong() {
            return chieuRong;
        }

        public void setChieuRong(double chieuRong) {
            this.chieuRong = chieuRong;
        }

        public double getDienTich() {
            return dienTich;
        }

        public void setDienTich(double dienTich) {
            this.dienTich = dienTich;
        }

        public double getChuVi() {
            return chuVi;
        }

        public void setChuVi(double chuVi) {
            this.chuVi = chuVi;
        }
        public void tinhChuVi(){
            chuVi = (chieuDai+chieuRong)*2;
        }
        public void tinhDienTich(){
            dienTich = chieuDai*chieuRong;
        }
    }

    static HinhChuNhat hcn;

    static class Nhap extends Thread{
        Scanner scan;
        Nhap(){
            scan = new Scanner(System.in);
        }

        @Override
        public void run() {
            System.out.println("Nhap vao chieu dai: ");
            double d = 0;
            double r = 0;
            while(d==0){
                d = scan.nextDouble();
                if(d==0){
                    System.out.println("Chieu dai phai khac 0! Nhap lai:");
                }
            }
            hcn.setChieuDai(d);
            System.out.println("Nhap vao chieu rong: ");

            while(r==0){
                r = scan.nextDouble();
                if(r==0){
                    System.out.println("Chieu rong phai khac 0! Nhap lai:");
                }
            }
            hcn.setChieuRong(r);
            System.out.println("Nhap chieu rong than cong!");
        }
    }

    public static class TinhChuVi extends Thread{
        @Override
        public void run() {
            while(hcn.getChieuDai() * hcn.getChieuRong() == 0){
                System.out.println("Chua co du lieu de tinh chu vi!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            hcn.tinhChuVi();
        }
    }
    public static class TinhDienTich extends Thread{
        @Override
        public void run() {
            while(hcn.getChieuDai() * hcn.getChieuRong() == 0){
                System.out.println("Chua co du lieu de tinh dien tich!");
                                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            hcn.tinhDienTich();
        }
    }
    public static class LuongChinh extends Thread{
        public int dem;
        LuongChinh(){
            dem = 0;
            hcn = new HinhChuNhat();
            new Nhap().start();
            new TinhChuVi().start();
            new TinhDienTich().start();
        }

        @Override
        public void run() {
            while(dem!=2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(hcn.getDienTich() !=0 ){
                    System.out.println("Hinh chu nhat co dien tich la: " + hcn.getDienTich());
                    dem++;
                    hcn.setDienTich(0);
                }
                if(hcn.getChuVi() !=0 ){
                    System.out.println("Hinh chu nhat co dien tich la: " + hcn.getChuVi());
                    dem++;
                    hcn.setChuVi(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        new LuongChinh().start();
    }
}
