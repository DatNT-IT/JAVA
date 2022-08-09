package views;

import service.SeviceNguoiDung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormDoiMaPin extends JFrame {
    SeviceNguoiDung seviceNguoiDung = new SeviceNguoiDung();
    private JLabel lblTitle;
    private JTextField txtUser;
    private JPasswordField txtPassNow;
    private JPasswordField txtpinNew;
    private JPasswordField txtComfirmpinNew;
    private JButton btnYes;
    private JButton btnHuy;
    private JPanel JPL;
    String pass;
    int user, vaitro;

    public FormDoiMaPin() {
        add(JPL);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        pack();
        txtUser.setEnabled(false);//khong cho sửa txtuser

        // mở form sẽ lưu giá trị
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                luuText();
            }
        });

        // quá trình đóng form sẽ r form chính
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        btnYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ma = Integer.parseInt(txtUser.getText());
                seviceNguoiDung.doipin(txtPassNow.getText(), pass, txtpinNew.getText(), txtComfirmpinNew.getText(), ma);
                dispose();
            }
        });
        btnHuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FromChinh fromChinh = new FromChinh();
                fromChinh.setUser(user);
                fromChinh.setPass(pass);
                fromChinh.setVaitro(vaitro);
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

    private boolean loi() {
        if (!txtPassNow.getText().equals(pass)) {
            JOptionPane.showMessageDialog(null, "bạn nhập sai pinhiện tại");
            txtPassNow.setText("");
            txtPassNow.requestFocus();
            return false;
        }

        if (txtpinNew.getText().isEmpty() || txtpinNew.getPassword().toString().isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống pin mới");
            txtpinNew.setText("");
            txtpinNew.requestFocus();
            return false;
        }

        if (txtComfirmpinNew.getText().isEmpty() || txtComfirmpinNew.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Không được để trống xác nhận pin");
            txtComfirmpinNew.setText("");
            txtComfirmpinNew.requestFocus();
            return false;
        }

        if (!txtpinNew.getText().equals(txtComfirmpinNew.getText())) {
            JOptionPane.showMessageDialog(null, "Vui lòng xác nhận đúng mã pin mới");
            txtComfirmpinNew.setText("");
            txtComfirmpinNew.requestFocus();
            return false;
        }
        return true;

    }

    // phương thức đẩy giá trị vào form
    private void luuText() {
        txtUser.setText(String.valueOf(user));
    }
}
