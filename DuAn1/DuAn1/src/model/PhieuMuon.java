package model;

public class PhieuMuon {
    private int maPhieuMuon, maCTS, maDG;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPhieuMuon, int maCTS, int maDG) {
        this.maPhieuMuon = maPhieuMuon;
        this.maCTS = maCTS;
        this.maDG = maDG;
    }

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public int getMaCTS() {
        return maCTS;
    }

    public void setMaCTS(int maCTS) {
        this.maCTS = maCTS;
    }

    public int getMaDG() {
        return maDG;
    }

    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }
}
