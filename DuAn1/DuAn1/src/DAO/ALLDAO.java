package DAO;

import Connects.Connect;
import model.DocGia;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ALLDAO {
    Connect con = new Connect();

    //thêm loại ls
    public void addls(String ten) {

        String sql = " insert into loaisach (tenls, xoa)  values (?,?)";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setInt(2, 1);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "thêm thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
        }
    }

    //xoá vĩnh viễn ls
    public void deletels(String id) {
        String sqlsach = "delete  CHITIETSACH where MAls=?";
        String sql = "delete loaisach where MAls = ?";
        try {
            PreparedStatement ps1 = con.con().prepareStatement(sqlsach);
            ps1.setString(1, id);

            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //xoá tạm ls
    public void deleteTamls(String id) {

        String sql = "update loaisach set xoa=0 where MAls=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //cập nhật ls
    public void updatels(String ten, String ma) {

        String sql = "update loaisach set TENls=?where MAls=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, ma);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }
    }

    //thêm lại đã xoá ls
    public void updateDaXoals(String id) {

        String sql = "update loaisach set xoa=1 where MAls=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật lại  thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật lại thất bại");
        }
    }

    //thêm nxb
    public void addnxb(String ten, String diachi) {

        String sql = " insert into nxb (tennxb,diachinxb, xoa)  values (?,?,?)";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, diachi);
            ps.setInt(3, 1);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "thêm thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
        }
    }

    //xoá vĩnh viễn nxb
    public void deletenxb(String id) {
        String sqlsach = "delete  CHITIETSACH where MAnxb=?";
        String sql = "delete nxb where MAxnb = ?";
        try {
            PreparedStatement ps1 = con.con().prepareStatement(sqlsach);
            ps1.setString(1, id);
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //xoá tạm xnb
    public void deleteTamnxb(String id) {

        String sql = "update xnb set xoa=0 where MAxnb=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //cập nhật xnb
    public void updatenxb(String ten, String diachi, String ma) {

        String sql = "update nxb set TENnxb=? ,diachinxb=? where MAnxb=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, diachi);
            ps.setString(3, ma);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }
    }

    //thêm lại đã xoá xnb
    public void updateDaXoanxb(String id) {

        String sql = "update nxb set xoa=1 where MAxnb=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật lại  thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật lại thất bại");
        }
    }

    //thêm  tg
    public void addtg(String ten, String diachi) {

        String sql = " insert into tacgia (tentg,diachitg, xoa)  values (?,?,?)";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, diachi);
            ps.setInt(3, 1);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "thêm thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
        }
    }

    //xoá vĩnh viễn tg
    public void deletetg(String id) {
        String sqlsach = "delete  CHITIETSACH where MAtg=?";
        String sql = "delete tacgia where MAtg = ?";
        try {
            PreparedStatement ps1 = con.con().prepareStatement(sqlsach);
            ps1.setString(1, id);
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //xoá tạm tg
    public void deleteTamtg(String id) {

        String sql = "update tacgia set xoa=0 where MAtg=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //cập nhật tg
    public void updatetg(String ten, String diachi, String ma) {

        String sql = "update tacgia set TENtg=? ,diachitg=? where MAtg=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, ten);
            ps.setString(2, diachi);
            ps.setString(3, ma);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }
    }

    //thêm lại đã xoá tg
    public void updateDaXoatg(String id) {

        String sql = "update tacgia set xoa=1 where MAtg=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật lại  thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật lại thất bại");
        }
    }

    //thêm  vt
    public void addvt(String hang, String o, String tu) {

        String sql = " insert into VITRI(hangsach, osach, xoa, mota, tusach) values (?,?,?,?,?)";
        String mota = "tủ" + tu + ",hàng" + hang + ",ô" + o;
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, hang);
            ps.setString(2, o);
            ps.setInt(3, 1);
            ps.setString(4, mota);
            ps.setString(5, tu);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "thêm thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "thêm thất bại");
        }
    }

    //xoá vĩnh viễn vt
    public void deletevt(String id) {

        String sqlsach = "delete  CHITIETSACH where MAVT=?";
        String sql = "delete vitri where MAvt = ?";
        try {
            PreparedStatement ps1 = con.con().prepareStatement(sqlsach);
            ps1.setString(1, id);
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //xoá tạm vt
    public void deleteTamvt(String id) {

        String sql = "update vitri set xoa=0 where MAvt=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "xoá thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "xoá thất bại");
        }
    }

    //cập nhật vt
    public void updatevt(String hang, String o, String tu) {
        String mota = "tủ" + tu + ",hàng" + hang + ",ô" + o;
        String sql = "update vitri set hangsach=? ,osach=?,tusach=?,mota=? where MAvt=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, hang);
            ps.setString(2, o);
            ps.setString(3, tu);
            ps.setString(4, mota);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật thất bại");
        }
    }

    //thêm lại đã xoá vt
    public void updateDaXoavt(String id) {

        String sql = "update vitri set xoa=1 where MAvt=?";
        try {
            PreparedStatement ps = con.con().prepareStatement(sql);
            ps.setString(1, id);


            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "cập nhật lại  thành công");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "cập nhật lại thất bại");
        }
    }

}
