package kr.ac.sku.intothevegan.infrastructure.persistence;

import kr.ac.sku.intothevegan.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Integer> {

    /* �����Խ��� ��� ��� */
    @Query("SELECT c from Comment c where c.board.no=:boardNo and c.id>0 order by c.id ASC")
    public List<Comment> getCommentsOfBoard(@Param("boardNo") Integer boardNo);


}
