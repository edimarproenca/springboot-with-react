package com.springreact.springReact.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class FormattedValueResult {
	
	private String outputFormat;
	private LocalDateTime originalValue;
	private String formattedValue;
	
	
	public Boolean passTest() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.outputFormat);
		return StringUtils.equals(dtf.format(this.originalValue), this.formattedValue);
	}
	
	@Override
	public String toString() {
		return "AnnotationParameter -> " + this.outputFormat + " | formattedValue -> " + this.formattedValue;
	}
	
}
