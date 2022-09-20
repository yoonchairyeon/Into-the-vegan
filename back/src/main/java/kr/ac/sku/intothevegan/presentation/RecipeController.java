package kr.ac.sku.intothevegan.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class RecipeController {

    @GetMapping("/recipe")
    public String index() {
        return "recipe/index";
    }
}
