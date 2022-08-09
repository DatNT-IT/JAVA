package views;

import Connects.Connect;
import javazoom.jl.player.Player;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FromChay extends JFrame {
    private JPanel JPL;
    private JProgressBar pro;

    public FromChay() {
        add(JPL);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false); // chống chỉnh sửa size frame
        new TieuDeNhay();
        setVisible(true);
        pack();//Phương thức pack () được định nghĩa trong lớp Window
        // trong Java và nó định kích thước khung sao cho tất cả nội dung của nó bằng hoặc cao hơn kích thước ưa thích của chúng.
        chay();
        if (pro.getValue() == 100) {
            FromDangNhap dangNhap = null;
            try {
                dangNhap = new FromDangNhap();
                dangNhap.setVisible(true);
                dispose();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    private void chay() {

        int time = 0;
        // pro.setForeground();
        while (time <= 100) {

            if (time < 30) {
                time += 5;
                pro.setValue(time);
                pro.setString(time + "%");
                pro.setForeground(Color.red);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (time > 30 && time < 75) {
                time += 3;
                pro.setValue(time);
                pro.setString(time + "%");
                pro.setForeground(Color.ORANGE);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                time += 1;
                pro.setValue(time);
                pro.setString(time + "%");
                pro.setForeground(Color.GREEN);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
        pro.setString("Done!");

    }

    public static void main(String[] args) {

        new FromChay();


//            try {
//
//                FileInputStream fileInputStream = new FileInputStream("music.mp3");
//                Player player =new Player(fileInputStream);
//                player.play();
//                System.out.println("đã chạy");
//
//
//            }catch (Exception e1){
//                e1.printStackTrace();
//            }


    }


    public class TieuDeNhay extends Thread {

        int icout = -1;
        String arrtext[] = {"HELLO", "CHÀO", "BẠN", "ĐỌC"};

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
}
