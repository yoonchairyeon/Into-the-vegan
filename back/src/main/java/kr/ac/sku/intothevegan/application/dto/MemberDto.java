package kr.ac.sku.intothevegan.application.dto;

import kr.ac.sku.intothevegan.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    @Id
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @Nullable
    private String address = null;

    @NotBlank
    private String email = null;

    @Nullable
    private String birthday = null;

    @Nullable
    private String role;

    private Date createdDate;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .password(password)
                .name(name)
                .phone(phone)
                .address(address)
                .email(email)
                .birthday(birthday)
                .role(role)
                .createdDate(createdDate)
                .build();
    }

    @Builder
    public MemberDto(String id, String password, String name, String phone, String address, String email, String birthday, String role, Date createdDate) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.createdDate = createdDate;
    }
}