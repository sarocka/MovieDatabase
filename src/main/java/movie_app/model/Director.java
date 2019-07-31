package movie_app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Director {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String biography;
	
	
	@OneToMany(mappedBy="director",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Movie> filmography = new ArrayList<>();
	
		
	
	
	public Director(Long id, String name, String biography, List<Movie> filmography) {
		super();
		this.id = id;
		this.name = name;
		this.biography = biography;
		this.filmography = filmography;
	}




	public Director(String name, String biography) {
		super();
		this.name = name;
		this.biography = biography;
	}




	public Director() {
		// TODO Auto-generated constructor stub
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




	public String getBiography() {
		return biography;
	}




	public void setBiography(String biography) {
		this.biography = biography;
	}




	public List<Movie> getFilmography() {
		return filmography;
	}




	public void setFilmography(List<Movie> filmography) {
		this.filmography = filmography;
	}




	public void addMovie(Movie movie) {
		if(movie.getDirector() != this) {
			movie.setDirector(this);
		}
		filmography.add(movie);
	}
}
