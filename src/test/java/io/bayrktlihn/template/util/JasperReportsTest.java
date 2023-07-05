package io.bayrktlihn.template.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.bayrktlihn.template.util.date.Dates;
import org.junit.jupiter.api.Test;

import io.bayrktlihn.template.entity.Person;
import io.bayrktlihn.template.enums.Gender;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

class JasperReportsTest {

	@Test
	void test() {
		Map<String, Object> params = new HashMap<>();
		Path path = PathUtil.getPathFromClasspath("jasper-reports/Bayrktlihn.jrxml");
		Path imagePath = PathUtil.getPathFromClasspath("jasper-reports/img/Baykar.png");

		List<Person> persons = new ArrayList<>();
		persons.add(Person.builder().firstName("Alihan").lastName("Bayraktar").gender(Gender.MAN)
				.birthDate(Dates.createDate(1995, 10, 4)).build());
		persons.add(Person.builder().firstName("Merve").lastName("Bayraktar").gender(Gender.WOMAN)
				.birthDate(Dates.createDate(1998, 6, 18)).build());
		persons.add(Person.builder().firstName("Deniz").lastName("Bayraktar").gender(Gender.WOMAN)
				.birthDate(Dates.createDate(1995, 11, 29)).build());

		try {
			params.put("image", Files.newInputStream(imagePath));
			params.put("persons", new JRBeanCollectionDataSource(persons));
			InputStream inputStream = Files.newInputStream(path);

			byte[] createdPdf = JasperReports.createPdf(inputStream, params);

			String encoded = Base64.getEncoder().encodeToString(createdPdf);

			System.out.println(encoded);

			inputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
