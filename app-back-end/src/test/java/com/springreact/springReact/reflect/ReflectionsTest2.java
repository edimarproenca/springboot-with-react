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
import org.junit.Before;
import org.junit.Test;

import com.springreact.springReact.annotations.Propertie1;
import com.springreact.springReact.dto.FormattedValueResult;
import com.springreact.springReact.model.EntityTestReflection;
import com.springreact.springReact.model.EntityTestReflection2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionsTest2 {

	public List<Object> list = new ArrayList<Object>();

	@Before
	public void entityFactory2() {
		LocalDateTime ldt = LocalDateTime.now();
		for (int i = 0; i < 300; i++) {
			list.add(new EntityTestReflection(ldt.plusDays(i), i, true));
			list.add(new EntityTestReflection2(ldt.plusDays(i)));
		}
	}

	@Test
	public void runTest() {
		List<FormattedValueResult> fvrList = new ArrayList<FormattedValueResult>();

		list.stream().forEach(etrs -> {
			try {
				fvrList.add(this.getFormatandLocalDate(etrs));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
				
		fvrList.stream().forEach(fvr -> log.info(fvr.toString()));

		assertTrue(fvrList.parallelStream().allMatch(FormattedValueResult::passTest));
	}

	private FormattedValueResult getFormatandLocalDate(Object obj) throws Exception {
		String formattedValue = "";
		String annotationValue = "";
		LocalDateTime fieldValue = null;
		DateTimeFormatter dtf = null;
		Class<? extends Object> objClass = obj.getClass();
		for (Field field : objClass.getDeclaredFields()) {
			if (this.hasPropertie1Annotation(field)) {
				Propertie1 annotation = field.getAnnotation(Propertie1.class);
				Optional<Object> value = null;
				annotationValue = annotation.outputFormat().getValor();
				if (StringUtils.isNotBlank(annotationValue)) {
					try {
						field.setAccessible(true);
						value = Optional.ofNullable(field.get(obj));
						field.setAccessible(false);
					} catch (Exception e) {
						throw new Exception(e.getCause());
					}
					if (value.isPresent()) {
						dtf = DateTimeFormatter.ofPattern(annotationValue);
						LocalDateTime date = (LocalDateTime) value.get();
						fieldValue = (LocalDateTime) value.get();
						formattedValue = dtf.format(fieldValue);
					} else {
						throw new Exception("Not be null");
					}
				} else {
					throw new Exception("outputFormat is blank");
				}
			}
		}
		return new FormattedValueResult(annotationValue, fieldValue, formattedValue);
	}

	private Boolean hasPropertie1Annotation(Field field) {
		Optional<Propertie1> opt = Optional.ofNullable(field.getAnnotation(Propertie1.class));
		return opt.isPresent();
	}

}
