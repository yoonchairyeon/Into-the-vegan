package kr.ac.sku.intothevegan.domain;

import javax.persistence.Entity;


public class Member {
    String id;
    String username;
    String password;
    String email;

    public Member(String id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
