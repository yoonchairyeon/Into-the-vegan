package kr.ac.sku.intothevegan.application.service;

import kr.ac.sku.intothevegan.application.validator.ResourceNotFoundException;
import kr.ac.sku.intothevegan.domain.CommentDiary;
import kr.ac.sku.intothevegan.domain.Diary;
import kr.ac.sku.intothevegan.domain.Member;
import kr.ac.sku.intothevegan.infrastructure.persistence.CommentDiaryRepository;
import kr.ac.sku.intothevegan.infrastructure.persistence.DiaryRepository;
import kr.ac.sku.intothevegan.infrastructure.persistence.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentDiaryService {

    @NonNull
    private DiaryRepository boardRepository;
    @NonNull
    private CommentDiaryRepository commentRepository;
    @NonNull
    private MemberRepository memberRepository;

    /* 다이어리 댓글 등록 */
    public CommentDiary createComment(CommentDiary comment, String memberId, int boardNo) {
        Optional<Diary> board = this.boardRepository.findById(boardNo);
        Optional<Member> member = this.memberRepository.findById(memberId);
        board.ifPresent(re->{
            comment.changeBoard(re);
        });
        member.ifPresent(re->{
            comment.changeAuthor(re);
        });
        System.out.println(comment);
        return this.commentRepository.save(comment);
    }

    /* 다이어리 댓글 수정 */
    public ResponseEntity<CommentDiary> updateComment(Integer id, CommentDiary updatedComment) {
        CommentDiary comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by id : [" + id + "]"));
        comment.setContent(updatedComment.getContent());
        comment.setLast_modified_date((new Timestamp(System.currentTimeMillis())).toString());

        CommentDiary endUpdatedComment = commentRepository.save(comment);
        return ResponseEntity.ok(endUpdatedComment);
    }

    /* 다이어리 댓글 목록 조회 */
    @Transactional(readOnly = true)
    public List<CommentDiary> Listcomments(int boardNo) {
        return this.commentRepository.getCommentsOfBoard(boardNo);
    }

    /* 다이어리 댓글 삭제 */
    @Transactional
    public List<CommentDiary> Deletecomment(int commentNo, int boardNo) {
        this.commentRepository.deleteById(commentNo);
        return this.commentRepository.getCommentsOfBoard(boardNo);
    }

}
