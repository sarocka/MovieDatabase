package movie_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import movie_app.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
