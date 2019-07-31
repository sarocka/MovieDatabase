package movie_app.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import movie_app.model.Comment;
import movie_app.web.dto.*;



@Component
public class CommentToCommentDTO implements Converter<Comment, CommentDTO> {

	@Override
	public CommentDTO convert(Comment comment) {
		CommentDTO dto = new CommentDTO();
		
		dto.setId(comment.getId());
		dto.setMovieId(comment.getMovie().getId());
		dto.setText(comment.getText());
		return dto;
	}
	
	public List<CommentDTO> convert(List<Comment>lista){
		List<CommentDTO> ret = new ArrayList<>();
		
		for(Comment comment : lista){
			ret.add(convert(comment));
		}
		
		return ret;
	}
	
}
