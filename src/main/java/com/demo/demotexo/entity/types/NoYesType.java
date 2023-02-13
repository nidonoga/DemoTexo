package com.demo.demotexo.entity.types;

public enum NoYesType {
	
	NO("Não"), YES("Sim");

	private String descricao;

	private NoYesType(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static NoYesType getType(String value) {
		for (NoYesType type : NoYesType.values())
			if ( type.name().equalsIgnoreCase(value))
				return type;

		return null;
	}

}
