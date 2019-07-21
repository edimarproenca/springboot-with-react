package com.springreact.springReact.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "engine_type")
public class EngineType implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "engine_type_sequece")
    @SequenceGenerator(name = "engine_type_sequece", sequenceName = "ENGINE_TYPE_SEQ")
	private Long id;
	private String name;
	
	public EngineType() {
	}
	
	public EngineType(String model) {
		super();
		this.name = model;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String model) {
		this.name = model;
	}

}
