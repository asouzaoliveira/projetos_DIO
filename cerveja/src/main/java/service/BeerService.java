package service;

import lombok.AllArgsConstructor;
import mapper.BeerMapper;
import repository.BeerRepository;
import entidade.Cerveja;
import execption.BeerAlreadyRegisteredException;
import execption.BeerNotFoundException;
import execption.BeerStockExceededException;
import entidade.Cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class BeerService {

	 private final BeerRepository beerRepository;
	    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

	    public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
	        verifyIfIsAlreadyRegistered(beerDTO.getName());
	        Cerveja beer = beerMapper.toModel(beerDTO);
	        Cerveja savedBeer = beerRepository.save(beer);
	        return beerMapper.toDTO(savedBeer);
	    }

	    public BeerDTO findByName(String name) throws BeerNotFoundException {
	        Beer foundBeer = beerRepository.findByName(name)
	                .orElseThrow(() -> new BeerNotFoundException(name));
	        return beerMapper.toDTO(foundBeer);
	    }

	    public List<BeerDTO> listAll() {
	        return beerRepository.findAll()
	                .stream()
	                .map(beerMapper::toDTO)
	                .collect(Collectors.toList());
	    }

	    public void deleteById(Long id) throws BeerNotFoundException {
	        verifyIfExists(id);
	        beerRepository.deleteById(id);
	    }

	    private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
	        Optional<Cerveja> optSavedBeer = beerRepository.findByName(name);
	        if (optSavedBeer.isPresent()) {
	            throw new BeerAlreadyRegisteredException(name);
	        }
	    }

	    private Cerveja verifyIfExists(Long id) throws BeerNotFoundException {
	        return beerRepository.findById(id)
	                .orElseThrow(() -> new BeerNotFoundException(id));
	    }

	    public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
	    	Cerveja beerToIncrementStock = verifyIfExists(id);
	        int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();
	        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
	            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
	            Cerveja incrementedBeerStock = beerRepository.save(beerToIncrementStock);
	            return beerMapper.toDTO(incrementedBeerStock);
	        }
	        throw new BeerStockExceededException(id, quantityToIncrement);
	    }}
	
}
