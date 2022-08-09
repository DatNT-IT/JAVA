package views;

import Connects.Connect;
import Connects.KhoSach.DataSach;
import Connects.KhoSach.UpdateSach;
import DAO.PhieuMuonDAO;
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

public class PhieuMuonChiTiet extends JFrame {
    Connect connect = new Connect();
    PhieuMuonDAO dao = new PhieuMuonDAO();
    private JPanel JPL;


    private JTextField txttendocgia;
    private JLabel lbltime;
    private JTable tblsach;
    private JTable tblphieumuon;
    private JTextField txtmamuon;
    private JTable tblchitiet;
    private JRadioButton rdonam;
    private JRadioButton rdonu;
    private JComboBox cbbmadocgia;
    private JTextField txtsdt;
    private JTextField txtdiachi;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JTextField txttimkiemsach;
    private JTextField txttimkiemmuonchitiet;
    private JTextField txttimkiemmuon;
    private JButton btnchitietmuon;
    private JButton btnphieumuon;
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
    private JLabel lbltrangchu;
    private JLabel lblday;
    private JLabel lblTile;
    private JMenuItem ImnuQuanLiViTri;
    private boolean check = false, check1 = false;
    String pass;
    int user, vaitro, themtt = 0;
    DefaultComboBoxModel<DocGia> cbbmodeDocGia;
    DefaultTableModel x;

    String lowxt[] = {"mã mượn", "mã sách", "ngày mượn", "hạn trả", "tiền cọc",};

    DefaultTableModel dtbsach;
    String lowsach[] = {"mã sách", "tên sách", "tác giả", "thể loại", "xnb", "tái bản lần", "số trang", "ngày nhập", "giá bìa", "vị trí", "được mượn", "tình trạng"};
    DefaultTableModel dtbphieumuon;
    String lowmuon[] = {"mã mượn", "độc giả", "tên độc giả"};

