package io.bayrktlihn.template.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import io.bayrktlihn.template.util.date.model.DayMonth;
import io.bayrktlihn.template.util.date.Dates;
import org.junit.jupiter.api.Test;

import io.bayrktlihn.template.entity.Person;

class JSONTest {

	@Test
	void test() {
		Person person = new Person();
		person.setFirstName("alihan");
		person.setLastName("bayraktar");
		person.setBirthDate(Dates.create(1995, 10, 4));

		String json = JSON.stringify(person);
		System.out.println(json);
	}

	@Test
	void dayMonthTest(){
		String fileContent = ClassPathUtil.getFileContent("holidays_in_every_year_tr.json");

		List<DayMonth> dayMonths = JSON.parseList(fileContent, DayMonth.class);
		for (DayMonth dayMonth : dayMonths) {
			System.out.println(dayMonth);
		}
	}

}
