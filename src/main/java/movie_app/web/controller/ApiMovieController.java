package movie_app.web.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
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

import movie_app.service.MovieService;
import movie_app.support.ActorToActorDTO;
import movie_app.support.MovieDTOToMovie;
import movie_app.support.MovieToMovieDTO;
import movie_app.web.dto.ActorDTO;
import movie_app.web.dto.MovieDTO;

@RestController
@RequestMapping(value="/api/movies")
public class ApiMovieController {
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private MovieToMovieDTO toDTO;
	
	@Autowired
	private MovieDTOToMovie toMovie;
	
	@Autowired
	private ActorToActorDTO toActorDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<MovieDTO>> getAll(
			@RequestParam(required=false) String titleOrDirector,
			
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Movie> moviespage = null;
		
		if(titleOrDirector != null ) {
			moviespage = movieService.search(titleOrDirector,pageNum);
		}
		else {
			moviespage = movieService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(moviespage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toDTO.convert(moviespage.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<MovieDTO> getOne(@PathVariable Long id){
		Movie source = movieService.findOne(id);
		if(source==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(source),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<MovieDTO> delete(@PathVariable Long id){
		Movie deleted = movieService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toDTO.convert(deleted),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<MovieDTO> add(
			@Validated 
			@RequestBody MovieDTO newDto){
		
		Movie saved= movieService.save(
				toMovie.convert(newDto));
		
		return new ResponseEntity<>(
				toDTO.convert(saved), 
				HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<MovieDTO> edit(
			@Validated @RequestBody MovieDTO dto,
			@PathVariable Long id){
		
		if(!id.equals(dto.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Movie persisted = movieService.save(
				toMovie.convert(dto));
		
		return new ResponseEntity<>(
				toDTO.convert(persisted),
				HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value= "{id}/actors", method=RequestMethod.GET)
	ResponseEntity<Set<ActorDTO>> getMoviesActors(
			@PathVariable Long id){
		
		Movie m = movieService.findOne(id);
		Set<Actor> actors= m.getActors();
		
		
		
		if(actors == null || actors.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		
		return new ResponseEntity<>(
				toActorDTO.convert(actors),HttpStatus.OK);
	}
	
}
