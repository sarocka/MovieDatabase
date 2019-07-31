package movie_app.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Director;
import movie_app.model.Movie;
import movie_app.service.DirectorService;
import movie_app.service.MovieService;
import movie_app.web.dto.MovieDTO;

@Component
public class MovieDTOToMovie implements Converter<MovieDTO, Movie>{

	@Autowired
	private MovieService movieService;
	
	@Autowired
	private DirectorService directorService; 
	
	@Override
	public Movie convert(MovieDTO dto) {
		
		Director director = directorService.findOne(dto.getDirectorId());
	
		if(director!=null ) { 
			
			Movie movie = null;
			
			if(dto.getId() != null) {
				movie = movieService.findOne(dto.getId());
			}
			else {
				movie = new Movie();
			}
			
			movie.setId(dto.getId());
			movie.setDescription(dto.getDescription());
			movie.setDuration(dto.getDuration());
			movie.setRating(dto.getRating());
			movie.setDirector(director);
			movie.setTitle(dto.getTitle());
			movie.setGenre(dto.getGenre());
		
			
			return movie;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	public List<Movie> convert(List<MovieDTO> listDto){
		List<Movie> ret = new ArrayList<>();
		
		for(MovieDTO dto : listDto){
			ret.add(convert(dto));
		}
		
		return ret;
	}
}
