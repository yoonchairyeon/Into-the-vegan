package kr.ac.sku.intothevegan.presentation;

import kr.ac.sku.intothevegan.application.PostsService;
import kr.ac.sku.intothevegan.application.dto.CommentDto;
import kr.ac.sku.intothevegan.application.dto.PostsDto;
import kr.ac.sku.intothevegan.application.dto.UserDto;
import kr.ac.sku.intothevegan.application.security.auth.LoginUser;
import kr.ac.sku.intothevegan.domain.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 화면 연결 Controller
 */

@RequiredArgsConstructor
@Controller
public class PostsIndexController {

    private final PostsService postsService;

    //커뮤니티
    @GetMapping("/board")                 /* default page = 0, size = 10  */
    public String index(Model model,
                        @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
                        @LoginUser UserDto.Response user) {
        Page<Posts> list = postsService.pageList(pageable);

        if (user != null) {
            model.addAttribute("user", user);
        }

        model.addAttribute("posts", list);
//        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
//        model.addAttribute("next", pageable.next().getPageNumber());
//        model.addAttribute("hasNext", list.hasNext());
//        model.addAttribute("hasPrev", list.hasPrevious());

        return "posts/index";
    }
    /* 글 작성 */
    @GetMapping("/posts/write")
    public String write(@LoginUser UserDto.Response user, Model model) {
        if (user != null) {
            model.addAttribute("user", user.getName());
        }
        return "/posts/write";
    }

    /* 글 상세보기 */
    @GetMapping("/posts/read/{id}")
    public String read(@PathVariable Long id, @LoginUser UserDto.Response user, Model model) {
        PostsDto.Response dto = postsService.findById(id);
        List<CommentDto.Response> comments = dto.getComments();


        /* 댓글 관련 */
        if (comments != null && !comments.isEmpty()) {
            model.addAttribute("comments", comments);
        }

        /* 사용자 관련 */
        if (user != null) {
            model.addAttribute("user", user);

            /* 게시글 작성자 본인인지 확인 */
            if (dto.getUserId().equals(user.getId())) {
                model.addAttribute("writer", true);
            }

            /* 댓글 작성자 본인인지 확인 */
            if (comments.stream().anyMatch(s -> s.getUserId().equals(user.getId()))) {
                model.addAttribute("isWriter", true);
            }
/*            for (int i = 0; i < comments.size(); i++) {
                boolean isWriter = comments.get(i).getUserId().equals(user.getId());
                model.addAttribute("isWriter",isWriter);
            }*/
        }

        postsService.updateView(id); // views ++
        model.addAttribute("posts", dto);
        return "/posts/read";
    }

    @GetMapping("/posts/update/{id}")
    public String update(@PathVariable Long id, @LoginUser UserDto.Response user, Model model) {
        PostsDto.Response dto = postsService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("posts", dto);

        return "/posts/update";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable, @LoginUser UserDto.Response user) {
        Page<Posts> searchList = postsService.search(keyword, pageable);

        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("searchList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("hasPrev", searchList.hasPrevious());

        return "posts/search";
    }
}
