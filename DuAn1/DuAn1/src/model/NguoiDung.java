package model;

public class NguoiDung {
    private String tenNguoiDung, matKhau, email, mapin;
    private int maND, gioiTinh, vaiTro;

    public NguoiDung() {
    }

    public NguoiDung(String tenNguoiDung, String matKhau, String email, int maND, int gioiTinh, int vaiTro, String mapin) {
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.email = email;
        this.maND = maND;
        this.gioiTinh = gioiTinh;
        this.vaiTro = vaiTro;
        this.mapin = mapin;
    }


    public NguoiDung(String tenNguoiDung, String matKhau, String email, int gioiTinh, int vaiTro, String mapin) {
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.vaiTro = vaiTro;
        this.mapin = mapin;
    }


    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMapin() {
        return mapin;
    }

    public void setMapin(String mapin) {
        this.mapin = mapin;
    }

    @Override
    public String toString() {
        return
                maND + "";
    }
}
