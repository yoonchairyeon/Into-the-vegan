package kr.ac.sku.intothevegan.controller;

import kr.ac.sku.intothevegan.domain.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

    @PostMapping("/api/members")
    public Member member(){

        Member member = new Member("sku","홍길동","ma123","sku1@sku.ac.kr");

        return member;
    }

}
