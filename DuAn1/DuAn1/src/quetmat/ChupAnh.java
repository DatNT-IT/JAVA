package quetmat;

import com.github.sarxos.webcam.Webcam;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ChupAnh {
    public static void main(String[] args) throws IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();
        ImageIO.write(webcam.getImage(), "PNG", new File("it.png"));
        System.out.println("Snapshot taken from cam and stored at " + new File("").getAbsolutePath() + "\\it.png");
    }
}
