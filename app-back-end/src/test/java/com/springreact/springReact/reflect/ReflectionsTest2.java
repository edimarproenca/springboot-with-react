package com.springreact.springReact.reflect;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.springreact.springReact.annotations.Propertie1;
import com.springreact.springReact.model.EntityTestReflection;
import com.springreact.springReact.model.EntityTestReflection2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionsTest2 {

	public List<EntityTestReflection> list = new ArrayList<EntityTestReflection>();

//	@Before
	public void entityFactory2() {
		for (int i = 0; i < 1000; i++) {
			list.add(new EntityTestReflection(LocalDateTime.now(), i, true));
		}
	}

	@Test
	public void runTest() {
		Map<String, Field> methodMap = new HashMap<String, Field>();
		EntityTestReflection etr = new EntityTestReflection(LocalDateTime.now(), 1, true);
		EntityTestReflection etr2 = new EntityTestReflection();
		EntityTestReflection2 etr3 = new EntityTestReflection2(LocalDateTime.now());
		EntityTestReflection2 etr4 = new EntityTestReflection2();

		try {
			log.info(this.getFormatandLocalDate(etr));
			log.info(this.getFormatandLocalDate(etr2));
			log.info(this.getFormatandLocalDate(etr3));
			log.info(this.getFormatandLocalDate(etr4));
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(true);
	}

	private String getFormatandLocalDate(Object obj) throws Exception {
		String result = "";
		String annotationValue = "";
		LocalDateTime fieldValue = null;
		DateTimeFormatter dtf = null;
		Class<? extends Object> objClass = obj.getClass();
		for (Field field : objClass.getDeclaredFields()) {
			if (field.getAnnotation(Propertie1.class) != null) {
				Propertie1 annotation = field.getAnnotation(Propertie1.class);
				Optional<Object> value = null;
				annotationValue = annotation.outputFormat();
				if (StringUtils.isNotBlank(annotationValue)) {
					try {
						field.setAccessible(true);
						value = Optional.ofNullable(field.get(obj));
						field.setAccessible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (value.isPresent()) {
						dtf = DateTimeFormatter.ofPattern(annotationValue);
						LocalDateTime date = (LocalDateTime) value.get();
						fieldValue = (LocalDateTime) value.get();
						result = dtf.format(fieldValue);
					} else {
						result = "NullObject";
					}
				}else {
					result = "outputFormat is blank";
				}
			}
		}
		return result;
	}

}
