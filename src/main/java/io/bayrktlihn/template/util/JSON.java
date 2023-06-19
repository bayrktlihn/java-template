package io.bayrktlihn.template.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSON {

	public static <T> String stringify(T entity) {

		try {
			JsonMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return objectMapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}

	public static <T> T parse(String json, Class<T> clazz) {
		try {
			JsonMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return objectMapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> parseList(String json, Class<T> clazz) {
		try {
			JsonMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return objectMapper.readerForListOf(clazz).readValue(json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
