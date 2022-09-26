package kr.ac.sku.intothevegan.application.security.auth;

import kr.ac.sku.intothevegan.application.dto.UserDto;
import kr.ac.sku.intothevegan.application.dto.UserSessionDto;
import kr.ac.sku.intothevegan.domain.User;
import kr.ac.sku.intothevegan.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Security User Service
 */
@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession session;

    /* username이 DB에 있는지 확인 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

        session.setAttribute("user", new UserSessionDto(user));

        /* 시큐리티 세션에 유저 정보 저장 */
        return new CustomUserDetails(user);
    }
}
