package model;

public class PhieuTra {
    private int maPhieuTra, maCTS, maDG;

    public PhieuTra() {
    }

    public PhieuTra(int maPhieuTra, int maCTS, int maDG) {
        this.maPhieuTra = maPhieuTra;
        this.maCTS = maCTS;
        this.maDG = maDG;
    }

    public int getMaPhieuTra() {
        return maPhieuTra;
    }

    public void setMaPhieuTra(int maPhieuTra) {
        this.maPhieuTra = maPhieuTra;
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
