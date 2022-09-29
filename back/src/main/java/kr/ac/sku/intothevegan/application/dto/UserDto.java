package kr.ac.sku.intothevegan.application.dto;

import kr.ac.sku.intothevegan.domain.Role;
import kr.ac.sku.intothevegan.domain.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    /** 회원 Service 요청(Request) DTO 클래스 */
        private Long id;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{4,20}$", message = "아이디는 특수문자를 제외한 4~20자리여야 합니다.")
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        private String username;

        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
        private String password;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;

        @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        private String email;

        private Role role;

        /* DTO -> Entity */
        public User toEntity() {
            User user = User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .name(name)
                    .email(email)
                    .role(role.USER)
                    .build();
            return user;
        }
}
