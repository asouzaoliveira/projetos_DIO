package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entidade.Cerveja;

public interface BeerRepository extends JpaRepository<Cerveja, Long> {
	
	
	Optional<Cerveja> findByName(String name);

}
