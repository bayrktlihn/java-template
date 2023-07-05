package io.bayrktlihn.template.util;

import io.bayrktlihn.template.util.date.Dates;
import org.junit.jupiter.api.Test;

import io.bayrktlihn.template.entity.Person;
import io.bayrktlihn.template.enums.Gender;

class XMLTest {

	@Test
	void test() {
		Person person = new Person();
		person.setGender(Gender.MAN);
		person.setFirstName("alihan");
		person.setLastName("bayraktar");
		person.setBirthDate(Dates.createDate(1995, 10, 4));

		String xml = XML.stringify(person);
		System.out.println(xml);
	}

}
