package movie_app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Comment {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column
	private String text;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Movie movie;
	
	public Comment() {
		super();
	}
	
	
	public Comment(String text, Movie movie) {
		super();
		this.text = text;
		this.movie = movie;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
		if (!movie.getComments().contains(this)) {
			movie.getComments().add(this);
		}
	}
	
	
	
}
