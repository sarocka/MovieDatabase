package movie_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import movie_app.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

	
}
