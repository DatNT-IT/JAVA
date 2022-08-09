package views;

import Connects.Connect;
import DAO.PhieuMuonDAO;
import DAO.PhieuTraDAO;
import model.DocGia;
import model.RendererHighlighted;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhieuTraCT extends JFrame {
    Connect connect = new Connect();
    PhieuTraDAO dao = new PhieuTraDAO();
    private JPanel JPL;
    private JLabel lbltrangchu;
    private JLabel lbltime;
    private JTable tblChiTietMuon;
    private JTable tbltra;
    private JTable tbltract;
    private JButton btntra;
    private JTextField txttimkiem;
    private JTextField txtngaytra;
    private JTextField txthantra;
    private JTextField txttiencoc;
    private JTextField txtnhanlai;
    private JLabel lblday;
    private JTextField txtmatra;
    private JTextField txttendocgia;
    private JComboBox cbbdocgia;
    private JTextField txtsdt;
    private JButton btnthem;
    private JButton btnxoa;
    private JTable tblmuon;
    private JButton btnphieutra;
    private JButton btnphieutrachitiet;
    private JLabel lblnv;
    private JTextField txttrathem;
    private JTextField txtngaymuon;
    private JPanel Menu;
    private JMenu mnuHeThong;
    private JMenuItem ImnuDangXuat;
    private JMenuItem ImnuDoiPass;
    private JMenuItem Imnudoimapin;
    private JMenuItem ImnuGIoiThieu;
    private JMenu MnuQuanLi;
    private JMenuItem ImnuQuanLiKho;
    private JMenuItem ImnuQuanLiDocGia;
    private JMenuItem ImnuQuanLiNhanVien;
    private JMenu MnuQuanLiThem;
    private JMenuItem ImnuQuanLiLoaiSach;
    private JMenuItem ImnuQuanLITacGia;
    private JMenuItem ImnuQuanLiNXB;
    private JMenu MnuThongKe;
    private JMenuItem ImnuThongKeSach;
    private JMenuItem ImnuThongKeDocGia;
    private JMenuItem ImnuTongHop;
    private JMenu MnuTroGiup;
    private JMenuItem ImnuHuongDan;
    private JMenu MnuDongGop;
    private JMenuItem ImnuWeb;
    private JMenuItem ImnuBaoLoi;
    private JLabel lblTile;
    private JMenuItem ImnuQuanLiViTri;
    boolean check = false, check1 = false;
    int them = 0;
    DefaultComboBoxModel<DocGia> cbbmodeDocGia;
    String pass;
    int user, vaitro;
    DefaultTableModel dtbmuon;
    String lowmuon[] = {"mã mượn", "độc giả", "tên độc giả"};
    DefaultTableModel dtbmuonct;
    String lowmuonct[] = {"mã mượn", "mã sách", "ngày mượn", "hạn trả", "tiền cọc"};
    DefaultTableModel dtbtra;
    String lowtra[] = {"mã trả", "mã độc giả", "tên độc giả"};
    DefaultTableModel dtbtract;
    String lowtract[] = {"mã trả", "mã người nhận", "mã sách", "ngày mượn", "hạn trả", "ngày trả", "tiền trả"};

    public PhieuTraCT() {
        add(JPL);
        setSize(1500, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//nhấn x k bị tắt luôn
        setLocationRelativeTo(null);
        setVisible(true);
        run();
        chuChay();
        cbbmodeDocGia = new DefaultComboBoxModel<>();
        cbbmodeDocGia = (DefaultComboBoxModel<DocGia>) cbbdocgia.getModel();
        try {
            String sql = "select tendg,diachi,ngaysinh,socccd,sdt,madg,gioitinh from docgia where xoa=1";
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cbbmodeDocGia.addElement(new DocGia(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dtbmuon = new DefaultTableModel(lowmuon, 0);
        tblmuon.setModel(dtbmuon);
        dtbmuonct = new DefaultTableModel(lowmuonct, 0);
        tblChiTietMuon.setModel(dtbmuonct);
        dtbtra = new DefaultTableModel(lowtra, 0);
        tbltra.setModel(dtbtra);
        dtbtract = new DefaultTableModel(lowtract, 0);
        tbltract.setModel(dtbtract);
        BangPhieuMuon();
        BangPhieutra();
        //tìm kiếm
        RendererHighlighted renderer = new RendererHighlighted(txttimkiem);
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(tblChiTietMuon.getModel());

        tblChiTietMuon.setRowSorter(rowSorter);
        tblChiTietMuon.setDefaultRenderer(Object.class, renderer);
        //tìm kiếm
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                luutext();

            }
        });
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
        txttimkiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txttimkiem.getText();
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
                String text = txttimkiem.getText();
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
        lbltrangchu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FromChinh fromChinh = new FromChinh();
                fromChinh.setUser(user);
                fromChinh.setPass(pass);
                fromChinh.setVaitro(vaitro);
                dispose();
            }
        });
        final JMenuItem xoa = new JMenuItem();
        xoa.setText("xoá");


        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(xoa);
        popupMenu.addSeparator();

        tbltract.setComponentPopupMenu(popupMenu);


        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // force selection of row upon right-click (it works)
                        //buộc chọn hàng khi nhấp chuột phải (nó hoạt động)
                        int rowAtPoint = tbltract.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), tbltract));
                        if (rowAtPoint > -1) {
                            tbltract.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        int rowAtPoint = tbltract.rowAtPoint(SwingUtilities.convertPoint(null, new Point(0, 0), tbltract));
                        if (rowAtPoint > -1) {
                            tbltract.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO

            }
        });
        xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tbltract.getSelectedRow();
                if (index == -1) {
                    return;
                }
                if (check1) {

                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin phiếu  này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        String sql = "delete chiTietPHIEUtra where MATRA =? and MActs=?";
                        try {
                            PreparedStatement ps = connect.con().prepareStatement(sql);
                            ps.setInt(1, Integer.parseInt(String.valueOf(tbltract.getValueAt(index, 0))));
                            ps.setInt(2, Integer.parseInt(String.valueOf(dtbtract.getValueAt(index, 2))));

                            if (ps.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(null, "xoá thành công");
                            }

                        } catch (SQLException e1) {
                            e1.printStackTrace();

                            JOptionPane.showMessageDialog(null, "xoá thất bại");
                        }


                        BangChiTiettraxoa(txtmatra.getText());
                        return;
                    }
                }
                int chon1 = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá thông tin phiếu  này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon1 == JOptionPane.YES_OPTION) {
                    String sql = "update chiTietPHIEUtra set xoa=0 where MATRA = ? and MActs=?";
                    try {
                        PreparedStatement ps = connect.con().prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(String.valueOf(tbltract.getValueAt(index, 0))));
                        ps.setInt(2, Integer.parseInt(String.valueOf(dtbtract.getValueAt(index, 2))));

                        if (ps.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(null, "xoá thành công");
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();

                        JOptionPane.showMessageDialog(null, "xoá thất bại");
                    }

                    BangChiTiettra(txtmatra.getText());
                }

            }


        });

        cbbdocgia.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                txtmatra.setText("");
                txtsdt.setText("");
                txttendocgia.setText("");
                String madg = cbbmodeDocGia.getSelectedItem().toString();
                hienthitext(madg);
            }
        });
        tbltra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tbltra.getSelectedRow();
                if (index == -1) {
                    return;
                }

                cbbmodeDocGia.setSelectedItem(String.valueOf(tbltra.getValueAt(index, 1)));
                BangChiTiettra(String.valueOf(dtbtra.getValueAt(index, 0)));

            }
        });
        tblmuon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblmuon.getSelectedRow();
                if (index == -1) {
                    return;
                }
                cbbmodeDocGia.setSelectedItem(String.valueOf(tblmuon.getValueAt(index, 1)));
                BangChiTietmuon(String.valueOf(dtbmuon.getValueAt(index, 0)));


            }
        });
        btnphieutra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {

                    BangPhieutraXoa();

                    check = true;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Phiếu Trả Bị Xoá");
                    btnphieutra.setText("Hiện Danh Sách Phiếu Trả");
                    btnthem.setText("Khôi Phục");


                    return;
                }
                if (check) {
                    BangPhieutra();
                    check = false;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Phiếu Trả");
                    btnphieutra.setText("Hiện Danh Sách Phiếu Trả Bị Xoá");
                    btnthem.setText("Thêm");
                    return;
                }
            }
        });
        btnphieutrachitiet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check1) {

                    BangChiTiettraxoa(txtmatra.getText());
                    check1 = true;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Phiếu Trả Chi Tiết Bị Xoá");
                    btnphieutrachitiet.setText("Hiện Danh Sách Phiếu Trả Chi Tiết");


                    return;
                }
                if (check1) {

                    BangChiTiettra(txtmatra.getText());

                    check1 = false;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Phiếu Trả Chi Tiết");
                    btnphieutrachitiet.setText("Hiện Danh Sách Phiếu Trả Chi Tiết Bị Xoá");

                    return;
                }
            }
        });
        btntra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblChiTietMuon.getSelectedRow();
                int index1 = tblmuon.getSelectedRow();
                if (index == -1) {
                    return;
                }
                if (txtmatra.getText().isEmpty() || txttendocgia.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "vui lòng kiểm tra hoặc tạo phiếu trả");
                }
                String sql = "insert into chiTietPHIEUtra(MATRA, MAND, MActs, tienboithuong, ngaytra, ngaymuon, hantra, xoa) values(?,?,?,?,?,?,?,?)";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ps.setString(1, txtmatra.getText());
                    ps.setInt(2, user);
                    ps.setString(3, String.valueOf(tblChiTietMuon.getValueAt(index, 1)));
                    String tien = JOptionPane.showInputDialog(null, " số tiền phải trả ");
                    System.out.println(tien);
                    ps.setString(4, tien);
                    ps.setString(5, lblday.getText());
                    ps.setString(6, String.valueOf(tblChiTietMuon.getValueAt(index, 2)));
                    ps.setString(7, String.valueOf(tblChiTietMuon.getValueAt(index, 3)));
                    ps.setInt(8, 1);

                    xoactmuon(String.valueOf(tblChiTietMuon.getValueAt(index, 0)), String.valueOf(tblChiTietMuon.getValueAt(index, 1)), String.valueOf(tblChiTietMuon.getValueAt(index, 2)));
                    if (ps.executeUpdate() > 0) {

                    }
                    BangChiTiettra(txtmatra.getText());
                    BangChiTietmuon(String.valueOf(tblmuon.getValueAt(index1, 0)));
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "trả thất bại");
                }

            }
        });
        tbltract.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                super.mouseClicked(e);
                int index = tbltract.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtngaymuon.setText(String.valueOf(tbltract.getValueAt(index, 3)));
                txtngaytra.setText(String.valueOf(tbltract.getValueAt(index, 5)));
                txthantra.setText(String.valueOf(tbltract.getValueAt(index, 4)));
                int tiencoc = 50000;
                txttiencoc.setText(String.valueOf(tiencoc));
                int tientra = Integer.parseInt(String.valueOf(tbltract.getValueAt(index, 6)));
                if (tiencoc - tientra > 0) {
                    txtnhanlai.setText(String.valueOf((tiencoc - tientra)));
                    txttrathem.setText("0");
                } else {
                    txttrathem.setText(String.valueOf(-(tiencoc - tientra)));
                    txtnhanlai.setText("0");
                }


            }
        });
        btnthem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    if (txtmatra.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    dao.updateDaXoaPhieu(txtmatra.getText());
                    BangPhieutraXoa();

                    return;
                }


                dao.addPhieu(cbbmodeDocGia.getSelectedItem().toString());
                BangPhieutra();


            }
        });
        btnxoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtmatra.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin phiếu  này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        dao.deletePhieu(txtmatra.getText());
                        BangPhieutraXoa();

                    }
                    return;
                }
                int chon1 = JOptionPane.showConfirmDialog(null, "bạn  muốn xoá thông tin phiếu  này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon1 == JOptionPane.YES_OPTION) {
                    dao.deleteTamPhieu(txtmatra.getText());
                    BangPhieutra();
                }
            }
        });
    }

    public void xoactmuon(String ma, String masach, String ngaymuon) {
        String sql = "delete chiTietPHIEUMUON where MAMUON =? and macts=? and  ngaymuon=?";
        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, ma);
            ps.setString(2, masach);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = formatter.parse(ngaymuon);
                String strDate = formatter.format(date);
                ps.setString(3, strDate);

            } catch (ParseException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "lỗi ngày");
                return;
            }
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "trả thành công");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BangPhieutra() {
        dtbtra = (DefaultTableModel) tbltra.getModel();
        dtbtra.setRowCount(0);

        try {
            String sql = "select phieutra.MAtra,phieutra.MAdg,TENDG from phieutra JOIN DOCGIA on phieutra.MAdg = DOCGIA.MADG where phieutra.xoa =1 and Docgia.XOA =1";
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dtbtra.addRow(new Object[]{
                        rs.getInt(1), rs.getInt(2), rs.getString(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "lỗi dữ liệu vào bảng");
        }


    }


    public void BangPhieutraXoa() {
        dtbtra = (DefaultTableModel) tbltra.getModel();
        dtbtra.setRowCount(0);

        try {
            String sql = "select phieutra.MAtra,phieutra.MAdg,TENDG from phieutra JOIN DOCGIA on phieutra.MAdg = DOCGIA.MADG where phieutra.xoa =0 and Docgia.XOA =1";
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dtbtra.addRow(new Object[]{
                        rs.getInt(1), rs.getInt(2), rs.getString(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "lỗi dữ liệu vào bảng");
        }


    }


    public void BangChiTietmuon(String mamuon) {

        dtbmuonct = (DefaultTableModel) tblChiTietMuon.getModel();
        dtbmuonct.setRowCount(0);


        String sql = "select MAMUON,macts,NGAYMUON,hantra,tiencoc from chiTietPHIEUMUON where MAMUON=? and xoa=1";

        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, mamuon);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dtbmuonct.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)});

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void BangChiTiettra(String matra) {

        dtbtract = (DefaultTableModel) tbltract.getModel();
        dtbtract.setRowCount(0);


        String sql = "select MATRA, MAND, MActs, ngaymuon, hantra, ngaytra, tienboithuong from chiTietPHIEUtra where matra=? and xoa=1";

        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, matra);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dtbtract.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void BangChiTiettraxoa(String matra) {

        dtbtract = (DefaultTableModel) tbltract.getModel();
        dtbtract.setRowCount(0);


        String sql = "select MATRA, MAND, MActs, ngaymuon, hantra, ngaytra, tienboithuong from chiTietPHIEUtra where matra=? and xoa=0";

        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, matra);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dtbtract.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void run() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Date t = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern("HH:mm:ss");
                    String ta = sdf.format(t);
                    lbltime.setText(ta);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                    String ta1 = sdf1.format(new Date());
                    lblday.setText(ta1);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        thread.start();
    }

    public void hienthitext(String madg) {

        String sql = "select MAtra ,TENDG,SDT from DOCGIA JOIN phieutra p on DOCGIA.MADG = p.MAdg  where DOCGIA.MADG = ?";

        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, madg);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                txttendocgia.setText(rs.getString(2));
                txtsdt.setText(rs.getString(3));
                txtmatra.setText(rs.getString(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BangPhieuMuon() {
        dtbmuon = (DefaultTableModel) tblmuon.getModel();
        dtbmuon.setRowCount(0);

        try {
            String sql = "select MAmuon,D.MADG,TENDG from phieuMuon JOIN DOCGIA D on D.MADG = phieuMuon.MAdg where phieuMuon.xoa =1 and D.XOA =1";
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dtbmuon.addRow(new Object[]{
                        rs.getInt(1), rs.getInt(2), rs.getString(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "lỗi dữ liệu vào bảng");
        }


    }

    public void setUser(int user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setVaitro(int vaitro) {
        vaitro = 21;
        this.vaitro = vaitro;
        lblnv.setText(String.valueOf(vaitro));
    }

    public void chuChay() {
        Thread thread = new Thread() {
            public void run() {
                String txt = lblTile.getText();
                while (true) {
                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lblTile.setText(txt);
                }
            }

        };
        thread.start();
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

    public void luutext() {
        if (vaitro == 1) {
            btnphieutra.setEnabled(false);
            btnphieutrachitiet.setEnabled(false);
        }
    }
}
