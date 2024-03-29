package kr.ac.sku.intothevegan.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    //홈 화면
    @RequestMapping("/")
    public String home(){
        log.info("index controller");
        return "index";
    }
}
