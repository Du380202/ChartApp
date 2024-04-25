package com.example.chartapp.Model;

public class ChiTietDonHang {
    private int idCT;
    private String maDonHang;
    private String maSanPham;
    private int soLuong;
    private int tongTien;

    public int getIdCT() {
        return idCT;
    }

    public void setIdCT(int idCT) {
        this.idCT = idCT;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int idCT, String maDonHang, String maSanPham, int soLuong, int tongTien) {
        this.idCT = idCT;
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }
}
