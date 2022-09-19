package kr.ac.sku.intothevegan.presentation;

import kr.ac.sku.intothevegan.application.PostsService;
import kr.ac.sku.intothevegan.application.dto.PostsDto;
import kr.ac.sku.intothevegan.application.dto.UserDto;
import kr.ac.sku.intothevegan.application.security.auth.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST API Controller
 */
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    /* CREATE */
    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostsDto.Request dto, @LoginUser UserDto.Response user) {
        return ResponseEntity.ok(postsService.save(dto, user.getName()));
    }

    /* READ */
    @GetMapping("/posts/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(postsService.findById(id));
    }

    /* UPDATE */
    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    /* DELETE */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
}
