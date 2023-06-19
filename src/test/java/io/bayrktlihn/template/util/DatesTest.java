package io.bayrktlihn.template.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import io.bayrktlihn.template.util.Dates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatesTest {

	@Test
	void startOfDay() {
		Date date = Dates.createDate(1995, 10, 4);
		String dateString = Dates.toString(date, "dd.MM.yyyy");
		Assertions.assertEquals("04.10.1995", dateString);
	}

	@Test
	void currentDateOrNextWorkDate(){
		Path path = PathUtil.getPathFromClasspath("holidays_in_every_year_tr.json");
		try {
			InputStream inputStream = Files.newInputStream(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
