package io.bayrktlihn.template.util.captcha;

import io.bayrktlihn.template.util.ApplicationProperties;
import io.bayrktlihn.template.util.captcha.model.Captcha;
import io.bayrktlihn.template.util.encryption.impl.Aes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaptchaUtilTest {

    @Test
    void create() {
        Captcha captcha = CaptchaUtil.create();

        ApplicationProperties applicationProperties = ApplicationProperties.get();
        String aesKey = applicationProperties.getValue("aes.key", String.class);


        Aes aes = new Aes(aesKey);
        String encryptedCaptchaText = aes.encrypt(captcha.getText());

        System.out.println(captcha.getText());
        System.out.println(captcha.getBase64EncodedImage());
        //send to client
        System.out.println(encryptedCaptchaText);

        //assume user entered correct captcha text and validate it
        Assertions.assertEquals(captcha.getText(), aes.decrypt(encryptedCaptchaText));


    }

}