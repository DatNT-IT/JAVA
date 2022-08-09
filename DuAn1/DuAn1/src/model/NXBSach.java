package model;

public class NXBSach {
    private int maNXB;
    private String tenNXB, diaChiNXB;

    public NXBSach() {
    }

    public NXBSach(int maNXB, String tenNXB, String diaChiNXB) {
        this.maNXB = maNXB;
        this.tenNXB = tenNXB;
        this.diaChiNXB = diaChiNXB;
    }

    public int getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(int maNXB) {
        this.maNXB = maNXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public String getDiaChiNXB() {
        return diaChiNXB;
    }

    public void setDiaChiNXB(String diaChiNXB) {
        this.diaChiNXB = diaChiNXB;
    }

    @Override
    public String toString() {
        return
                tenNXB + ""
                ;
    }
}
