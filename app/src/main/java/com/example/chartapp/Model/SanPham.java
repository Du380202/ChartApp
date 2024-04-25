package com.example.chartapp.Model;

public class SanPham {
    private String maSP;
    private String tenSanPham;
    private String maTheLoai;
    private int soLuong;
    private int giaSP;

    public SanPham() {
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public SanPham(String maSP, String tenSanPham, String maTheLoai, int soLuong, int giaSP) {
        this.maSP = maSP;
        this.tenSanPham = tenSanPham;
        this.maTheLoai = maTheLoai;
        this.soLuong = soLuong;
        this.giaSP = giaSP;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
