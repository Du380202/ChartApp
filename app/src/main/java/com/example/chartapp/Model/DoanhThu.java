package com.example.chartapp.Model;

public class DoanhThu {
    private int thang;
    private Double tong;

    public DoanhThu() {
    }

    public DoanhThu(int thang, Double tong) {
        this.thang = thang;
        this.tong = tong;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public Double getTong() {
        return tong;
    }

    public void setTong(Double tong) {
        this.tong = tong;
    }
}
