package kr.ac.sku.intothevegan.infrastructure.persistence;

import kr.ac.sku.intothevegan.domain.CommentDiary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentDiaryRepository extends CrudRepository<CommentDiary,Integer> {

    /* ���̾ ��� ��� */
    @Query("SELECT c from CommentDiary c where c.board.no=:boardNo and c.id>0 order by c.id ASC")
    public List<CommentDiary> getCommentsOfBoard(@Param("boardNo") Integer boardNo);


}
