package model;

public class ChiTietPhieuTra {
    private int maPhieuTra, maDG, maCTS, tientra;
    public String ngayTra, ngaymuon, hantra;


    public ChiTietPhieuTra() {
    }

    public ChiTietPhieuTra(int maPhieuTra, int maDG, int maCTS, int tientra, String ngayTra, String ngaymuon, String hantra) {
        this.maPhieuTra = maPhieuTra;
        this.maDG = maDG;
        this.maCTS = maCTS;
        this.tientra = tientra;
        this.ngayTra = ngayTra;
        this.ngaymuon = ngaymuon;
        this.hantra = hantra;

    }

    public int getMaPhieuTra() {
        return maPhieuTra;
    }

    public void setMaPhieuTra(int maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
    }

    public int getMaDG() {
        return maDG;
    }

    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    public int getMaCTS() {
        return maCTS;
    }

    public void setMaCTS(int maCTS) {
        this.maCTS = maCTS;
    }


    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getTientra() {
        return tientra;
    }

    public void setTientra(int tientra) {
        this.tientra = tientra;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getHantra() {
        return hantra;
    }

    public void setHantra(String hantra) {
        this.hantra = hantra;
    }


}
