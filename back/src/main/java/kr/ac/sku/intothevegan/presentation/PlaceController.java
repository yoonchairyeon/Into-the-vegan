package kr.ac.sku.intothevegan.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PlaceController {

    @GetMapping("/maps")
    public String index(){
        return "place/maps";
    }
}
