package movie_app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import movie_app.model.Actor;
import movie_app.model.Movie;

public interface MovieService  {

	Movie findOne(Long id);
	
	
	Page<Movie> findAll( int pageNum);
	
	
	Page<Movie> search(
			@Param("titleOrDirector") String titleOrDirector, 
			
			 int pageNum);
	
	
	Movie save(Movie movie);
	
	
	Movie delete(Long id);
	
	
	Page<Movie> findByDirectorId(Long id, int pageNum);
	
	
	
}
