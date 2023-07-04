package io.bayrktlihn.template.util.captcha;

import com.google.code.kaptcha.util.Config;
import io.bayrktlihn.template.util.ImageUtil;
import io.bayrktlihn.template.util.captcha.model.Captcha;

import java.awt.image.BufferedImage;
import java.util.Base64;
import java.util.Properties;


import com.google.code.kaptcha.impl.DefaultKaptcha;

public class CaptchaUtil {

    public static Captcha create(){

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(defaultProperties()));


        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);

        byte[] bytes = ImageUtil.toByteArray(image, "jpeg");


        String base64EncodedImage = Base64.getEncoder().encodeToString(bytes);
        return new Captcha(text, base64EncodedImage);
    }

    private static Properties defaultProperties() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "175");
        properties.setProperty("kaptcha.image.height", "60");
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        properties.setProperty("kaptcha.textproducer.font.color", "255,255,255");
        properties.setProperty("kaptcha.textproducer.char.length", "8");
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "gray");
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        properties.setProperty("kaptcha.noise.color", "white");
        properties.put("kaptcha.background.clear.from", "white");
        properties.put("kaptcha.background.clear.to", "black");
        properties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
        return properties;
    }


}
