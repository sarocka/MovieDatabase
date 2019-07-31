package movie_app.web.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import movie_app.model.Comment;
import movie_app.service.CommentService;
import movie_app.service.MovieService;
import movie_app.support.CommentDTOToComment;
import movie_app.support.CommentToCommentDTO;
import movie_app.web.dto.CommentDTO;


@RestController
@RequestMapping(value="/api/comments")

public class ApiCommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	MovieService movieService;
	@Autowired
	CommentToCommentDTO toDto;
	@Autowired
	CommentDTOToComment toComment;
	
	
	
	@RequestMapping(method=RequestMethod.POST,	consumes="application/json")
	public ResponseEntity<CommentDTO> add(@RequestBody CommentDTO dto){
	Comment r= toComment.convert(dto);
	Comment saved = commentService.save(r);
	if (saved!=null) {
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
		
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
}
	
}
