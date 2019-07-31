package movie_app.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Director;
import movie_app.web.dto.DirectorDTO;

@Component
public class DirectorToDirectorDTO implements Converter<Director, DirectorDTO> {

	@Override
	public DirectorDTO convert(Director director) {
		DirectorDTO dto = new DirectorDTO();
		
		dto.setId(director.getId());
		dto.setBiography(director.getBiography());
		dto.setName(director.getName());
		
		return dto;
	}
	
	public List<DirectorDTO> convert(List<Director> directors){
		List<DirectorDTO> ret = new ArrayList<>();
		
		for(Director director : directors){
			ret.add(convert(director));
		}
		
		return ret;
	}
}
