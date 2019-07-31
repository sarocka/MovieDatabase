package movie_app.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import movie_app.model.Actor;
import movie_app.model.Movie;
import movie_app.repository.MovieRepository;
import movie_app.service.MovieService;



@Service
public class JpaMovieService implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public Movie findOne(Long id) {
		return movieRepository.findOne(id);
	}

	@Override
	public Page<Movie> findAll(int pageNum) {
		return movieRepository.findAll(new PageRequest(pageNum, 3));
	}

	@Override
	public Page<Movie> search(String titleOrDirector,int pageNum){
		
		if(titleOrDirector != null) {
			titleOrDirector = '%' + titleOrDirector + '%';
		}
		
		
		return movieRepository.search(titleOrDirector,new PageRequest(pageNum, 3));
	}
	
	@Override
	public Movie save(Movie movie) {
		
		return movieRepository.save(movie);
	}

	@Override
	public Movie delete(Long id) {
		Movie movie = movieRepository.findOne(id);
		if(movie == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant entity");
		}
		movieRepository.delete(movie);
		return movie;
	}

	@Override
	public Page<Movie> findByDirectorId(Long id, int pageNum) {
		return movieRepository.findByDirectorId(id, new PageRequest(pageNum, 3));
	}

	

				
	
}
