package movie_app.service;

import java.util.List;

import movie_app.model.Comment;

public interface CommentService {
	Comment findOne(long id);
	List<Comment>findAll();
	Comment save (Comment comment);
	List<Comment> save (List<Comment>comments);
}
