package kr.ac.sku.intothevegan.infrastructure.persistence;

import kr.ac.sku.intothevegan.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    /* 게시글 모든 정보 조회 */
    public final static String SELECT_BOARD_LIST_PAGED = ""
            + "SELECT "
            + "no,"
            + "title,"
            + "contents,"
            + "member_id,"
            + "created_time,"
            + "updated_time,"
            + "counts"
            + " FROM board WHERE 0 < no "
            + "ORDER BY no DESC LIMIT ?1, ?2";
    @Query(value = SELECT_BOARD_LIST_PAGED, nativeQuery = true)
    List<Board> findFromTo(final Integer objectStartNum, final Integer objectCountPerPage);


    /* 조회수 증가 */
    public String ADD_COUNTS = "UPDATE board b SET b.counts = b.counts + 1 WHERE b.no=:no";
    @Modifying
    @Query(value = ADD_COUNTS, nativeQuery = true)
    Integer addCounts(final Integer no);


    /* 조회수 높은 글 4개 조회 */
    public String SELECT_BEST_BOARD = "SELECT * FROM board ORDER BY counts DESC, created_time ASC LIMIT 4";
    @Query(value = SELECT_BEST_BOARD, nativeQuery = true)
    List<Board> findTopByCounts();


    /* 검색 */
    public String SELECT_SEARCH_BOARD = "SELECT * FROM board " +
            "WHERE title LIKE %:search% ORDER BY no DESC " +
             "LIMIT :objectStartNum, :objectCountPerPage";
    public String SELECT_SEARCH_COUNT = "SELECT count(*) FROM board WHERE title LIKE %:search%";
    @Query(value = SELECT_SEARCH_BOARD, nativeQuery = true)
    List<Board> findBoardByTitle(final String search, final Integer objectStartNum, final Integer objectCountPerPage);
    @Query(value = SELECT_SEARCH_COUNT, nativeQuery = true)
    Long countBoardsByTitleContains(@Param("search") String search);
}
