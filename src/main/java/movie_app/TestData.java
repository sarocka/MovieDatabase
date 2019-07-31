package movie_app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import movie_app.model.Actor;
import movie_app.model.Comment;
import movie_app.model.Director;
import movie_app.model.Movie;
import movie_app.service.ActorService;
import movie_app.service.CommentService;
import movie_app.service.DirectorService;
import movie_app.service.MovieService;



@Component
public class TestData {

	
	@Autowired
	private DirectorService directorService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ActorService actorService;
	@PostConstruct
	public void init() {
		
		Director d1 = new Director("Alejandro G. Inarritu","August 15, 1963 in Mexico City, Distrito Federal, Mexico");
		Director d2 = new Director("Pedro Almodóvar","September 25, 1949 in Calzada de Calatrava, Ciudad Real, Castilla-La Mancha, Spain");
		Director d3 = new Director("Paolo Sorrentino","Paolo Sorrentino was born on May 31, 1970 in Naples, Campania, Italy. He is a writer and director, known for Youth (2015), The Great Beauty (2013) and This must be the place (2011).");
	Director d4 = new Director("Dennis Gansel","Born in 1973 in Hannover, Germany, Dennis Gansel studied at Munich Film School HFF. Remarkably his first feature length movie Das Phantom (2000) was shot while he was still studying.  Known for: The Wave and Fall");
		directorService.save(d1);
		directorService.save(d3);
		directorService.save(d2);
		directorService.save(d4);
		
		Movie m1= new Movie("The Great Beauty", 7.8,141,"Jep Gambardella has seduced his way through the lavish nightlife of Rome for decades, but after his 65th birthday and a shock from the past, Jep looks past the nightclubs and parties to find a timeless landscape of absurd, exquisite beauty.", d3, "drama");
		Movie m2= new Movie("Talk to Her", 8.0,152,"Two men share an odd friendship while they care for two women who are both in deep comas.",d2, "drama");
		Movie m3= new Movie("Babel",7.5,143, "Tragedy strikes a married couple on vacation in the Moroccan desert, touching off an interlocking story involving four different families.",d1, "drama");
		Movie m4= new Movie("The wave",7.6,107, "A high school teacher's experiment to demonstrate to his students what life is like under a dictatorship spins horribly out of control when he forms a social unit with a life of its own..",d4, "drama");
		Movie m5= new Movie("Youth",7.3, 124, "A retired orchestra conductor is on vacation with his daughter and his film director best friend in the Alps when he receives an invitation from Queen Elizabeth II to perform for Prince Philip's birthday.",d3, "comedy, drama");
		
		Movie m6= new Movie("21 gram",7.7, 124, "A freak accident brings together a critically ill mathematician, a grieving mother, and a born-again ex-con.",d1, "crime, drama");
		Movie m7= new Movie("Volver", 7.6,120,"After her death, a mother returns to her home town in order to fix the situations she couldn't resolve during her life.",d2, "comedy, drama");
		movieService.save(m1);
		movieService.save(m2);
		movieService.save(m3);
		movieService.save(m4);
		movieService.save(m5);
		movieService.save(m6);
		movieService.save(m7);
		Comment c1= new Comment("trully inspiring story", m1);
		Comment c2= new Comment("Very beautiful and compelling, but perhaps not for every one.", m2);
		commentService.save(c1);
		commentService.save(c2);
		
		Actor a1 = new Actor("Brad Pitt");
		Actor a2 = new Actor("Toni Servillo");
		Actor a3 = new Actor("Rosario Flores");
		Actor a4 = new Actor("Penelope Cruz");
		Actor a5 = new Actor("Cate Blanchet");
		Actor a6 = new Actor("Sean Penn");
		
		actorService.save(a1);
		actorService.save(a2);
		actorService.save(a3);
		actorService.save(a4);
		actorService.save(a5);
		actorService.save(a6);
		
		a1.addMovie(m3);
		a2.addMovie(m1);
		a3.addMovie(m2);
		a4.addMovie(m7);
		a5.addMovie(m3);
		a6.addMovie(m6);
		
		actorService.save(a1);
		actorService.save(a2);
		actorService.save(a3);
		actorService.save(a4);
		actorService.save(a5);
		actorService.save(a6);
		
		m1.addActor(a2);
		m2.addActor(a3);
		m3.addActor(a1);
		m6.addActor(a6);
		m7.addActor(a4);
		m3.addActor(a5);
		movieService.save(m1);
		movieService.save(m2);
		
		movieService.save(m6);
		movieService.save(m7);
		movieService.save(m3);
		
		
		
	}
}
