package io.bayrktlihn.template;

import io.bayrktlihn.template.util.ApplicationProperties;

import java.util.Properties;

public class TemplateApplication {
    public static void main(String[] args) {
        Properties applicationProperties = ApplicationProperties.getProperties();

        System.out.println(applicationProperties);
    }
}
