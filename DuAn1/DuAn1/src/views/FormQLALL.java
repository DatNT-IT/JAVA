package views;

import Connects.Connect;
import Connects.LoaiSach.DataLoaiSach;
import Connects.LoaiSach.UpdateLS;
import Connects.NXB.DataNXB;
import Connects.NXB.UpdateNXB;
import Connects.ViTri.DataViTri;
import Connects.ViTri.UpdateViTri;
import DAO.ALLDAO;
import model.RendererHighlighted;
import Connects.tacGia.DataTacGia;
import Connects.tacGia.UpdateTacGia;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class FormQLALL extends JFrame {
    Connect con = new Connect();
    ALLDAO alldao = new ALLDAO();
    private JMenu mnuHeThong;
    private JMenuItem ImnuDangXuat;
    private JMenuItem ImnuDoiPass;
    private JMenuItem Imnudoimapin;
    private JMenuItem ImnuGIoiThieu;
    private JMenu MnuQuanLi;
    private JMenuItem ImnuQuanLiKho;
    private JMenuItem ImnuQuanLiDocGia;
    private JMenuItem ImnuQuanLiNhanVien;


    private JMenu MnuThongKe;
    private JMenuItem ImnuThongKeSach;
    private JMenuItem ImnuThongKeDocGia;
    private JMenuItem ImnuTongHop;
    private JMenu MnuTroGiup;
    private JMenuItem ImnuHuongDan;
    private JMenu MnuDongGop;
    private JMenuItem ImnuWeb;
    private JMenuItem ImnuBaoLoi;
    private JButton btnNhanVien;
    private JButton btnMauNen;
    private JButton btnDocGia;
    private JButton btnKhoSach;
    private JButton btnTrangChu;

    private JTabbedPane tabbedPane1;
    private JTextField txtMaTheLoai;
    private JTextField txtTenTheLoai;
    private JTable tblTheLoai;
    private JButton btnSuaLS;
    private JButton btnThemLS;
    private JButton btnXoaLS;
    private JButton btnNewLS;
    private JLabel lblTile;
    private JPanel JPL;
    private JTextField txtDiaChiTG;
    private JButton btnSuaTG;
    private JButton btnThemTG;
    private JButton btnXoaTG;
    private JButton btnNewTG;
    private JTextField txtDiaChiNXB;
    private JTextField txtTenNXB;
    private JTextField txtMaNXB;
    private JButton btnSuaNXB;
    private JButton btnThemNXB;
    private JButton btnXoaNXB;
    private JButton btnNewNXB;
    private JTable tblNXB;
    private JTextField txtTenTG;
    private JTextField txtMaTG;
    private JMenu MnuQuanLiThem;
    private JMenuItem ImnuQuanLiLoaiSach;
    private JMenuItem ImnuQuanLITacGia;
    private JMenuItem ImnuQuanLiNXB;
    private JLabel lblTile2;
    private JLabel lblTile1;
    private JTable tblTG;
    private JButton btntra;
    private JButton btnMuon;
    private JButton btndeletetl;
    private JTextField txtTimKiemLS;
    private JTextField txtTimKiemNXB;
    private JTextField txtTimKiemTG;
    private JButton btndeletetg;
    private JButton btndeletenxb;
    private JMenuItem ImnuQuanLiViTri;
    private JTextField txtosach;
    private JTextField txttusach;
    private JTextField txtmota;
    private JButton btnsuavitri;
    private JButton btnthemvitri;
    private JButton btnxoavitri;
    private JButton btnnewvitri;
    private JButton btndeletevitri;
    private JLabel lblTile3;
    private JTextField txtTimKiemViTri;
    private JTextField txtmavt;
    private JTextField txthangsach;
    private JTable tblvitri;
    String pass;
    int user, tab, vaitro;
    private boolean check = false;


    public FormQLALL() {

        add(JPL);
        setSize(1360, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        // this.setResizable(false);
        setVisible(true);
        txtMaTheLoai.setEnabled(false);
        txtMaNXB.setEnabled(false);
        txtMaTG.setEnabled(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                luuText();
            }
        });

        setTableModeNXB();
        setTableModeLS();
        setTableModeTG();
        setTableModeVT();
        //tìm kiếm sách
        RendererHighlighted renderer = new RendererHighlighted(txtTimKiemLS);
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(tblTheLoai.getModel());

        tblTheLoai.setRowSorter(rowSorter);
        tblTheLoai.setDefaultRenderer(Object.class, renderer);
        //tìm kiếm sách
        //tìm kiếm phieu mượn
        RendererHighlighted renderer1 = new RendererHighlighted(txtTimKiemTG);
        TableRowSorter<TableModel> rowSorter1
                = new TableRowSorter<>(tblTG.getModel());

        tblTG.setRowSorter(rowSorter1);
        tblTG.setDefaultRenderer(Object.class, renderer1);
        //tìm kiếm phiếu mượn
        //tìm kiếm chi tiết
        RendererHighlighted renderer2 = new RendererHighlighted(txtTimKiemNXB);
        TableRowSorter<TableModel> rowSorter2
                = new TableRowSorter<>(tblNXB.getModel());

        tblNXB.setRowSorter(rowSorter2);
        tblNXB.setDefaultRenderer(Object.class, renderer2);
        //tìm kiếm chi tiết
        //tìm kiếm chi tiết
        RendererHighlighted renderer3 = new RendererHighlighted(txtTimKiemViTri);
        TableRowSorter<TableModel> rowSorter3
                = new TableRowSorter<>(tblvitri.getModel());

        tblvitri.setRowSorter(rowSorter3);
        tblvitri.setDefaultRenderer(Object.class, renderer3);
        //tìm kiếm chi tiết
        btnMauNen.setMnemonic(KeyEvent.VK_B);//có thể nhấn alt + c thay vì click sẽ hiện bảng màu
        btnMauNen.setToolTipText(" alt + b");//hiện giới thiệu khi chuột để trc nút đó.
        //phím tắt ql vị trí
        ImnuQuanLiViTri.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));

        //phím tắt đổi mã pin
        Imnudoimapin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));

        //phím tắt đăng xuất
        ImnuDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));

        //phím tắt đổi pass
        ImnuDoiPass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        //phím tắt giới thiệu
        ImnuGIoiThieu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

        //phím tắt quản lí kho sách
        ImnuQuanLiKho.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK + InputEvent.SHIFT_MASK));


        //phím tắt độc giả
        ImnuQuanLiDocGia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.SHIFT_MASK));

        //phím tắt Hướng Dẫn
        ImnuHuongDan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));

        //phím tắt Thống kê sách
        ImnuThongKeSach.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));

        //phím tắt Thống kê độc giả
        ImnuThongKeDocGia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));

        //phím tắt Thống kê tổng hợp
        ImnuTongHop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));
        //phím tắt quản lí nhan viên
        ImnuQuanLiNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));
        //phím tắt ql loại sách
        ImnuQuanLiLoaiSach.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));
        //phím tắt ql tác giả
        ImnuQuanLITacGia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));
        //phím tắt nxb
        ImnuQuanLiNXB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK));
        //phím tắt trang web
        ImnuWeb.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK + InputEvent.SHIFT_MASK));
        //phím tắt báo lỗi
        ImnuBaoLoi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK + InputEvent.ALT_MASK + InputEvent.SHIFT_MASK));
        // mở chương trình và lưu giá trị

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FromChinh fromChinh = new FromChinh();
                fromChinh.setUser(user);
                fromChinh.setPass(pass);
                fromChinh.setVaitro(vaitro);
                dispose();
            }
        });

        ImnuBaoLoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "mọi yêu cầu đóng góp vui lòng phản hồi qua :\n" +
                        "Email:Datntph15646@fpt.edu.vn\n" +
                        "hotline: 08658432580");
            }
        });
        ImnuWeb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI("https://www.sachhayonline.com/"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }

            }
        });
        ImnuHuongDan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HTML();

            }
        });
        btnMuon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhieuMuonChiTiet muonChiTiet = new PhieuMuonChiTiet();
                muonChiTiet.setUser(user);
                muonChiTiet.setPass(pass);
                muonChiTiet.setVaitro(vaitro);
                dispose();
            }
        });

        Imnudoimapin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormDoiMaPin pin = new FormDoiMaPin();
                pin.setUser(user);
                pin.setPass(pass);
                pin.setVaitro(vaitro);

            }
        });

        ImnuDoiPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formDoiPass doiPass = new formDoiPass();
                doiPass.setUser(user);
                doiPass.setPass(pass);
                doiPass.setVaitro(vaitro);

            }
        });
        ImnuDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new FromDangNhap();
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImnuQuanLiDocGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLDocGia docGia = new FormQLDocGia();
                docGia.setUser(user);
                docGia.setPass(pass);
                docGia.setVaitro(vaitro);
                dispose();
            }
        });
        ImnuQuanLiKho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLSach sach = new FormQLSach();
                sach.setUser(user);
                sach.setPass(pass);
                sach.setVaitro(vaitro);
                dispose();
            }
        });

        ImnuThongKeSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormThongKe muonTra = new FormThongKe();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                muonTra.settab(0);
                dispose();
            }
        });
        ImnuThongKeDocGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormThongKe muonTra = new FormThongKe();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                muonTra.settab(1);
                dispose();
            }
        });
        ImnuTongHop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormThongKe muonTra = new FormThongKe();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                muonTra.settab(2);
                dispose();
            }
        });
        ImnuQuanLiLoaiSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLALL muonTra = new FormQLALL();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                muonTra.settab(0);
                dispose();
            }
        });
        ImnuQuanLITacGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLALL muonTra = new FormQLALL();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                muonTra.settab(1);
                dispose();
            }
        });
        ImnuQuanLiNXB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLALL muonTra = new FormQLALL();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                muonTra.settab(2);
                dispose();
            }
        });
        ImnuQuanLiViTri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLALL muonTra = new FormQLALL();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                muonTra.settab(3);
                dispose();
            }
        });
        ImnuQuanLiNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLNhanVien muonTra = new FormQLNhanVien();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                dispose();
            }
        });

        btnNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLNhanVien muonTra = new FormQLNhanVien();
                muonTra.setUser(user);
                muonTra.setPass(pass);
                muonTra.setVaitro(vaitro);
                dispose();
            }
        });
        btnDocGia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLDocGia docGia = new FormQLDocGia();
                docGia.setUser(user);
                docGia.setPass(pass);
                docGia.setVaitro(vaitro);
                dispose();
            }
        });
        btnKhoSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormQLSach docGia = new FormQLSach();
                docGia.setUser(user);
                docGia.setPass(pass);
                docGia.setVaitro(vaitro);
                dispose();
            }
        });


        btnMauNen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "chọn 1 màu viền theo ý thích", null);
                if (color != null) {
                    //  btnok.setBackground(color);
                    JPL.setBackground(color);
                    System.out.println("màu viền bạn chọn là màu : " + color);

                }
            }
        });
        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FromChinh();
                dispose();
            }
        });
        btnThemLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    if (txtMaTheLoai.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn khôi phục thông tin về loại sách này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.updateDaXoals(txtMaTheLoai.getText());
                        setTableModeLSXoa();
                        resecTL();
                    }
                    return;
                }


                try {
                    if (txtTenTheLoai.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống tên thể loại");
                        return;
                    }
                    // SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy , EEE");
                    alldao.addls(txtTenTheLoai.getText());
                    setTableModeLS();
                    resecTL();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh


            }
        });
        btnXoaLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaTheLoai.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin về loại sách này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.deletels(txtMaTheLoai.getText());
                        setTableModeLSXoa();
                        resecTL();
                    }
                    return;
                }
                int chon = JOptionPane.showConfirmDialog(null, "bạn có  muốn xoá  thông tin về LS này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    alldao.deleteTamls(txtMaTheLoai.getText());
                    setTableModeLS();
                    resecTL();
                }
            }

        });
        btnXoaTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaTG.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin về tác giả này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.deletetg(txtMaTG.getText());
                        setTableModeTGXoa();
                        resectg();
                    }
                    return;
                }
                int chon = JOptionPane.showConfirmDialog(null, "bạn có  muốn xoá  thông tin về tác giả này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    alldao.deleteTamtg(txtMaTG.getText());
                    setTableModeTG();
                    resectg();
                }
            }

        });
        btnXoaNXB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaNXB.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin về NXB này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.deletenxb(txtMaNXB.getText());
                        setTableModeNXBXoa();
                        resecnxb();
                    }
                    return;
                }
                int chon = JOptionPane.showConfirmDialog(null, "bạn có  muốn xoá  thông tin về nxb này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    alldao.deleteTamnxb(txtMaNXB.getText());
                    setTableModeNXB();
                    resecnxb();
                }
            }

        });
        btnxoavitri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtmavt.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã vị trí");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin về vị trí này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.deletevt(txtmavt.getText());
                        setTableModeVTXoa();
                        resecvt();
                    }
                    return;
                }
                int chon = JOptionPane.showConfirmDialog(null, "bạn có  muốn xoá  thông tin về vị trí này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    alldao.deleteTamvt(txtmavt.getText());
                    setTableModeVT();
                    resecvt();
                }
            }

        });
        btnSuaLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenTheLoai.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống tên");
                    return;
                }
                alldao.updatels(txtTenTheLoai.getText(), txtMaTheLoai.getText());
                setTableModeLS();
                resecTL();
            }
        });
        btnNewLS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resecTL();
            }
        });
        txtTimKiemLS.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemLS.getText();
                //trim xoá khoảng trắng(dấu cách) đầu và cuối chuỗi
                //text.trim.length trong text có bao nhieu kí tự trước và sau text nêu bằng 0 kí tự sẽ null
                //(?i) là để nó tìm kiếm cái text theo kiểu case insensitive không phân biệt hoa thường
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemLS.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    //RowFilter để lọc kết quả tìm kiếm.
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        txtTimKiemTG.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemTG.getText();
                //trim xoá khoảng trắng(dấu cách) đầu và cuối chuỗi
                //text.trim.length trong text có bao nhieu kí tự trước và sau text nêu bằng 0 kí tự sẽ null
                //(?i) là để nó tìm kiếm cái text theo kiểu case insensitive không phân biệt hoa thường
                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemTG.getText();
                if (text.trim().length() == 0) {
                    rowSorter1.setRowFilter(null);
                } else {
                    //RowFilter để lọc kết quả tìm kiếm.
                    rowSorter1.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        txtTimKiemNXB.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemNXB.getText();
                //trim xoá khoảng trắng(dấu cách) đầu và cuối chuỗi
                //text.trim.length trong text có bao nhieu kí tự trước và sau text nêu bằng 0 kí tự sẽ null
                //(?i) là để nó tìm kiếm cái text theo kiểu case insensitive không phân biệt hoa thường
                if (text.trim().length() == 0) {
                    rowSorter2.setRowFilter(null);
                } else {
                    rowSorter2.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemNXB.getText();
                if (text.trim().length() == 0) {
                    rowSorter2.setRowFilter(null);
                } else {
                    //RowFilter để lọc kết quả tìm kiếm.
                    rowSorter2.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        txtTimKiemViTri.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiemViTri.getText();
                //trim xoá khoảng trắng(dấu cách) đầu và cuối chuỗi
                //text.trim.length trong text có bao nhieu kí tự trước và sau text nêu bằng 0 kí tự sẽ null
                //(?i) là để nó tìm kiếm cái text theo kiểu case insensitive không phân biệt hoa thường
                if (text.trim().length() == 0) {
                    rowSorter3.setRowFilter(null);
                } else {
                    rowSorter3.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtTimKiemViTri.getText();
                if (text.trim().length() == 0) {
                    rowSorter3.setRowFilter(null);
                } else {
                    //RowFilter để lọc kết quả tìm kiếm.
                    rowSorter3.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        btndeletetl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {
                    setTableModeLSXoa();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những loại sách bị xoá");
                    btndeletetl.setText("hiện những loại sach đang còn ");
                    btnThemLS.setText("khôi phục");
                    btnSuaLS.setEnabled(false);
                    txtTenTheLoai.setEnabled(false);

                    txtMaTheLoai.setEnabled(true);
                    return;
                }
                if (check) {
                    setTableModeLS();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những người còn là độc giả");
                    btndeletetl.setText("hiện những loại sách đã bị xoá");
                    btnThemLS.setText("thêm");
                    btnSuaLS.setEnabled(true);
                    txtTenTheLoai.setEnabled(true);


                    txtMaTheLoai.setEnabled(false);
                    return;
                }
            }
        });
        btndeletetg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {
                    setTableModeTGXoa();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những tác giả bị xoá");
                    btndeletetg.setText("hiện những tác giả đang còn ");
                    btnThemTG.setText("khôi phục");
                    btnSuaTG.setEnabled(false);
                    txtTenTG.setEnabled(false);
                    txtDiaChiTG.setEnabled(false);
                    txtMaTG.setEnabled(true);
                    return;
                }
                if (check) {
                    setTableModeTG();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những tác giả còn");
                    btndeletetg.setText("hiện những tác giả đã bị xoá");
                    btnThemTG.setText("thêm");
                    btnSuaTG.setEnabled(true);
                    txtTenTG.setEnabled(true);
                    txtDiaChiTG.setEnabled(true);


                    txtMaTG.setEnabled(false);
                    return;
                }
            }
        });
        btndeletevitri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {
                    setTableModeVTXoa();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những vị trí đã bị xoá");
                    btndeletetg.setText("hiện những vị trí còn ");
                    btnthemvitri.setText("khôi phục");
                    btnsuavitri.setEnabled(false);
                    txthangsach.setEnabled(false);
                    txtosach.setEnabled(false);
                    txttusach.setEnabled(false);
                    txtmavt.setEnabled(true);
                    return;
                }
                if (check) {
                    setTableModeVT();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những vị trí còn");
                    btndeletetg.setText("hiện những vị trí đã bị xoá\"");
                    btnThemTG.setText("thêm");
                    btnsuavitri.setEnabled(true);
                    txthangsach.setEnabled(true);
                    txtosach.setEnabled(true);
                    txttusach.setEnabled(true);
                    txtmavt.setEnabled(false);
                    return;
                }
            }
        });
        btndeletenxb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {
                    // BangTLXoa();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những Connects.NXB bị xoá");
                    btndeletenxb.setText("hiện những Connects.NXB đang còn ");
                    btnThemNXB.setText("khôi phục");
                    btnSuaNXB.setEnabled(false);
                    txtTenNXB.setEnabled(false);
                    txtDiaChiNXB.setEnabled(false);
                    txtMaNXB.setEnabled(true);
                    return;
                }
                if (check) {
                    //  BangTL();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những Connects.NXB còn");
                    btndeletenxb.setText("hiện những Connects.NXB đã bị xoá");
                    btnThemNXB.setText("thêm");
                    btnSuaNXB.setEnabled(true);
                    txtTenNXB.setEnabled(true);
                    txtDiaChiNXB.setEnabled(true);


                    txtMaNXB.setEnabled(false);
                    return;
                }
            }
        });
        btnSuaNXB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenNXB.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống tên");
                    return;
                }
                if (txtDiaChiNXB.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống địa chỉ");
                    return;
                }
                alldao.updatenxb(txtTenNXB.getText(), txtDiaChiNXB.getText(), txtMaNXB.getText());
                setTableModeNXB();
                resecnxb();
            }
        });
        btnsuavitri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtosach.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống ô sách");
                    return;
                }
                if (txthangsach.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống hàng sách");
                    return;
                }
                if (txttusach.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống tủ sách");
                    return;
                }
                alldao.updatevt(txthangsach.getText(), txtosach.getText(), txttusach.getText());
                setTableModeVT();
                resecvt();
            }
        });
        btnSuaTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtTenTG.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống tên");
                    return;
                }
                if (txtDiaChiTG.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được bỏ trống địa chỉ");
                    return;
                }
                alldao.updatetg(txtTenTG.getText(), txtDiaChiTG.getText(), txtMaTG.getText());
                setTableModeTG();
                resectg();
            }
        });
        btnNewNXB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resecnxb();
            }
        });
        btnNewTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resectg();
            }
        });

        btnThemNXB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    if (txtMaNXB.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn khôi phục thông tin về tác giả này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.updateDaXoanxb(txtMaNXB.getText());
                        setTableModeNXBXoa();
                        resecnxb();
                    }
                    return;
                }


                try {
                    if (txtTenNXB.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống tên NXB");
                        return;
                    }
                    if (txtDiaChiNXB.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống địa chỉ NXB");
                        return;
                    }
                    // SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy , EEE");
                    alldao.addnxb(txtTenNXB.getText(), txtDiaChiNXB.getText());
                    setTableModeNXB();
                    resecnxb();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh


            }
        });
        btnthemvitri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    if (txtmavt.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn khôi phục thông tin về vị trí này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.updateDaXoavt(txtmavt.getText());
                        setTableModeVTXoa();
                        resecnxb();
                    }
                    return;
                }


                try {
                    if (txthangsach.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống hàng sách");
                        return;
                    }
                    if (txtosach.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống ô sách");
                        return;
                    }
                    if (txttusach.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống tủ sách");
                        return;
                    }
                    // SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy , EEE");
                    alldao.addvt(txthangsach.getText(), txtosach.getText(), txttusach.getText());
                    setTableModeVT();
                    resecvt();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh


            }
        });
        btnThemTG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    if (txtMaTG.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn khôi phục thông tin về tác giả này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        alldao.updateDaXoatg(txtMaTG.getText());
                        setTableModeTGXoa();
                        resectg();
                    }
                    return;
                }


                try {
                    if (txtTenTG.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống tên tg");
                        return;
                    }
                    if (txtDiaChiTG.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống địa chỉ tg");
                        return;
                    }
                    // SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy , EEE");
                    alldao.addtg(txtTenTG.getText(), txtDiaChiTG.getText());
                    setTableModeTG();
                    resectg();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh


            }
        });
        tblNXB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblNXB.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtMaNXB.setText(String.valueOf(tblNXB.getValueAt(index, 0)));
                txtTenNXB.setText(String.valueOf(tblNXB.getValueAt(index, 1)));
                txtDiaChiNXB.setText(String.valueOf(tblNXB.getValueAt(index, 2)));
            }
        });

        tblTG.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblTG.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtMaTG.setText(String.valueOf(tblTG.getValueAt(index, 0)));
                txtTenTG.setText(String.valueOf(tblTG.getValueAt(index, 1)));
                txtDiaChiTG.setText(String.valueOf(tblTG.getValueAt(index, 2)));
            }
        });

        tblTheLoai.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblTheLoai.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtMaTheLoai.setText(String.valueOf(tblTheLoai.getValueAt(index, 0)));
                txtTenTheLoai.setText(String.valueOf(tblTheLoai.getValueAt(index, 1)));

            }
        });

        btntra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhieuTraCT phieuTraCT = new PhieuTraCT();
                phieuTraCT.setUser(user);
                phieuTraCT.setPass(pass);
                phieuTraCT.setVaitro(vaitro);
                dispose();
            }
        });
        btnnewvitri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resecvt();
            }
        });
        tblvitri.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblvitri.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtmavt.setText(String.valueOf(tblvitri.getValueAt(index, 0)));
                txthangsach.setText(String.valueOf(tblvitri.getValueAt(index, 1)));
                txtosach.setText(String.valueOf(tblvitri.getValueAt(index, 2)));
                txttusach.setText(String.valueOf(tblvitri.getValueAt(index, 3)));
                txtmota.setText(String.valueOf(tblvitri.getValueAt(index, 4)));
            }
        });
    }


    public void setUser(int user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setVaitro(int vaitro) {
        this.vaitro = vaitro;
    }

    public void settab(int tab) {
        this.tab = tab;
    }


    public void resecTL() {
        txtTenTheLoai.setText("");
        txtMaTheLoai.setText("");

    }

    public void resecnxb() {
        txtTenNXB.setText("");
        txtDiaChiNXB.setText("");
        txtMaNXB.setText("");
    }

    public void resectg() {
        txtTenTG.setText("");
        txtDiaChiTG.setText("");
        txtMaTG.setText("");

    }

    public void resecvt() {
        txtmavt.setText("");
        txthangsach.setText("");
        txttusach.setText("");
        txtosach.setText("");
        txtmota.setText("");

    }

    private void luuText() {
        System.out.println(vaitro);
        if (vaitro == 1) {
            btnthemvitri.setEnabled(false);
            btnsuavitri.setEnabled(false);
            btnxoavitri.setEnabled(false);
            btnThemTG.setEnabled(false);
            btnSuaTG.setEnabled(false);
            btnXoaTG.setEnabled(false);
            btnThemLS.setEnabled(false);
            btnSuaLS.setEnabled(false);
            btnXoaLS.setEnabled(false);
            btnThemNXB.setEnabled(false);
            btnSuaNXB.setEnabled(false);
            btnXoaNXB.setEnabled(false);
            btndeletetl.setEnabled(false);
            btndeletetg.setEnabled(false);
            btndeletevitri.setEnabled(false);
            btndeletenxb.setEnabled(false);
        }
        if (tab == 0) {
            tabbedPane1.remove(3);
            tabbedPane1.remove(2);//đóng quản lí độc giả chỉ hiện ql thẻ
            tabbedPane1.remove(1);
            chuChay();
            new TieuDeNhay();
        }
        if (tab == 1) {
            tabbedPane1.remove(3);
            tabbedPane1.remove(2);
            tabbedPane1.remove(0);
            chuChay1();
            new TieuDeNhay1();
        }
        if (tab == 2) {
            tabbedPane1.remove(3);
            tabbedPane1.remove(1);
            tabbedPane1.remove(0);
            chuChay2();
            new TieuDeNhay2();
        }
        if (tab == 3) {
            tabbedPane1.remove(2);
            tabbedPane1.remove(1);
            tabbedPane1.remove(0);
            chuChay3();
            new TieuDeNhay3();
        }


    }

    public void setTableModeTG() {
        UpdateTacGia model = new UpdateTacGia(DataTacGia.getListTG());
        tblTG.setModel(model);

    }

    public void setTableModeTGXoa() {
        UpdateTacGia model = new UpdateTacGia(DataTacGia.getListTGXoa());
        tblTG.setModel(model);

    }

    public void setTableModeLS() {
        UpdateLS model = new UpdateLS(DataLoaiSach.getListLS());
        tblTheLoai.setModel(model);

    }

    public void setTableModeLSXoa() {
        UpdateLS model = new UpdateLS(DataLoaiSach.getListLSXoa());
        tblTheLoai.setModel(model);

    }

    public void setTableModeNXB() {
        UpdateNXB model = new UpdateNXB(DataNXB.getListNXB());
        tblNXB.setModel(model);

    }

    public void setTableModeNXBXoa() {
        UpdateNXB model = new UpdateNXB(DataNXB.getListNXBXoa());
        tblNXB.setModel(model);

    }

    public void setTableModeVT() {
        UpdateViTri model = new UpdateViTri(DataViTri.getListVT());
        tblvitri.setModel(model);

    }

    public void setTableModeVTXoa() {
        UpdateViTri model = new UpdateViTri(DataViTri.getListVTXoa());
        tblvitri.setModel(model);

    }

    public void chuChay() {
        Thread thread = new Thread() {


            public void run() {
                String txt = lblTile.getText();

                while (true) {

                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lblTile.setText(txt);
                }
            }

        };
        thread.start();
    }

    public void chuChay1() {
        Thread thread = new Thread() {


            public void run() {
                String txt = lblTile1.getText();

                while (true) {

                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lblTile1.setText(txt);
                }
            }

        };
        thread.start();
    }

    public void chuChay2() {
        Thread thread = new Thread() {


            public void run() {
                String txt = lblTile2.getText();

                while (true) {

                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lblTile2.setText(txt);
                }
            }

        };
        thread.start();
    }

    public void chuChay3() {
        Thread thread = new Thread() {


            public void run() {
                String txt = lblTile3.getText();

                while (true) {

                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lblTile3.setText(txt);
                }
            }

        };
        thread.start();
    }

    public class TieuDeNhay extends Thread {
        int icout = -1;
        String arrtext[] = {"Quản Lí Loại Sách", "Quản", "Lí", "Loại", "Sách"};

        public TieuDeNhay() {
            start();
        }

        public void run() {
            while (true) {
                try {
                    icout++;
                    if (icout == arrtext.length) {
                        icout = 0;
                    }
                    setTitle(arrtext[icout]);


                    sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class TieuDeNhay1 extends Thread {
        int icout = -1;
        String arrtext[] = {"Quản Lí Tác Giả", "Quản", "Lí", "Tác", "Giả"};

        public TieuDeNhay1() {
            start();
        }

        public void run() {
            while (true) {
                try {
                    icout++;
                    if (icout == arrtext.length) {
                        icout = 0;
                    }
                    setTitle(arrtext[icout]);


                    sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class TieuDeNhay2 extends Thread {
        int icout = -1;
        String arrtext[] = {"Quản Lí Nhà Xuất Bản", "Quản", "Lí", "NXB"};

        public TieuDeNhay2() {
            start();
        }

        public void run() {
            while (true) {
                try {
                    icout++;
                    if (icout == arrtext.length) {
                        icout = 0;
                    }
                    setTitle(arrtext[icout]);


                    sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class TieuDeNhay3 extends Thread {
        int icout = -1;
        String arrtext[] = {"Quản Lí Vị Trí", "Quản", "Lí", "Vị", "Trí"};

        public TieuDeNhay3() {
            start();
        }

        public void run() {
            while (true) {
                try {
                    icout++;
                    if (icout == arrtext.length) {
                        icout = 0;
                    }
                    setTitle(arrtext[icout]);


                    sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void HTML() {

        JFrame frame = new JFrame("Hướng Dẫn");
        frame.setLayout(new FlowLayout());
        JEditorPane editorPane = new JEditorPane();
        editorPane.setSize(500, 300);
        editorPane.setContentType("text/html");
        editorPane.setText("<html>\n" +
                "  <head>\n" +
                "    \n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"vi-tinsub-noi-dung\">\n" +
                "      <p align=\"center\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>QUY \n" +
                "        TR&#204;NH M&#431;&#7906;N &#8211; TR&#7842; T&#192;I LI&#7878;U</strong></font></span>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B1</strong>: \n" +
                "        Tr&#236;nh th&#7867; th&#432; vi&#7879;n cho th&#7911; th&#432;.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B2</strong>: \n" +
                "        Tra m&#227; t&#224;i li&#7879;u .</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 36.0pt; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">+ \n" +
                "        Tra b&#7857;ng m&#225;y t&#237;nh.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 36.0pt; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">+ \n" +
                "        Tra b&#7857;ng t&#7911; m&#7909;c l&#7909;c.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B3:</strong> \n" +
                "        L&#7845;y phi&#7871;u y&#234;u c&#7847;u m&#432;&#7907;n t&#224;i li&#7879;u n&#7871;u c&#243; nhu c&#7847;u m&#432;&#7907;n. </font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B4:</strong> \n" +
                "        Ghi &#273;&#7847;y &#273;&#7911; c&#225;c th&#244;ng tin tr&#234;n Phi&#7871;u y&#234;u c&#7847;u:</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 36.0pt; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">+ \n" +
                "        H&#7885; t&#234;n, th&#244;ng tin &#273;&#7883;a ch&#7881; c&#7911;a ng&#432;&#7901;i m&#432;&#7907;n.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 36.0pt; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">+ \n" +
                "        T&#234;n t&#224;i li&#7879;u, t&#234;n t&#225;c gi&#7843;, s&#7889; &#273;&#259;ng k&#253; c&#225; bi&#7879;t, m&#227; kho c&#7911;a t&#224;i li&#7879;u c&#7847;n \n" +
                "        m&#432;&#7907;n.<br><strong><em>L&#432;u &#253;:</em></strong></font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong><em>a, \n" +
                "        &#272;&#7885;c t&#7841;i ch&#7895;:</em></strong></font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">- \n" +
                "        M&#7895;i l&#7847;n ch&#7881; &#273;&#432;&#7907;c m&#432;&#7907;n t&#7889;i &#273;a 3 t&#224;i li&#7879;u; &#273;&#7889;i v&#7899;i ph&#242;ng &#273;&#7885;c t&#7921; ch&#7885;n, \n" +
                "        b&#7841;n &#273;&#7885;c &#273;&#432;&#7907;c ph&#233;p tr&#7921;c ti&#7871;p l&#7921;a ch&#7885;n t&#224;i li&#7879;u tr&#234;n gi&#225; s&#225;ch.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">- \n" +
                "        Kh&#244;ng &#273;&#432;&#7907;c ph&#233;p t&#7921; &#253; mang t&#224;i li&#7879;u ra kh&#7887;i ph&#242;ng &#273;&#7885;c.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">- \n" +
                "        Kh&#244;ng s&#7917; d&#7909;ng c&#225;c thi&#7871;t b&#7883; m&#225;y m&#243;c d&#224;nh ri&#234;ng cho b&#7841;n &#273;&#7885;c qu&#225; th&#7901;i \n" +
                "        gian quy &#273;&#7883;nh c&#7911;a Th&#432; vi&#7879;n.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong><em>b, \n" +
                "        M&#432;&#7907;n v&#7873; nh&#224;:</em></strong></font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">&#160; \n" +
                "        &#160; &#160;M&#7895;i l&#7847;n &#273;&#432;&#7907;c m&#432;&#7907;n t&#7889;i &#273;a 03 b&#7843;n s&#225;ch <em>(trong \n" +
                "        &#273;&#243; s&#225;ch v&#259;n h&#7885;c kh&#244;ng qu&#225; 02 b&#7843;n)</em> \n" +
                "        v&#7899;i th&#7901;i h&#7841;n 07 ng&#224;y.</font></span>\n" +

                "      </p>\n" +
                "<p align=\\\"justify\\\" style=\\\"margin-left: 0cm\\\">\\n" +
                " <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\">" +
                " Qu&#225; th&#7901;i h&#7841;n &#273;&#243;, n&#7871;u c&#243; nhu c&#7847;u s&#7917; d&#7909;ng ti&#7871;p \n" +
                "                          t&#7909;c, b&#7841;n &#273;&#7885;c ph&#7843;i &#273;&#7871;n gia h&#7841;n; m&#7895;i l&#7847;n gia h&#7841;n &#273;&#432;&#7907;c th&#234;m 03 ng&#224;y v&#224; \n" +
                "                        kh&#244;ng qu&#225; 02 l&#7847;n." +
                "</font></span>" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B5: \n" +
                "        </strong>&#272;&#432;a Phi&#7871;u y&#234;u c&#7847;u &#273;&#7875; th&#7911; th&#432; &#273;i l&#7845;y t&#224;i li&#7879;u.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B6: \n" +
                "        </strong>Nh&#7853;n t&#224;i li&#7879;u v&#224; ki&#7875;m tra t&#236;nh tr&#7841;ng t&#224;i li&#7879;u, n&#7871;u c&#243; g&#236; b&#7845;t \n" +
                "        th&#432;&#7901;ng c&#7847;n b&#225;o ngay cho th&#7911; th&#432;.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B7:</strong> \n" +
                "        Mang t&#224;i li&#7879;u v&#7873; v&#7883; tr&#237; ng&#7891;i &#273;&#7885;c.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B8:</strong> \n" +
                "        N&#7871;u mu&#7889;n m&#432;&#7907;n v&#7873; nh&#224; c&#7847;n &#273;&#259;ng k&#253; v&#224; l&#224;m theo h&#432;&#7899;ng d&#7851;n c&#7911;a th&#7911; th&#432;.</font></span>\n" +
                "      </p>\n" +
                "      <p align=\"justify\" style=\"margin-left: 0cm; margin-right: 0cm\">\n" +
                "        <span><font size=\"16px\" color=\"#000000\" face=\"Times New Roman,Times,serif\"><strong>B9:</strong> \n" +
                "        Khi &#273;&#7885;c xong t&#224;i li&#7879;u, b&#7841;n &#273;&#7885;c mang t&#224;i li&#7879;u tr&#7903; l&#7841;i v&#224; b&#224;n giao cho \n" +
                "        th&#7911; th&#432;.<br><strong>B10:</strong> Nh&#7853;n l&#7841;i th&#7867; th&#432; vi&#7879;n.</font></span>\n" +
                "      </p>\n" +


                "  </body>\n" +
                "</html>"
        );
        JScrollPane scrollPane = new JScrollPane(editorPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
}
