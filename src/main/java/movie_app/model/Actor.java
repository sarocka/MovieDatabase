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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Actor {

	@Id
	@GeneratedValue
	@Column
	private Long id; 
	@Column
	private String name;
	
	 @ManyToMany(mappedBy = "actors")
	    private Set<Movie> movies = new HashSet<>();
	 
	public Actor(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Actor() {
		
	}

	

	public Actor(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void addMovie(Movie movie) {
        movies.add(movie);
        movie.getActors().add(this);
    }
	public void removeMovie(Movie movie) {
		movies.remove(movie);
		movie.getActors().remove(this);
	}
	
}
