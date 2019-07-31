package movie_app.support;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Actor;
import movie_app.model.Movie;
import movie_app.web.dto.ActorDTO;
import movie_app.web.dto.MovieDTO;

@Component
public class MovieToMovieDTO implements Converter<Movie, MovieDTO>{

	@Override
	public MovieDTO convert(Movie movie) {
		
		MovieDTO retValue = new MovieDTO();
		retValue.setId(movie.getId());
		retValue.setDescription(movie.getDescription());
		retValue.setDuration(movie.getDuration());
		retValue.setRating(movie.getRating());
		retValue.setTitle(movie.getTitle());
		retValue.setDirectorId(movie.getDirector().getId());
		retValue.setDirectorName(movie.getDirector().getName());
		retValue.setGenre(movie.getGenre());
		return retValue;
	}

	public List<MovieDTO> convert(List<Movie>list){
		List<MovieDTO> ret = new ArrayList<>();
		
		for(Movie source : list){
			ret.add(convert(source));
		}
		
		return ret;
	}

	public Set<MovieDTO> convert(Set<Movie> movies) {
		Set<MovieDTO> retVal = new HashSet<>();
		for (Movie m : movies) {
			retVal.add(convert(m));
		}
		return retVal;
	}
}
