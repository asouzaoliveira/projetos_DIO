package execption;

public class BeerAlreadyRegisteredException extends Exception {
	
	public BeerAlreadyRegisteredException(String beerName) {
        super(String.format("Beer with name %s already registered in the system.", beerName));
    }

}
