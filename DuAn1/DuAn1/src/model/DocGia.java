package model;

public class DocGia {
    private String tenDG, diaChi, ngaySinh, soCCCD, SDT;
    private int maDG, gioiTinh;

    public DocGia() {
    }

    public DocGia(String tenDG, String diaChi, String ngaySinh, String soCCCD, String SDT, int maDG, int gioiTinh) {
        this.tenDG = tenDG;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.soCCCD = soCCCD;
        this.SDT = SDT;
        this.maDG = maDG;
        this.gioiTinh = gioiTinh;
    }

    public DocGia(String tenDG, String diaChi, String ngaySinh, String soCCCD, String SDT, int gioiTinh) {
        this.tenDG = tenDG;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.soCCCD = soCCCD;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getMaDG() {
        return maDG;
    }

    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return maDG +
                "";
    }
}
