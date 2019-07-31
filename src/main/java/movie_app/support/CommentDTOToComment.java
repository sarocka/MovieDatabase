package movie_app.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Comment;
import movie_app.model.Movie;
import movie_app.service.CommentService;
import movie_app.service.MovieService;
import movie_app.web.dto.CommentDTO;


@Component

public class CommentDTOToComment implements Converter<CommentDTO, Comment> {
	@Autowired
	private CommentService commentService;
	@Autowired
	private MovieService movieService;

	public Comment convert(CommentDTO dto) {
		Movie movie = movieService.findOne(dto.getMovieId());
		if (movie!=null) {
			Comment comment = null;
			if(dto.getId()!=null){
				comment= commentService.findOne(dto.getId());
			}else{
				comment=new Comment();
			}
			comment.setId(dto.getId());
			comment.setMovie(movie);
			comment.setText(dto.getText());
			
			return comment;
		} else {
			throw new IllegalArgumentException();
		}
		
	}
	
	public List<Comment> convert(List<CommentDTO> listDTO){
		List<Comment> ret = new ArrayList<>();
		
		for(CommentDTO dto: listDTO){
			ret.add(convert(dto));
		}
		
		return ret;
	}
}
