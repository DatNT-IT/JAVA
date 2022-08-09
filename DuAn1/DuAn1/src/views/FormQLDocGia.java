package views;

import Connects.Connect;
import Connects.DocGia.DataDocGia;
import Connects.DocGia.UpdateDocGia;
import DAO.DocGiaDao;
import com.toedter.calendar.JDateChooser;
import model.RendererHighlighted;
import service.SeviceDocGia;

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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormQLDocGia extends JFrame {

    DocGiaDao dao = new DocGiaDao();
    SeviceDocGia seviceDocGia = new SeviceDocGia();
    Connect con = new Connect();
    Calendar cld = Calendar.getInstance();

    JDateChooser ngaySinh = new JDateChooser(cld.getTime());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //15,16 hiện calendar chọn ngày , cho pane về boderlayout hoặc carlayout

    private JPanel JPL;
    private JTable tblDocGia;
    private JLabel lblTile;

    private JMenuItem ImnuDangXuat;
    private JMenuItem ImnuDoiPass;
    private JMenuItem Imnudoimapin;
    private JMenuItem ImnuGIoiThieu;
    private JMenuItem ImnuQuanLiKho;

    private JMenuItem ImnuQuanLiDocGia;
    private JMenuItem ImnuQuanLiNhanVien;

    private JMenuItem ImnuThongKeSach;
    private JMenuItem ImnuThongKeDocGia;
    private JMenuItem ImnuTongHop;
    private JMenuItem ImnuHuongDan;
    private JMenuItem ImnuWeb;
    private JMenuItem ImnuBaoLoi;
    private JButton btnNhanVien;
    private JButton btnMauNen;
    private JButton btnDocGia;
    private JButton btnKhoSach;
    private JButton btnTrangChu;
    private JMenu mnuHeThong;
    private JMenu MnuQuanLi;
    private JMenu MnuThongKe;
    private JMenu MnuTroGiup;
    private JMenu MnuDongGop;
    private JButton btnphieutra;
    private JTextField txtMaDocGia;
    private JTextField txtDiaChi;
    private JTextField txtTenDocGia;
    private JTextField txtCCCD;
    private JButton btnSua;
    private JButton btnThem;
    private JButton btnXoa;
    private JButton btnPhanLoai;
    private JTextField txtTimKiem;
    private JTextField txtSDT;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;

    private JMenu MnuQuanLiThem;
    private JMenuItem ImnuQuanLiLoaiSach;
    private JMenuItem ImnuQuanLITacGia;
    private JMenuItem ImnuQuanLiNXB;
    private JPanel paneNgaySinh;
    private JButton btnphieumuon;
    private JButton btnDeleteND;
    private JMenuItem ImnuQuanLiViTri;

    int user, vaitro;
    String pass;
    private boolean check = false;


    public FormQLDocGia() {
        add(JPL);
        setSize(1360, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
//        this.setResizable(false);
        setVisible(true);
        txtMaDocGia.setEnabled(false);
        new TieuDeNhay();
        chuChay();
        setTableModeDG();
        ngaySinh.setDateFormatString("yyyy-MM-dd");

        paneNgaySinh.add(ngaySinh);
        //tìm kiếm
        RendererHighlighted renderer = new RendererHighlighted(txtTimKiem);
        TableRowSorter<TableModel> rowSorter
                = new TableRowSorter<>(tblDocGia.getModel());

        tblDocGia.setRowSorter(rowSorter);
        tblDocGia.setDefaultRenderer(Object.class, renderer);
        //tìm kiếm


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
                luuText();
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

                    System.out.println("màu viền bạn chọn là màu : " + color);

                }
            }
        });
        btnTrangChu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FromChinh chinh = new FromChinh();
                chinh.setVaitro(vaitro);
                chinh.setUser(user);
                chinh.setPass(pass);
                dispose();
            }
        });

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkSDT = "0[0-9]{9}";
                String checkCCCD = "[0-9]{12}";
                int gioiTinh;
                if (rdoNam.isSelected() == true) {
                    gioiTinh = 1;
                } else {
                    gioiTinh = 0;
                }
                if (check) {
                    if (txtMaDocGia.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "không được để trống mã");
                        return;
                    }
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn khôi phục thông tin về độc giả này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        dao.updateDaXoa(txtMaDocGia.getText());
                        setTableModeDGXoa();
                        risec();
                    }
                    return;
                }

                if (!txtSDT.getText().matches(checkSDT)) {
                    JOptionPane.showMessageDialog(null, "số điện thoại phải là số và bắt đầu 0 (không được quá 10 số)");
                    return;
                } else if (!txtCCCD.getText().matches(checkCCCD)) {
                    JOptionPane.showMessageDialog(null, "CCCD phải là số và không được quá 12 số");
                    return;
                }
                String diachi = txtDiaChi.getText();
                String date;
                try {
                    // SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy , EEE");
                    date = simpleDateFormat.format(ngaySinh.getDate());

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "lỗi định dạng ngày");
                    return;
                }
                //String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh
                seviceDocGia.add(txtTenDocGia.getText(), txtSDT.getText(), gioiTinh, txtCCCD.getText(), diachi, date);
                setTableModeDG();
                risec();

            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtMaDocGia.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "không được để trống mã");
                    return;
                }

                if (check) {
                    int chon = JOptionPane.showConfirmDialog(null, "bạn có thực sự muốn xoá hết thông tin về người dùng này", "cảnh báo", JOptionPane.YES_NO_OPTION);
                    if (chon == JOptionPane.YES_OPTION) {
                        dao.delete(txtMaDocGia.getText());
                        setTableModeDGXoa();
                        risec();
                    }
                    return;
                }

                seviceDocGia.delete(txtMaDocGia.getText());
                setTableModeDG();
                risec();
            }

        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int gioiTinh;

                if (rdoNam.isSelected() == true) {
                    gioiTinh = 1;
                } else {
                    gioiTinh = 0;
                }
                int ma = Integer.parseInt(txtMaDocGia.getText());
