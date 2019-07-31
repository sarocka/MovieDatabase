package movie_app.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Actor;
import movie_app.web.dto.ActorDTO;


@Component
public class ActorToActorDTO implements Converter<Actor, ActorDTO> {

	@Override
	public ActorDTO convert(Actor actor) {
		ActorDTO dto = new ActorDTO();
		
		dto.setId(actor.getId());
		dto.setName(actor.getName());
		
		return dto;
	}
	
	public List<ActorDTO> convert(List<Actor>lista){
		List<ActorDTO> ret = new ArrayList<>();
		
		for(Actor actor : lista){
			ret.add(convert(actor));
		}
		
		return ret;
	}

	public Set<ActorDTO> convert(Set<Actor> actors) {
		Set<ActorDTO> retVal = new HashSet<>();
		for (Actor actor : actors) {
			retVal.add(convert(actor));
		}
		return retVal;
	}
	
}
