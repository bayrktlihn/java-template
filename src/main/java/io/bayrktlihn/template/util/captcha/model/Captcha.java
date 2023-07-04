package io.bayrktlihn.template.util.captcha.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Captcha {
    private String text;
    private String base64EncodedImage;
}
