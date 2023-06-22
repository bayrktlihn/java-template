package io.bayrktlihn.template;

import io.bayrktlihn.template.util.ApplicationProperties;

import java.util.Properties;

public class TemplateApplication {
    public static void main(String[] args) {
        ApplicationProperties applicationProperties = ApplicationProperties.get();

        Properties values = applicationProperties.getValues();

        for (String stringPropertyName : values.stringPropertyNames()) {
            Object o = values.get(stringPropertyName);
            System.out.println(stringPropertyName + " " + o);
        }


    }
}
