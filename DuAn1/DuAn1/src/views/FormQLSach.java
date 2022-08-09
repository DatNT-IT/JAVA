package views;

import Connects.Connect;
import Connects.KhoSach.DataSach;
import Connects.KhoSach.UpdateSach;
import DAO.SachDAO;
import com.toedter.calendar.JDateChooser;
import model.*;
import service.SeviceSach;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormQLSach extends JFrame {
    SeviceSach seviceSach = new SeviceSach();
    SachDAO sachDAO = new SachDAO();
    Connect con = new Connect();
    Calendar cld = Calendar.getInstance();

    JDateChooser ngayNhap = new JDateChooser(cld.getTime());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //13,14 hiện calendar chọn ngày , cho pane về boderlayout
    private JPanel JPL;
    private JTextField txtMaSach;
    private JTextField txtGiaBia;
    private JRadioButton rdoCo;
    private JRadioButton rdoKhong;
    private JComboBox cbbTheLoai;
    private JSpinner soLuong;

    private JButton btnThem;
    private JButton btnXoa;
    private JTextField txtTimKiem;
    private JTable tblSach;
    private JLabel trangchu;
    private JComboBox cbbTacGia;
    private JComboBox cbbNXB;

    private JLabel lblTile;
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
    private JTextField txtTinhTrang;
    private JPanel all;


    private JMenu MnuQuanLiThem;
    private JMenuItem ImnuQuanLiLoaiSach;
    private JMenuItem ImnuQuanLITacGia;
    private JMenuItem ImnuQuanLiNXB;
    private JPanel paneNgayNhap;
    private JButton btnphieutra;
    private JButton btnMuonDoc;
    private JTextField txtTenSach;

    private JButton btnphieumuon;
    private JButton btnNew;
    private JComboBox cbbViTri;
    private JButton btndeleteSach;
    private JSpinner sotrang;
    private JSpinner taibanlan;
    private JMenuItem ImnuQuanLiViTri;
    DefaultComboBoxModel<LoaiSach> loaisachmode;
    DefaultComboBoxModel<NXBSach> nxbmode;
    DefaultComboBoxModel<ChiTietTacGia> tacgiamode;
    DefaultComboBoxModel<ViTri> vitrimode;

    int user, vaitro, chon;
    String pass;
    boolean check = false;

    public FormQLSach() {
        add(JPL);
        setSize(1360, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        new TieuDeNhay();
        chuChay();

        txtMaSach.setEnabled(false);
        rdoCo.setSelected(true);
        ngayNhap.setDateFormatString("yyyy-MM-dd");
        paneNgayNhap.add(ngayNhap);
        soLuong.setValue(1);//bắt đầu từ 1
        SpinnerModel spinnerModel = new SpinnerNumberModel(1, // initial value
                1, // min
                100, // max
                1);// step

        soLuong.setModel(spinnerModel);
        //
        sotrang.setValue(1);//bắt đầu từ 1
        SpinnerModel spinnerModel1 = new SpinnerNumberModel(1, // initial value
                1, // min
                100, // max
                1);// step

        sotrang.setModel(spinnerModel1);
        //
        taibanlan.setValue(1);//bắt đầu từ 1
        SpinnerModel spinnerModel2 = new SpinnerNumberModel(1, // initial value
                1, // min
                100, // max
                1);// step

        taibanlan.setModel(spinnerModel2);


        BangSach();
        //ccb tg
        tacgiamode = new DefaultComboBoxModel<>();
        tacgiamode = (DefaultComboBoxModel<ChiTietTacGia>) cbbTacGia.getModel();
        try {
            String sql = "select matg,tentg,diachitg from tacgia where XOA =1";
            Statement st = con.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tacgiamode.addElement(new ChiTietTacGia(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ccb nxb
        nxbmode = new DefaultComboBoxModel<>();
        nxbmode = (DefaultComboBoxModel<NXBSach>) cbbNXB.getModel();
        try {
            String sql = "select manxb,tennxb,diachinxb from NXB where XOA =1";
            Statement st = con.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nxbmode.addElement(new NXBSach(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ccb loại sách
        loaisachmode = new DefaultComboBoxModel<>();
        loaisachmode = (DefaultComboBoxModel<LoaiSach>) cbbTheLoai.getModel();
        try {
            String sql = "select mals,tenls from LOAISACH where XOA =1";
            Statement st = con.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                loaisachmode.addElement(new LoaiSach(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //ccb vị trí
        vitrimode = new DefaultComboBoxModel<>();
        vitrimode = (DefaultComboBoxModel<ViTri>) cbbViTri.getModel();
        try {
            String sql = "select mavt,hangsach,osach,tusach,mota from vitri where XOA =1";
            Statement st = con.con().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                vitrimode.addElement(new ViTri(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //tìm kiếm
        RendererHighlighted renderer = new RendererHighlighted(txtTimKiem);
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(tblSach.getModel());

        tblSach.setRowSorter(rowSorter);
        tblSach.setDefaultRenderer(Object.class, renderer);
        //tìm kiếm
        // tabbedPane1.remove(1);
        btnMauNen.setMnemonic(KeyEvent.VK_B);//có thể nhấn alt + c thay vì click sẽ hiện bảng màu
        btnMauNen.setToolTipText(" alt + b");//hiện giới thiệu khi chuột để trc nút đó
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
            public void windowOpened(WindowEvent e) {
                luutext();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                new FromChinh();
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
        btnphieumuon.addActionListener(new ActionListener() {
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
                    Menu.setBackground(color);
                    all.setBackground(color);
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
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtTimKiem.getText();
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
                String text = txtTimKiem.getText();
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
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                risec();
            }
        });
        btndeleteSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {
                    BangSachXoa();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những Sách bị xoá");
                    btndeleteSach.setText("hiện những Sách đang còn ");
                    btnThem.setText("khôi phục");
                    txtTenSach.setEnabled(false);
                    ngayNhap.setEnabled(false);
                    txtGiaBia.setEnabled(false);
                    sotrang.setEnabled(false);
                    soLuong.setEnabled(false);
                    rdoCo.setEnabled(false);
                    rdoKhong.setEnabled(false);
                    taibanlan.setEnabled(false);
                    txtTinhTrang.setEnabled(false);
                    cbbNXB.setEnabled(false);
                    cbbTacGia.setEnabled(false);
                    cbbTheLoai.setEnabled(false);
                    cbbViTri.setEnabled(false);
                    txtMaSach.setEnabled(true);
                    return;
                }
                if (check) {
                    BangSach();
                    check = false;
                    JOptionPane.showMessageDialog(null, "đã hiện những Sách còn");
                    btndeleteSach.setText("hiện những Sách đã bị xoá");
                    btnThem.setText("thêm");


                    txtTenSach.setEnabled(true);
                    ngayNhap.setEnabled(true);
                    txtGiaBia.setEnabled(true);
                    sotrang.setEnabled(true);
                    soLuong.setEnabled(true);
                    rdoCo.setEnabled(true);
                    rdoKhong.setEnabled(true);
                    taibanlan.setEnabled(true);
                    txtTinhTrang.setEnabled(true);
                    cbbNXB.setEnabled(true);
                    cbbTacGia.setEnabled(true);
                    cbbTheLoai.setEnabled(true);
                    cbbViTri.setEnabled(true);
                    txtMaSach.setEnabled(false);
                    return;
                }
            }
        });
        tblSach.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = tblSach.getSelectedRow();
                if (index == -1) {
                    return;
                }
                txtMaSach.setText(String.valueOf(tblSach.getValueAt(index, 0)));
                txtTenSach.setText(String.valueOf(tblSach.getValueAt(index, 1)));
                tacgiamode.setSelectedItem(tblSach.getValueAt(index, 2));
                loaisachmode.setSelectedItem(tblSach.getValueAt(index, 3));
                taibanlan.setValue(tblSach.getValueAt(index, 4));
                txtGiaBia.setText(String.valueOf(tblSach.getValueAt(index, 5)));
                nxbmode.setSelectedItem(tblSach.getValueAt(index, 6));
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(tblSach.getValueAt(index, 7)));
                    ngayNhap.setDate(date);


                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                soLuong.setValue(tblSach.getValueAt(index, 8));
                sotrang.setValue(tblSach.getValueAt(index, 9));
                vitrimode.setSelectedItem(tblSach.getValueAt(index, 10));
                txtTinhTrang.setText(String.valueOf(tblSach.getValueAt(index, 11)));
                String muon = String.valueOf(tblSach.getValueAt(index, 12));
                if (muon.trim().equalsIgnoreCase("1")) {
                    rdoCo.setSelected(true);

                } else {
                    rdoKhong.setSelected(true);

                }
            }
        });
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int chomuon;
                if (rdoCo.isSelected() == true) {
                    chomuon = 1;
                } else {
                    chomuon = 0;
                }

                if (check) {
                    if (txtMaSach.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn khôi phục thông tin về sách này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        sachDAO.updateDaXoa(txtMaSach.getText());
                        BangSachXoa();
                        risec();

                    }
                    return;
                }
                int tienBia;
                try {
                    tienBia = Integer.parseInt(txtGiaBia.getText());
                    if (tienBia < 0) {
                        JOptionPane.showMessageDialog(null, "tiền phải lớ hơn 0");
                        return;
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "tiền không đúng định dạng");
                    return;
                }

                String date;
                try {

                    date = simpleDateFormat.format(ngayNhap.getDate());

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ngày sai định dạng");
                    return;
                }


                //String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh
                int trang = (int) sotrang.getValue();
                int taiban = (int) taibanlan.getValue();
                int soluong = (int) soLuong.getValue();


                seviceSach.addsach(txtTenSach.getText(), date, tienBia, trang, taiban, soluong, chomuon, txtTinhTrang.getText(), cbbTacGia.getSelectedItem().toString(), cbbNXB.getSelectedItem().toString(), cbbTheLoai.getSelectedItem().toString(), cbbViTri.getSelectedItem().toString());
                BangSach();
                risec();

            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaSach.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin về sách này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        sachDAO.delete(txtMaSach.getText());
                        BangSachXoa();
                        risec();
                    }
                    return;
                }

                seviceSach.deletesach(txtMaSach.getText());
                BangSach();
                risec();

            }
        });

        btnphieutra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PhieuTraCT phieuTraCT = new PhieuTraCT();
                phieuTraCT.setUser(user);
                phieuTraCT.setPass(pass);
                phieuTraCT.setVaitro(vaitro);
                dispose();
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

    public void risec() {
        txtMaSach.setText("");
        cbbNXB.setSelectedItem(0);
        cbbTheLoai.setSelectedItem(0);
        cbbTacGia.setSelectedItem(0);
        taibanlan.setValue(1);
        txtTinhTrang.setText("");
        cbbViTri.setSelectedItem(0);
        txtTenSach.setText("");
        ngayNhap.setDateFormatString("MM-dd-yyyy");
        txtGiaBia.setText("");
        sotrang.setValue(1);
        soLuong.setValue(1);
        rdoCo.setSelected(true);
        txtTimKiem.setText("");
        System.out.println(cbbTacGia.getSelectedItem());
    }

    public void BangSach() {
        UpdateSach model = new UpdateSach(DataSach.getListSach());
        tblSach.setModel(model);

    }

    public void BangSachXoa() {
        UpdateSach model = new UpdateSach(DataSach.getListSachXoa());
        tblSach.setModel(model);
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

    public class TieuDeNhay extends Thread {
        int icout = -1;
        String arrtext[] = {"Quản Lí Sách", "Quản", "Lí", "Sách"};

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
            btnThem.setEnabled(false);
            btndeleteSach.setEnabled(false);
            btnXoa.setEnabled(false);
        }
    }
}

