package movie_app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import movie_app.model.Director;

public interface DirectorService {

	Director findOne(Long id);
	List<Director> findAll();
	Page<Director> findAll(int page);
	Director save(Director director);
	
	void delete(Long id); 
	
}
