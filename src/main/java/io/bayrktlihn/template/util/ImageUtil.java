package io.bayrktlihn.template.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class ImageUtil {
    public static byte[] toByteArray(BufferedImage img, String imageFileType) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, imageFileType, baos);
            return baos.toByteArray();
        } catch (Throwable e) {
            throw new RuntimeException();
        }
    }
}
