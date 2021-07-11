package one.digitalinnovation.personalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import one.digitalinnovation.personalapi.MessageResponseDto;
import one.digitalinnovation.personalapi.Person;
import one.digitalinnovation.personalapi.repository.PersonalRepository;

@Service

public class PersonService {
	
	private PersonalRepository personalRepository;
	
	@Autowired
	public PersonService (PersonalRepository personalRepository) {
		
		this.personalRepository = personalRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
public String createPerson( Person person) {
		
		Person personsave = personalRepository.save(person);
		return MessageResponseDto.builder("create person with ID"+ personsave);
	}
		

}
