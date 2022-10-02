package kr.ac.sku.intothevegan.presentation;

import kr.ac.sku.intothevegan.application.dto.CommentDto;
import kr.ac.sku.intothevegan.application.service.CommentService;
import kr.ac.sku.intothevegan.domain.Comment;
import kr.ac.sku.intothevegan.infrastructure.persistence.MemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    @NonNull
    MemberRepository memberRepository;
    @NonNull
    CommentService commentService;

    @PostMapping("/add")
    public Comment addComment(@RequestBody CommentDto commentDto) { //, Principal principal){
        return this.commentService.createComment(new Comment(commentDto.getContent()), commentDto.getMemberId(), commentDto.getBoardNo());
    }


    @GetMapping("/list/{boardNo}")
    public ResponseEntity<List<Comment>> addComment(@PathVariable int boardNo){
        return new ResponseEntity<>(this.commentService.Listcomments(boardNo),HttpStatus.CREATED);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int commentId, @RequestBody CommentDto commentDto) { //, Principal principal){
        return this.commentService.updateComment(commentId, new Comment(commentDto.getContent()));
    }

    @DeleteMapping("/delete/{boardNo}/{commentNo}")
    public ResponseEntity<List<Comment>> addComment(@PathVariable int boardNo,@PathVariable int commentNo) {
        return new ResponseEntity<>(this.commentService.Deletecomment(commentNo, boardNo),HttpStatus.CREATED);
    }

}

