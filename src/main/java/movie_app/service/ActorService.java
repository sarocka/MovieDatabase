package movie_app.service;

import java.util.List;



import movie_app.model.Actor;

public interface ActorService {
	Actor findOne(long id);
	List<Actor>findAll();
	Actor save (Actor actor);
	List<Actor> save (List<Actor>actors);
	Actor delete(Long id);
	
}
