package movie_app.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Actor;
import movie_app.service.ActorService;
import movie_app.web.dto.ActorDTO;




@Component

public class ActorDTOToActor implements Converter<ActorDTO, Actor> {
	
	

	public Actor convert(ActorDTO dto) {
		Actor actor = new Actor();
			actor.setId(dto.getId());
			actor.setName(dto.getName());
			
			return actor;
		
		
	}
	
	public List<Actor> convert(List<ActorDTO> listDTO){
		List<Actor> ret = new ArrayList<>();
		
		for(ActorDTO dto: listDTO){
			ret.add(convert(dto));
		}
		
		return ret;
	}
}
