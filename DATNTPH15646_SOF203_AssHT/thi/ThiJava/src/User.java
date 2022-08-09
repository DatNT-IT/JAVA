import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User  extends JFrame{
    private JPanel JPL;
    private JLabel lblname;
    private JTextField txtname;
    private JLabel lblpass;
    private JPasswordField password;
    private JButton btnlogin;
    private JButton btncancel;
    SQLServerDataSource ds = new SQLServerDataSource();
    Connection con ;
    public User() {
        ds.setUser("sa");
        ds.setPassword("123");
        ds.setServerName("DESKTOP-PFMTEUO\\SQLEXPRESS");
        ds.setPortNumber(1433);
        ds.setDatabaseName("ASSJAVA3");
        add(JPL);
        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtname.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"nhập user");
                }else if (password.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"nhập pass");
                }else {
                    try {
con = ds.getConnection();
                        String sql = "SELECT * FROM dbo.usres WHERE username =? AND password =?";
                        PreparedStatement pstat = con.prepareStatement(sql);
                        pstat.setString(1,txtname.getText());
                        pstat.setString(2,password.getText());
                        ResultSet rs = pstat.executeQuery();
                        if (rs.next()){
                            JOptionPane.showMessageDialog(null,"đăng nhập thành công");
                            new ChonChuongTrinh().setVisible(true);
                            dispose();//đóng ctrinh
                        }else {
                            JOptionPane.showMessageDialog(null,"xem lại user hoặc pass");
                        }
                    }catch (Exception e1){
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null,"lỗi");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        User x = new User();
        x.setTitle("đăng nhập");
        x.setSize(800,500);
        x.setLocationRelativeTo(null);
        x.setDefaultCloseOperation(EXIT_ON_CLOSE);
        x.setVisible(true);
    }
}
