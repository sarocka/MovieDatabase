package movie_app.service_impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie_app.model.Comment;
import movie_app.repository.CommentRepository;
import movie_app.service.CommentService;
import movie_app.service.MovieService;

@Service
@Transactional
public class JpaCommentService implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	@Override
	public Comment findOne(long id) {
		// TODO Auto-generated method stub
		return commentRepository.findOne(id);
	}

	@Override
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}

	

	@Override
	public List<Comment> save(List<Comment> comment) {
		
		return commentRepository.save(comment);
	}

	@Override
	public Comment save(Comment comment) {
		
		return commentRepository.save(comment);
		 
		}

	
}
