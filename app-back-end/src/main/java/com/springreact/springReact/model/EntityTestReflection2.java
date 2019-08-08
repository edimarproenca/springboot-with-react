package com.springreact.springReact.model;

import java.time.LocalDateTime;

import com.springreact.springReact.annotations.Propertie1;
import com.springreact.springReact.enums.FormatTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityTestReflection2 {

	@Propertie1(outputFormat = FormatTypeEnum.dd_MM_yyyy)
	public LocalDateTime data;
	
	
	
}
