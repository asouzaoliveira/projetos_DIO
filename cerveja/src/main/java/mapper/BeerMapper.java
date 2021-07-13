package mapper;



import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dto.BeerDTO;
import entidade.Cerveja;

@Mapper

public interface BeerMapper {
	
	BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Cerveja toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Cerveja beer);

}
