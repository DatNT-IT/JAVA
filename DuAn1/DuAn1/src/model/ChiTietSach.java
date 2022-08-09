package model;

public class ChiTietSach {
    private int maSach, soTrang, soLuong, lanTaiBan, duocPhepMuon;
    private String tenSach, ngayNhap, tinhTrang, tenVT, tenLS, tenTG, tenNXB;
    private int giaBia;

    public ChiTietSach() {
    }

    public ChiTietSach(int maSach, String tenSach) {
        this.maSach = maSach;
        this.tenSach = tenSach;
    }

    public ChiTietSach(int soTrang, String tenVT, int soLuong, String tenLS, String tenTG, String tenNXB, int lanTaiBan, int duocPhepMuon, String tenSach, String ngayNhap, String tinhTrang, int giaBia) {
        this.soTrang = soTrang;
        this.tenVT = tenVT;
        this.soLuong = soLuong;
        this.tenLS = tenLS;
        this.tenTG = tenTG;
        this.tenNXB = tenNXB;
        this.lanTaiBan = lanTaiBan;
        this.duocPhepMuon = duocPhepMuon;
        this.tenSach = tenSach;
        this.ngayNhap = ngayNhap;
        this.tinhTrang = tinhTrang;
        this.giaBia = giaBia;
    }

    public ChiTietSach(int maSach, int soTrang, String tenVT, int soLuong, String tenLS, String tenTG, String tenNXB, int lanTaiBan, int duocPhepMuon, String tenSach, String ngayNhap, String tinhTrang, int giaBia) {

        this.maSach = maSach;
        this.soTrang = soTrang;
        this.tenVT = tenVT;
        this.soLuong = soLuong;
        this.tenLS = tenLS;
        this.tenTG = tenTG;
        this.tenNXB = tenNXB;
        this.lanTaiBan = lanTaiBan;
        this.duocPhepMuon = duocPhepMuon;
        this.tenSach = tenSach;
        this.ngayNhap = ngayNhap;
        this.tinhTrang = tinhTrang;
        this.giaBia = giaBia;
    }


    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    public String getTenLS() {
        return tenLS;
    }

    public void setTenLS(String tenLS) {
        this.tenLS = tenLS;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getLanTaiBan() {
        return lanTaiBan;
    }

    public void setLanTaiBan(int lanTaiBan) {
        this.lanTaiBan = lanTaiBan;
    }

    public int getDuocPhepMuon() {
        return duocPhepMuon;
    }

    public void setDuocPhepMuon(int duocPhepMuon) {
        this.duocPhepMuon = duocPhepMuon;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(int giaBia) {
        this.giaBia = giaBia;
    }

    @Override
    public String toString() {
        return maSach + "";
    }
}
