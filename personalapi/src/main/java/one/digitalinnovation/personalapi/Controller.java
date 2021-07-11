package one.digitalinnovation.personalapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import one.digitalinnovation.personalapi.repository.PersonalRepository;
import one.digitalinnovation.personalapi.service.PersonService;
@Data

@RestController
@RequestMapping("/api/v1/people")
public class Controller {
	
	private PersonService personService;
	
	@Autowired
	public Controller(PersonService personService) {
		this.personService = personService;
		
	}
	
	@PostMapping
	public String MessageResponseDto(@RequestBody Person person) {
		
		return personService.createPerson(person);
		
		
	}
		

}
