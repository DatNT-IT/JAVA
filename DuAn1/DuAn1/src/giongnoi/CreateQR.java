package giongnoi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

public class CreateQR {


    private final static String QR_CODE_IMAGE_PATH = "D:\\code_QR\\3.jpg";

    private static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        File file = new File(filePath);

        MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);


    }


    public static void main(String[] args) {
        try {
            generateQRCodeImage("3", 350, 350, QR_CODE_IMAGE_PATH);
            System.out.println("đã tạo qr");
        } catch (WriterException e) {
            System.out.println("Không thể tạo mã QR, WriterException:: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Không thể tạo mã QR, IOException:: " + e.getMessage());
        }
    }


}
