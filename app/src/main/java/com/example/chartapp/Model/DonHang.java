package com.example.chartapp.Model;

public class DonHang {
    private String maDonHang;
    private String TenDonHang;
    private String ngayDat;
    private String ngayGiao;
    private boolean trangThai;
    private String diaChi;
    private String sdt;
    private String maKhachHang;

    public DonHang() {
    }

    public DonHang(String maDonHang, String tenDonHang, String ngayDat, String ngayGiao, boolean trangThai, String diaChi, String sdt, String maKhachHang) {
        this.maDonHang = maDonHang;
        TenDonHang = tenDonHang;
        this.ngayDat = ngayDat;
        this.ngayGiao = ngayGiao;
        this.trangThai = trangThai;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.maKhachHang = maKhachHang;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTenDonHang() {
        return TenDonHang;
    }

    public void setTenDonHang(String tenDonHang) {
        TenDonHang = tenDonHang;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }
}
