package movie_app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import movie_app.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

	
}
