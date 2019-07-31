package movie_app.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
@Entity
@Table
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column
	private Double rating;
	
	@Column
	private Integer duration;
	
	@Column
	private String description;
	@Column
	private String genre;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Director director;
	
	@OneToMany(mappedBy="movie",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE})
    @JoinTable(
        name = "movie_actor", 
        joinColumns = { @JoinColumn(name = "movie_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
   private Set<Actor> actors = new HashSet<>();
	 
	
	

	public Movie(Long id, String title, Double rating, Integer duration, String description, Director director,
			List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.rating = rating;
		this.duration = duration;
		this.description = description;
		this.director = director;
		this.comments = comments;
	}
	
	public Movie(String title, Double rating, Integer duration, String description, Director director, String genre) {
		super();
		this.title = title;
		this.rating = rating;
		this.duration = duration;
		this.description = description;
		this.director = director;
		this.genre=genre;
	}

	public Movie() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
		if(!director.getFilmography().contains(this)){
			director.getFilmography().add(this);
		}
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void addComment(Comment comment) {
		if(comment.getMovie() != this) {
			comment.setMovie(this);
		}
		comments.add(comment);
	}

	

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public void addActor(Actor actor) {
        actors.add(actor);
        actor.getMovies().add(this);
    }
 
	public void removeActor(Actor actor) {
		actors.remove(actor);
		actor.getMovies().remove(this);
	}
   
	
	
}
