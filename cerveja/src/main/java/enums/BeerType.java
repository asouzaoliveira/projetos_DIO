package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum BeerType {
	
	LAGER("lAGER"),
	MALZEBIER("Malzebier"),
	WITBIER("Witbier"),
	WEIS("Weis"),
	ALE("Ale"),
	IPA("Ipa"),
	STOUT("Stout");
	
	private final String description; 
	
	private BeerType(String description) {
		this.description = description;
	}

	

}
