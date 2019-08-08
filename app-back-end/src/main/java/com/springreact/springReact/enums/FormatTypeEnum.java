package com.springreact.springReact.enums;

public enum FormatTypeEnum {

	  yyyyMMdd("yyyyMMdd"), dd_MM_yyyy("dd-MM-yyyy");
    
    private final String valor;
    FormatTypeEnum(String valorOpcao){
        valor = valorOpcao;
    }
    public String getValor(){
        return valor;
    }
	
}
