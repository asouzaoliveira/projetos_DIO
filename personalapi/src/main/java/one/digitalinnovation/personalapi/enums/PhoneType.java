package one.digitalinnovation.personalapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum PhoneType {

	NOME(),
	MOBILE(),
	COMMERCIAL();
	
	private static final String description = "descricao";
}
