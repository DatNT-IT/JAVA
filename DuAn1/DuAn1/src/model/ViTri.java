package model;

public class ViTri {
    private int maVT;
    private String hangSach, oSach, tuSach, moTa;

    public ViTri() {
    }


    public ViTri(String hangSach, String oSach, String tuSach, String moTa) {
        this.hangSach = hangSach;
        this.oSach = oSach;
        this.tuSach = tuSach;
        this.moTa = moTa;
    }

    public ViTri(int maVT, String hangSach, String oSach, String tuSach, String moTa) {
        this.maVT = maVT;
        this.hangSach = hangSach;
        this.oSach = oSach;
        this.tuSach = tuSach;
        this.moTa = moTa;
    }

    public int getMaVT() {
        return maVT;
    }

    public void setMaVT(int maVT) {
        this.maVT = maVT;
    }

    public String getHangSach() {
        return hangSach;
    }

    public void setHangSach(String hangSach) {
        this.hangSach = hangSach;
    }

    public String getoSach() {
        return oSach;
    }

    public void setoSach(String oSach) {
        this.oSach = oSach;
    }

    public String getTuSach() {
        return tuSach;
    }

    public void setTuSach(String tuSach) {
        this.tuSach = tuSach;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return moTa + "";
    }
}
