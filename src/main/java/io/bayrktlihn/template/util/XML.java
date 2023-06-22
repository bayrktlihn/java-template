package io.bayrktlihn.template.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XML {
    public static <T> String stringify(T entity) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter xml = new StringWriter();
            marshaller.marshal(entity, xml);
            return xml.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(String xml, Class<T> clazz) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader stringReader = new StringReader(xml);

            return (T) unmarshaller.unmarshal(stringReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
