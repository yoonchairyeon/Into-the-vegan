package kr.ac.sku.intothevegan.application.service;

import kr.ac.sku.intothevegan.application.validator.ResourceNotFoundException;
import kr.ac.sku.intothevegan.domain.Board;
import kr.ac.sku.intothevegan.domain.Comment;
import kr.ac.sku.intothevegan.domain.Member;
import kr.ac.sku.intothevegan.infrastructure.persistence.BoardRepository;
import kr.ac.sku.intothevegan.infrastructure.persistence.CommentRepository;
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
public class CommentService {

    @NonNull
    private BoardRepository boardRepository;
    @NonNull
    private CommentRepository commentRepository;
    @NonNull
    private MemberRepository memberRepository;

    public Comment createComment(Comment comment, String memberId, int boardNo) {
        Optional<Board> board = this.boardRepository.findById(boardNo);
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

    /* �����Խ��� ��� ���� */
    public ResponseEntity<Comment> updateComment(Integer id, Comment updatedComment) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by id : [" + id + "]"));
        comment.setContent(updatedComment.getContent());
        comment.setLast_modified_date((new Timestamp(System.currentTimeMillis())).toString());

        Comment endUpdatedComment = commentRepository.save(comment);
        return ResponseEntity.ok(endUpdatedComment);
    }

    /* �����Խ��� ��� ��� ��ȸ */
    @Transactional(readOnly = true)
    public List<Comment> Listcomments(int boardNo) {
        return this.commentRepository.getCommentsOfBoard(boardNo);
    }

    /* �����Խ��� ��� ���� */
    @Transactional
    public List<Comment> Deletecomment(int commentNo, int boardNo) {
        this.commentRepository.deleteById(commentNo);
        return this.commentRepository.getCommentsOfBoard(boardNo);
    }
}