    public PhieuMuonChiTiet() {
        add(JPL);
        setSize(1500, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//nhấn x k bị tắt luôn
        setLocationRelativeTo(null);
        setVisible(true);

        x = new DefaultTableModel(lowxt, 0);
        tblchitiet.setModel(x);
        rdonam.setSelected(true);

        dtbsach = new DefaultTableModel(lowsach, 0);
        tblsach.setModel(dtbsach);
        dtbphieumuon = new DefaultTableModel(lowmuon, 0);
        tblphieumuon.setModel(dtbphieumuon);
        run();
        chuChay();
        //ccb tg
        cbbmodeDocGia = new DefaultComboBoxModel<>();
        cbbmodeDocGia = (DefaultComboBoxModel<DocGia>) cbbmadocgia.getModel();
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
        BangPhieuMuon();
        BangSach();

        //tìm kiếm sách
        RendererHighlighted renderer = new RendererHighlighted(txttimkiemsach);
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(tblsach.getModel());

        tblsach.setRowSorter(rowSorter);
        tblsach.setDefaultRenderer(Object.class, renderer);
        //tìm kiếm sách
        //tìm kiếm phieu mượn
        RendererHighlighted renderer1 = new RendererHighlighted(txttimkiemmuon);
        TableRowSorter<TableModel> rowSorter1
                = new TableRowSorter<>(tblphieumuon.getModel());

        tblphieumuon.setRowSorter(rowSorter1);
        tblphieumuon.setDefaultRenderer(Object.class, renderer1);
        //tìm kiếm phiếu mượn
        //tìm kiếm chi tiết
        RendererHighlighted renderer2 = new RendererHighlighted(txttimkiemmuonchitiet);
        TableRowSorter<TableModel> rowSorter2
                = new TableRowSorter<>(tblchitiet.getModel());

        tblchitiet.setRowSorter(rowSorter2);
        tblchitiet.setDefaultRenderer(Object.class, renderer2);
        //tìm kiếm chi tiết
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
                luuText();
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
        txttimkiemsach.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txttimkiemsach.getText();
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
                String text = txttimkiemsach.getText();
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
        txttimkiemmuon.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txttimkiemmuon.getText();
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
                String text = txttimkiemmuon.getText();
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
        txttimkiemmuonchitiet.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txttimkiemmuonchitiet.getText();
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
                String text = txttimkiemmuonchitiet.getText();
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
        //menu table
        final JMenuItem them = new JMenuItem();
        them.setText("thêm");


        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(them);
        popupMenu.addSeparator();

        tblsach.setComponentPopupMenu(popupMenu);


        popupMenu.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // force selection of row upon right-click (it works)
                        //buộc chọn hàng khi nhấp chuột phải (nó hoạt động)
                        int rowAtPoint = tblsach.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), tblsach));
                        if (rowAtPoint > -1) {
                            tblsach.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        int rowAtPoint = tblsach.rowAtPoint(SwingUtilities.convertPoint(null, new Point(0, 0), tblsach));
                        if (rowAtPoint > -1) {
                            tblsach.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // TODO

            }
        });

        them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tblsach.getSelectedRow();
                if (index == -1) {
                    return;
                }
                if (Integer.parseInt(String.valueOf(tblsach.getValueAt(index, 10))) == 1) {
                    String sql = "insert into chiTietPHIEUMUON(MAMUON, macts, NGAYMUON, hantra, tiencoc, xoa) values (?,?,?,DATEADD(day , 7,?),?,?)";
                    try {
                        PreparedStatement ps = connect.con().prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(txtmamuon.getText()));
                        ps.setInt(2, Integer.parseInt(String.valueOf(tblsach.getValueAt(index, 0))));
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = formatter.parse(lbltime.getText());
                            String strDate = formatter.format(date);
                            ps.setString(3, strDate);
                            ps.setString(4, strDate);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "lỗi ngày");
                        }
                        ps.setInt(5, 50000);
                        ps.setInt(6, 1);
                        if (ps.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(null, "thêm mượn thành công");
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();

                        JOptionPane.showMessageDialog(null, "thất bại thêm mượn");
                    }


                    BangChiTiet(txtmamuon.getText());
                    return;
                }
                JOptionPane.showMessageDialog(null, "sách này không được mượn");
            }
        });

        //menu table
        final JMenuItem giahan = new JMenuItem();
        giahan.setText("gia hạn");
        final JMenuItem xoa = new JMenuItem();
        xoa.setText("xoá");


        final JPopupMenu popupMenu1 = new JPopupMenu();

        popupMenu1.add(giahan);
        popupMenu1.addSeparator();
        popupMenu1.add(xoa);
        tblchitiet.setComponentPopupMenu(popupMenu1);


        popupMenu1.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // force selection of row upon right-click (it works)
                        //buộc chọn hàng khi nhấp chuột phải (nó hoạt động)
                        int rowAtPoint = tblchitiet.rowAtPoint(SwingUtilities.convertPoint(popupMenu1, new Point(0, 0), tblchitiet));
                        if (rowAtPoint > -1) {
                            tblchitiet.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {

                        int rowAtPoint = tblchitiet.rowAtPoint(SwingUtilities.convertPoint(null, new Point(0, 0), tblsach));
                        if (rowAtPoint > -1) {
                            tblchitiet.setRowSelectionInterval(rowAtPoint, rowAtPoint);
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
                int index = tblchitiet.getSelectedRow();
                if (index == -1) {
                    return;
                }
                if (!check1) {


                    int chon = JOptionPane.showConfirmDialog(null, "bạn có  muốn xoá thông tin về phiếu mượn này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {

                        String sql = "update chiTietPHIEUMUON set xoa=? where macts=? and NGAYMUON=?";
                        try {
                            PreparedStatement ps = connect.con().prepareStatement(sql);
                            ps.setInt(1, 0);
                            ps.setInt(2, Integer.parseInt(String.valueOf(tblchitiet.getValueAt(index, 1))));
                            ps.setString(3, String.valueOf(tblchitiet.getValueAt(index, 2)));


                            if (ps.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(null, "xoá thành công");
                            }

                        } catch (SQLException e1) {
                            e1.printStackTrace();

                            JOptionPane.showMessageDialog(null, "xoá thất bại");
                        }
                    }
                    BangChiTiet(txtmamuon.getText());

                    return;

                }
                int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin về phiếu mượn này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon == JOptionPane.YES_OPTION) {
                    String sql = "delete  chiTietPHIEUMUON where macts=? and NGAYMUON=?,mamuon=?";
                    try {
                        PreparedStatement ps = connect.con().prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(String.valueOf(tblchitiet.getValueAt(index, 1))));
                        ps.setString(2, String.valueOf(tblchitiet.getValueAt(index, 2)));
                        ps.setString(3, String.valueOf(tblchitiet.getValueAt(index, 0)));


                        if (ps.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(null, "xoá thành công");
                        }

                    } catch (SQLException e1) {
                        e1.printStackTrace();

                        JOptionPane.showMessageDialog(null, "xoá thất bại");
                    }

                }
                BangChiTietXoa(txtmamuon.getText());
            }

        });
        giahan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = tblchitiet.getSelectedRow();

                if (index == -1) {
                    return;
                }
                if (check1) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn khôi phục  thông tin về phiếu mượn này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        String sql = "update chiTietPHIEUMUON set xoa=1 where MAMUON = ? and macts=?";
                        try {
                            PreparedStatement ps = connect.con().prepareStatement(sql);
                            ps.setString(1, String.valueOf(tblchitiet.getValueAt(index, 0)));
                            ps.setString(2, String.valueOf(tblchitiet.getValueAt(index, 1)));

                            if (ps.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(null, "cập nhật lại  thành công");
                            }


                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "cập nhật lại thất bại");
                        }
                    }
                    BangChiTietXoa(txtmamuon.getText());

                    return;
                }
                String ngaythem = null;
                String sql1 = "select DATEADD(day , ?,?)";

                try {
                    PreparedStatement ps1 = connect.con().prepareStatement(sql1);
                    String ma = JOptionPane.showInputDialog(null, " số ngày muốn gia hạn thêm ");
                    String ngay = String.valueOf(tblchitiet.getValueAt(index, 3));
                    ps1.setInt(1, Integer.parseInt(ma));
                    ps1.setString(2, ngay);
                    ResultSet rs = ps1.executeQuery();
                    while (rs.next()) {

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = formatter.parse(rs.getString(1));
                            String strDate = formatter.format(date);
                            ngaythem = strDate;
                        } catch (ParseException e1) {
                            e1.printStackTrace();

                            JOptionPane.showMessageDialog(null, "lỗi ngày");
                        }
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();

                    JOptionPane.showMessageDialog(null, "lỗi");

                }
                String sql = "Update chiTietPHIEUMUON\n" +
                        "set [hantra] = stuff(convert(varchar(25),[hantra],25),1,10,?)\n" +
                        "Where macts=? and NGAYMUON=?";
                try {
                    PreparedStatement ps = connect.con().prepareStatement(sql);
                    ;
                    ps.setString(1, ngaythem);
                    System.out.println(ngaythem + "\t" + String.valueOf(tblchitiet.getValueAt(index, 1)) + "\t" + String.valueOf(tblchitiet.getValueAt(index, 3)));
                    ps.setString(2, String.valueOf(tblchitiet.getValueAt(index, 1)));
                    ps.setString(3, String.valueOf(tblchitiet.getValueAt(index, 2)));
                    if (ps.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(null, "gia hạn thành công");
                    }
                    BangChiTiet(txtmamuon.getText());
                } catch (SQLException e1) {
                    e1.printStackTrace();

                    JOptionPane.showMessageDialog(null, "gia hạn thất bại");

                }
            }

        });
        tblphieumuon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblphieumuon.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtmamuon.setText(String.valueOf(dtbphieumuon.getValueAt(index, 0)));
                cbbmodeDocGia.setSelectedItem(String.valueOf(dtbphieumuon.getValueAt(index, 1)));
                hienthitext(String.valueOf(dtbphieumuon.getValueAt(index, 1)));
                BangChiTiet(txtmamuon.getText());


            }
        });

        cbbmadocgia.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String madg = cbbmodeDocGia.getSelectedItem().toString();
                hienthitext(madg);
            }
        });
        btnchitietmuon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check1) {
                    BangChiTietXoa(txtmamuon.getText());
                    check1 = true;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Bị Xoá");
                    btnchitietmuon.setText("Hiện Danh Sách Đang Mượn");
                    giahan.setText("Khôi Phục");

                    return;
                }
                if (check1) {
                    BangChiTiet(txtmamuon.getText());
                    check1 = false;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Đang Mượn");
                    btnchitietmuon.setText("Hiện Danh Sách Bị Xoá");
                    giahan.setText("Gia Hạn");

                    return;
                }
            }
        });
        btnphieumuon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {

                    BangPhieuMuonXoa();
                    check = true;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Phiếu Mượn Bị Xoá");
                    btnphieumuon.setText("Hiện Danh Sách Phiếu Mượn");

                    btnThem.setText("Khôi Phục");
                    btnSua.setEnabled(false);

                    giahan.setEnabled(false);

                    return;
                }
                if (check) {

                    BangPhieuMuon();
                    check = false;
                    JOptionPane.showMessageDialog(null, "Đã Hiện Danh Sách Phiếu  Mượn");
                    btnphieumuon.setText("Hiện Danh Sách Phiếu Mượn Bị Xoá");
                    btnThem.setText("Thêm");
                    btnSua.setEnabled(true);
                    giahan.setEnabled(true);
                    return;
                }
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (check) {
                    if (txtmamuon.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    dao.updateDaXoaPhieu(txtmamuon.getText());
                    BangPhieuMuonXoa();

                    return;
                }
                //String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh
                dao.addPhieu(cbbmodeDocGia.getSelectedItem().toString());
                BangPhieuMuon();


            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtmamuon.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin phiếu  này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        dao.deletePhieu(txtmamuon.getText());
                        BangPhieuMuonXoa();

                    }
                    return;
                }
                int chon1 = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá thông tin phiếu  này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                if (chon1 == JOptionPane.YES_OPTION) {
                    dao.deleteTamPhieu(txtmamuon.getText());
                    BangPhieuMuon();
                }
            }
        });

    }

    public void BangPhieuMuon() {
        dtbphieumuon = (DefaultTableModel) tblphieumuon.getModel();
        dtbphieumuon.setRowCount(0);

        try {
            String sql = "select MAmuon,D.MADG,TENDG from phieuMuon JOIN DOCGIA D on D.MADG = phieuMuon.MAdg where phieuMuon.xoa =1 and D.XOA =1";
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dtbphieumuon.addRow(new Object[]{
                        rs.getInt(1), rs.getInt(2), rs.getString(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "lỗi dữ liệu vào bảng");
        }


    }

    public void BangPhieuMuonXoa() {
        dtbphieumuon = (DefaultTableModel) tblphieumuon.getModel();
        dtbphieumuon.setRowCount(0);

        try {
            String sql = "select MAmuon,D.MADG,TENDG from phieuMuon JOIN DOCGIA D on D.MADG = phieuMuon.MAdg where phieuMuon.xoa =0 and D.XOA =1";
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dtbphieumuon.addRow(new Object[]{
                        rs.getInt(1), rs.getInt(2), rs.getString(3)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "lỗi dữ liệu vào bảng");
        }


    }

    public void BangSach() {
        dtbsach = (DefaultTableModel) tblsach.getModel();
        dtbsach.setRowCount(0);

        try {
            String sql = "SELECT MACTS,TENSACH,tentg,TENLS,TENNXB,lantaiban,SOTRANG,NGAYNHAP,giabia,mota,duocmuon,TINHTRANG FROM CHITIETSACH JOIN tacgia t on t.matg = CHITIETSACH.MATG JOIN LOAISACH L on L.MALS = CHITIETSACH.MALS JOIN NXB N on N.MANXB = CHITIETSACH.MANXB JOIN VITRI V on V.MAVT = CHITIETSACH.MAVT\n" +
                    "where CHITIETSACH.xoa =1";
            Statement st = connect.con().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dtbsach.addRow(new Object[]{
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "lỗi dữ liệu vào bảng");
        }
    }

    public void hienthitext(String madg) {

        String sql = "select TENDG,SDT,GIOITINH,diachi from DOCGIA  where DOCGIA.MADG = ?";

        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, madg);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                txttendocgia.setText(rs.getString(1));
                txtsdt.setText(rs.getString(2));
                int gioitinh = rs.getInt(3);
                if (gioitinh == 1) {
                    rdonam.setSelected(true);
                    rdonu.setSelected(false);
                } else {
                    rdonu.setSelected(true);
                    rdonam.setSelected(false);
                }
                txtdiachi.setText(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void BangChiTiet(String mamuon) {

        x = (DefaultTableModel) tblchitiet.getModel();
        x.setRowCount(0);


        String sql = "select MAMUON,macts,NGAYMUON,hantra,tiencoc from chiTietPHIEUMUON where MAMUON=? and xoa=1";

        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, mamuon);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public void BangChiTietXoa(String mamuon) {

        x = (DefaultTableModel) tblchitiet.getModel();
        x.setRowCount(0);


        String sql = "select MAMUON,macts,NGAYMUON,hantra,tiencoc from chiTietPHIEUMUON where MAMUON=? and xoa=0";

        try {
            PreparedStatement ps = connect.con().prepareStatement(sql);
            ps.setString(1, mamuon);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                x.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});

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
                    lblday.setText(ta);
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                    String ta1 = sdf1.format(new Date());
                    lbltime.setText(ta1);
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

    public void luuText() {
        if (vaitro == 1) {
            btnchitietmuon.setEnabled(false);
            btnphieumuon.setEnabled(false);
        }
    }
}
