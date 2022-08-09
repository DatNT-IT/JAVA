import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChonChuongTrinh extends JFrame {

    private JPanel JPL;
    private JButton quảnLýSinhViênButton;
    private JButton quảnLýĐiểmSinhButton;
    private JButton btndangxuat;
    private JButton btnthoat;

    public ChonChuongTrinh(){
        add(JPL);
        setTitle("chọn chương trình");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        quảnLýSinhViênButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QLSV().setVisible(true);
                dispose();
            }
        });
        quảnLýĐiểmSinhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QLDSV().setVisible(true);
                dispose();
            }
        });
        btnthoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btndangxuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new User().setVisible(true);
                dispose();
            }
        });
    }


}
