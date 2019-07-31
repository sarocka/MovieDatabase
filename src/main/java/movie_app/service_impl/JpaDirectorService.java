package movie_app.service_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import movie_app.model.Director;
import movie_app.repository.DirectorRepository;
import movie_app.service.DirectorService;

@Service
public class JpaDirectorService implements DirectorService {
	@Autowired
	private DirectorRepository directorRepository;

	@Override
	public Director findOne(Long id) {
		return directorRepository.findOne(id);
	}

	@Override
	public List<Director> findAll() {
		
		return directorRepository.findAll();
	}

	@Override
	public Director save(Director director) {
		return directorRepository.save(director);
	}

	@Override
	public void delete(Long id) {
		directorRepository.delete(id);
	}

	@Override
	public Page<Director> findAll(int page) {
		return directorRepository.findAll(new PageRequest(page, 5));
	}

}
