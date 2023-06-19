package io.bayrktlihn.template.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
}
