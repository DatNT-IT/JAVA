package model;

public class ChiTietTacGia {
    private int maTG;
    private String tenTG, diaChi;

    public ChiTietTacGia() {
    }

    public ChiTietTacGia(int maTG, String tenTG, String diaChi) {
        this.maTG = maTG;

        this.tenTG = tenTG;
        this.diaChi = diaChi;
    }

    public int getMaTG() {
        return maTG;
    }

    public void setMaTG(int maTG) {
        this.maTG = maTG;
    }


    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return
                tenTG + ""
                ;
    }
}
