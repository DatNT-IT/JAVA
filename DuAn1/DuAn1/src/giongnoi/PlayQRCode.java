package giongnoi;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import views.FormDangNhapQR;
import views.FromDangNhap;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayQRCode extends JFrame {
    private JPanel JPL;
    private JPanel camera;


    public PlayQRCode() {
        add(JPL);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setVisible(true);

        Webcam webcam = Webcam.getDefault();   //Generate Webcam Object
        webcam.setViewSize(new Dimension(320, 240));
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setMirrored(false);

        add(webcamPanel);


////hiện bên đăng nhập cam phải Override run
        Thread thread = new Thread() {
            @Override
            public void run() {
                setVisible(true);
                pack();
                do {
                    try {
                        BufferedImage image = webcam.getImage();
                        LuminanceSource source = new BufferedImageLuminanceSource(image);
                        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                        Result result = new MultiFormatReader().decode(bitmap);
                        if (result.getText() != null) {
                            System.out.println(result.getText());
                            dispose();
                            webcam.close();

                            new FormDangNhapQR().setUser(result.getText());


                            break;
                        }

                    } catch (NotFoundException e) {
                        //pass
                    }

                } while (true);
            }
        };
        thread.start();
    }

    public static void main(String[] args) {
        new PlayQRCode();
    }
}
