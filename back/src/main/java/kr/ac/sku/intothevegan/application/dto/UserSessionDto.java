package kr.ac.sku.intothevegan.application.dto;

import kr.ac.sku.intothevegan.domain.Role;
import kr.ac.sku.intothevegan.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {
    private String username;
    private String password;
    private String name;
    private String email;
    private Role role;
    private String modifiedDate;

    /* Entity -> Dto */
    public UserSessionDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.modifiedDate = user.getModifiedDate();
    }
}