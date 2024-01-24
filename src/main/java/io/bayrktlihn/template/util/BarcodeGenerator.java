package io.bayrktlihn.template.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class BarcodeGenerator {

    public static byte[] createQRImage(String text, int width, int height, String fileType)
            throws WriterException, IOException {

        Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(byteMatrix, fileType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

    }

    public static byte[] createQRImage(String text, int size, String fileType)
            throws WriterException, IOException {

        return createQRImage(text, size, size, fileType);

    }

    public static byte[] createCode128Image(String text, int width, int height, String fileType)
            throws IOException {

        Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        Code128Writer code128Writer = new Code128Writer();
        BitMatrix byteMatrix = code128Writer.encode(text, BarcodeFormat.CODE_128, width, height, hintMap);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(byteMatrix, fileType, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

    }

    public static byte[] createCode128Image(String text, int size, String fileType)
            throws IOException {

        return createCode128Image(text, size, size, fileType);

    }
}
