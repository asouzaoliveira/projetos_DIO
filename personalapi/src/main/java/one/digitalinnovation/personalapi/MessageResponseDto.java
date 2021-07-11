package one.digitalinnovation.personalapi;

import lombok.Builder;
import lombok.Data;

@Data
@Builder



public class MessageResponseDto {

	
	private static String message;

	public static String builder(String string) {
		
		return message;
	}

}
