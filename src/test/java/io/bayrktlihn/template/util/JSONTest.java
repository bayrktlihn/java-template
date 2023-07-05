package io.bayrktlihn.template.util;

import java.nio.file.Path;
import java.util.List;

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
		person.setBirthDate(Dates.createDate(1995, 10, 4));

		String json = JSON.stringify(person);
		System.out.println(json);
	}

	@Test
	void dayMonthTest(){
		Path path = PathUtil.getPathFromClasspath("holidays_in_every_year_tr.json");
		String fileContent = FileUtil.readAll(path.toFile());

		List<DayMonth> dayMonths = JSON.parseList(fileContent, DayMonth.class);
		for (DayMonth dayMonth : dayMonths) {
			System.out.println(dayMonth);
		}
	}

}
