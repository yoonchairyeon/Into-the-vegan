package kr.ac.sku.intothevegan.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column(nullable = false, unique = true)
    private String name; //이름

    @Column(nullable = false, length = 100)
    private String password; //비밀번호

    @Column(nullable = false, length = 50, unique = true)
    private String email; //이메일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /* 회원정보 수정 */
    public void modify(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }
}
