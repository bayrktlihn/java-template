package io.bayrktlihn.template.util;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class NumberFormatTest {

	@Test
	void test() {
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("tr", "TR"));
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setRoundingMode(RoundingMode.UP);
		
		System.out.println(numberFormat.format(123124213213.4163213));
	}
}
