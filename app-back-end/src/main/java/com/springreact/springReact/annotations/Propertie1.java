package com.springreact.springReact.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.springreact.springReact.enums.FormatTypeEnum;

@Retention(RetentionPolicy.RUNTIME)
public @interface Propertie1 {

	public FormatTypeEnum outputFormat();

}
