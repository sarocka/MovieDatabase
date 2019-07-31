package movie_app.repository;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import movie_app.model.Movie;



@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	Page<Movie> findByDirectorId(Long id, Pageable page);
	
	
	@Query("SELECT m FROM Movie m WHERE "
			+ "(:titleOrDirector IS NULL or m.title like :titleOrDirector) OR "
			+ "(:titleOrDirector IS NULL or m.director.name like :titleOrDirector) "
			)
	Page<Movie> search(
			@Param("titleOrDirector") String titleOrDirector, 
			
			Pageable pageRequest);
}
