package movie_app.service_impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie_app.model.Actor;
import movie_app.model.Movie;
import movie_app.repository.ActorRepository;
import movie_app.service.ActorService;

@Service
@Transactional
public class JpaActorService implements ActorService {
	
	@Autowired
	ActorRepository actorRepository;
	@Override
	public Actor findOne(long id) {
		// TODO Auto-generated method stub
		return actorRepository.findOne(id);
	}

	@Override
	public List<Actor> findAll() {
		// TODO Auto-generated method stub
		return actorRepository.findAll();
	}

	

	@Override
	public List<Actor> save(List<Actor> actors) {
		
		return actorRepository.save(actors);
	}

	@Override
	public Actor save(Actor actor) {
		
		return actorRepository.save(actor);
		 
		}

	@Override
	public Actor delete(Long id) {
		Actor a = actorRepository.findOne(id);
		if(a== null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant entity");
		}
		actorRepository.delete(a);
		return a;
	}

	
	
}
