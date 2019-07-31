package movie_app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import movie_app.model.Director;
import movie_app.model.Movie;
import movie_app.service.DirectorService;
import movie_app.service.MovieService;
import movie_app.support.DirectorDTOToDirector;
import movie_app.support.DirectorToDirectorDTO;
import movie_app.support.MovieToMovieDTO;
import movie_app.web.dto.DirectorDTO;
import movie_app.web.dto.MovieDTO;



@Controller
@RequestMapping(value="/api/directors")
public class ApiDirectorController {
	
	@Autowired
	private DirectorService directorService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private DirectorDTOToDirector toDirector;
	
	@Autowired
	private DirectorToDirectorDTO toDto;
	
	@Autowired
	private MovieToMovieDTO toMovieDTO;
	

	@RequestMapping(method=RequestMethod.GET)
	ResponseEntity<List<DirectorDTO>> getOne(
			@RequestParam(defaultValue="0") int page){
		
		List<Director> directors;
		Page<Director> directorsPage = directorService.findAll(page);
		directors = directorsPage.getContent();
		
		if(directors == null || directors.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(directors), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	ResponseEntity<DirectorDTO> getDirector(@PathVariable Long id){
		Director director = directorService.findOne(id);
		if(director==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(
				toDto.convert(director),
				HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	ResponseEntity<Director> delete(@PathVariable Long id){
		directorService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.POST,
					consumes="application/json")
	public ResponseEntity<DirectorDTO> add(
			@Validated @RequestBody DirectorDTO newDto){
		Director director = toDirector.convert(newDto);
		
		Director savedDirector = directorService.save(director);
		
		return new ResponseEntity<>(
				toDto.convert(savedDirector), 
				HttpStatus.CREATED);
	}
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<DirectorDTO> edit(
			@RequestBody DirectorDTO dto,
			@PathVariable Long id){
		
		if(id!=dto.getId()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Director persisted = directorService.save(toDirector.convert(dto));
		
		return new ResponseEntity<>(
				toDto.convert(persisted),
				HttpStatus.OK);
	}
	
	@RequestMapping(value= "{id}/movies", method=RequestMethod.GET)
	ResponseEntity<List<MovieDTO>> getDirectorsMovies(
			@PathVariable Long id, @RequestParam(defaultValue="0") int pageNum){
		
		Page<Movie> moviespage = movieService.findByDirectorId(id, pageNum);
		
		if(moviespage == null || moviespage.getContent().isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(moviespage.getTotalPages()) );
		
		return new ResponseEntity<>(
				toMovieDTO.convert(moviespage.getContent()),
				headers,
				HttpStatus.OK);
	}
	
}
