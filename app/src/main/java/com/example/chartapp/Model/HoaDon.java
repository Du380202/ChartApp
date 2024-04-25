package com.example.chartapp.Model;

public class HoaDon {
    private String maHoaDon;
    private String maDonHang;
    private String ngayTao;
    private int tongGiaTri;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String maDonHang, String ngayTao, int tongGiaTri) {
        this.maHoaDon = maHoaDon;
        this.maDonHang = maDonHang;
        this.ngayTao = ngayTao;
        this.tongGiaTri = tongGiaTri;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTongGiaTri() {
        return tongGiaTri;
    }

    public void setTongGiaTri(int tongGiaTri) {
        this.tongGiaTri = tongGiaTri;
    }
}
