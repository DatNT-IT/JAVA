package model;

public class ChiTietPhieuMuon {
    private int maMuon, maCTS, maND;
    private String ngayMuon, hanTra;

    public ChiTietPhieuMuon() {
    }

    public ChiTietPhieuMuon(int maMuon, int maCTS, int maND, String ngayMuon, String hanTra) {
        this.maMuon = maMuon;
        this.maCTS = maCTS;
        this.maND = maND;

        this.ngayMuon = ngayMuon;
        this.hanTra = hanTra;
    }

    public int getMaMuon() {
        return maMuon;
    }

    public void setMaMuon(int maMuon) {
        this.maMuon = maMuon;
    }

    public int getMaCTS() {
        return maCTS;
    }

    public void setMaCTS(int maCTS) {
        this.maCTS = maCTS;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return hanTra;
    }

    public void setNgayTra(String hanTra) {
        this.hanTra = hanTra;
    }
}