//          int ma,String ten,int sdt,int gioitinh,int cccd, String diachi,String ngaySinh

                String diachi = txtDiaChi.getText();

                System.out.println(simpleDateFormat.format(ngaySinh.getDate()));
                seviceDocGia.update(ma, txtTenDocGia.getText(), txtSDT.getText(), gioiTinh, txtCCCD.getText(), txtDiaChi.getText(), simpleDateFormat.format(ngaySinh.getDate()));


                setTableModeDG();

            }
        });
//
        tblDocGia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = tblDocGia.getSelectedRow();
                if (index == -1) {
                    return;
                }
                //  mã độc giả","tên độc giả","Giới Tính","ngày sinh","số CCCD","SĐT","địa chỉ"
                txtMaDocGia.setText(String.valueOf((tblDocGia.getValueAt(index, 0))));
                txtTenDocGia.setText(String.valueOf(tblDocGia.getValueAt(index, 1)));
                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(tblDocGia.getValueAt(index, 3)));
                    ngaySinh.setDate(date);


                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                // ngaySinh.setDateFormatString(String.valueOf(dtb.getValueAt(index,3)));
                txtCCCD.setText(String.valueOf(tblDocGia.getValueAt(index, 4)));
                txtSDT.setText(String.valueOf(tblDocGia.getValueAt(index, 5)));
                txtDiaChi.setText(String.valueOf(tblDocGia.getValueAt(index, 6)));
                String gioitinh = String.valueOf(tblDocGia.getValueAt(index, 2));
                if (gioitinh.trim().equalsIgnoreCase("nam")) {
                    rdoNam.setSelected(true);

                } else {
                    rdoNu.setSelected(true);

                }
            }
        });
        //
        btnPhanLoai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                risec();
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

        btnDeleteND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!check) {
                    setTableModeDGXoa();
                    check = true;
                    JOptionPane.showMessageDialog(null, "đã hiện những người bị xoá");
                    btnDeleteND.setText("hiện những đang còn là độc giả");
                    btnThem.setText("khôi phục");
                    btnSua.setEnabled(false);
                    txtTenDocGia.setEnabled(false);
                    txtSDT.setEnabled(false);
                    txtDiaChi.setEnabled(false);
                    txtCCCD.setEnabled(false);
                    ngaySinh.setEnabled(false);
                    rdoNam.setEnabled(false);
                    rdoNu.setEnabled(false);

                    txtMaDocGia.setEnabled(true);
                    return;
                }
                if (check) {
                    setTableModeDG();
                    check = false;
                    JOptionPane.showMessageDialog(null, "đã hiện những người còn là độc giả");
                    btnDeleteND.setText("hiện những độc giả đã bị xoá");
                    btnThem.setText("thêm");
                    btnSua.setEnabled(true);
                    txtTenDocGia.setEnabled(true);
                    txtSDT.setEnabled(true);
                    txtDiaChi.setEnabled(true);
                    txtCCCD.setEnabled(true);
                    ngaySinh.setEnabled(true);
                    rdoNam.setEnabled(true);
                    rdoNu.setEnabled(true);

                    txtMaDocGia.setEnabled(false);
                    return;
                }
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

    public void setTableModeDG() {
        UpdateDocGia model = new UpdateDocGia(DataDocGia.getListDG());
        tblDocGia.setModel(model);
        Integer gtinh[] = {0, 1};
        JComboBox combo = new JComboBox(gtinh);

        TableColumn column = tblDocGia.getColumnModel().getColumn(2);
        column.setCellEditor(new DefaultCellEditor(combo));

    }

    public void setTableModeDGXoa() {
        UpdateDocGia model = new UpdateDocGia(DataDocGia.getListDGXoa());
        tblDocGia.setModel(model);
        Integer gtinh[] = {0, 1};
        JComboBox combo = new JComboBox(gtinh);

        TableColumn column = tblDocGia.getColumnModel().getColumn(2);
        column.setCellEditor(new DefaultCellEditor(combo));


    }

    public void risec() {

        txtTenDocGia.setText("");
        txtCCCD.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        rdoNam.setSelected(true);
        ngaySinh.setDateFormatString("yyyy-MM-dd");
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
        String arrtext[] = {"Quản Lí Độc Giả", "Quản", "Lí", "Độc", "Giả"};

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

    public void luuText() {
        if (vaitro == 1) {
            btnDeleteND.setEnabled(false);

        }
    }

    public static void main(String[] args) {
        new FormQLDocGia();
    }
}
