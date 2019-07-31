package movie_app.web.controller;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import movie_app.model.Actor;
import movie_app.model.Movie;
import movie_app.service.ActorService;

import movie_app.support.ActorDTOToActor;
import movie_app.support.ActorToActorDTO;

import movie_app.support.MovieToMovieDTO;
import movie_app.web.dto.ActorDTO;
import movie_app.web.dto.MovieDTO;

@RestController
@RequestMapping(value="/api/actors")

public class ApiActorController {

	@Autowired
	private ActorService actorService;
	
	
	
	@Autowired
	private ActorToActorDTO toDTO;
	
	@Autowired
	private ActorDTOToActor toActor;
	
	@Autowired
	private MovieToMovieDTO toMovieDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<ActorDTO>> getAll(
			
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		List<Actor> actors = null;
		
		
		actors = actorService.findAll();
		
		
		
		return new ResponseEntity<>(
				toDTO.convert(actors),
				
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<ActorDTO> getOne(@PathVariable Long id){
		Actor source = actorService.findOne(id);
		if(source==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(source),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<ActorDTO> delete(@PathVariable Long id){
		Actor deleted = actorService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<ActorDTO> add(
			@Validated 
			@RequestBody ActorDTO newDto){
		
		Actor saved= actorService.save(
				toActor.convert(newDto));
		
		return new ResponseEntity<>(
				toDTO.convert(saved), 
				HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<ActorDTO> edit(
			@Validated @RequestBody ActorDTO dto,
			@PathVariable Long id){
		
		if(!id.equals(dto.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Actor persisted = actorService.save(
				toActor.convert(dto));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value= "{id}/movies", method=RequestMethod.GET)
	ResponseEntity<Set<MovieDTO>> getActorsMovies(
			@PathVariable Long id){
		
		Actor a = actorService.findOne(id);
		Set<Movie> movies= a.getMovies();
		
		
		
		if(movies == null || movies.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		
		return new ResponseEntity<>(
				toMovieDTO.convert(movies),HttpStatus.OK);
	}
	
	
}
