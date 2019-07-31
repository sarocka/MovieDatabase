package movie_app.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Director;
import movie_app.service.DirectorService;
import movie_app.web.dto.DirectorDTO;

@Component
public class DirectorDTOToDirector implements Converter<DirectorDTO, Director> {
	@Autowired
	private DirectorService directorService;

	@Override
	public Director convert(DirectorDTO dto) {
		Director director = null;
		if(dto.getId()==null){
			director = new Director();
		}else{
			director = directorService.findOne(dto.getId());
			if(director == null){
				throw new IllegalStateException("Editing non-existant entity");
			}
		}
		director.setBiography(dto.getBiography());
		director.setId(dto.getId());
		director.setName(dto.getName());;
		
		return director;
	}
	
	public List<Director> convert(List<DirectorDTO> dtoList){
		List<Director> ret = new ArrayList<>();
		
		for(DirectorDTO dto: dtoList){
			ret.add(convert(dto));
		}
		
		return ret;
	}

}
