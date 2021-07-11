package one.digitalinnovation.personalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinnovation.personalapi.Person;

public interface PersonalRepository extends JpaRepository<Person, Long>{
	
	

}
