package com.springreact.springReact.model;

import java.time.LocalDateTime;

import com.springreact.springReact.annotations.Propertie1;
import com.springreact.springReact.annotations.Propertie2;
import com.springreact.springReact.annotations.Propertie3;
import com.springreact.springReact.enums.FormatTypeEnum;

public class EntityTestReflection {

	@Propertie1(outputFormat = FormatTypeEnum.yyyyMMdd)
	private LocalDateTime propertie1;
	
	@Propertie2()
	private int propertie2;
	
	@Propertie3()
	private boolean propertie3;
	
	public EntityTestReflection() {
	}

	public EntityTestReflection(LocalDateTime propertie1, int propertie2, boolean propertie3) {
		super();
		this.propertie1 = propertie1;
		this.propertie2 = propertie2;
		this.propertie3 = propertie3;
	}

	public LocalDateTime getPropertie1() {
		return propertie1;
	}

	public void setPropertie1(LocalDateTime propertie1) {
		this.propertie1 = propertie1;
	}

	public int getPropertie2() {
		return propertie2;
	}

	public void setPropertie2(int propertie2) {
		this.propertie2 = propertie2;
	}

	public boolean isPropertie3() {
		return propertie3;
	}

	public void setPropertie3(boolean propertie3) {
		this.propertie3 = propertie3;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
