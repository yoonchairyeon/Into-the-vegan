package kr.ac.sku.intothevegan.application.validator;

import kr.ac.sku.intothevegan.application.dto.UserDto;
import kr.ac.sku.intothevegan.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 중복 확인 유효성 검증을 위한 커스텀 Validator 클래스
 */
@RequiredArgsConstructor
@Component
public class CustomValidators {

    @RequiredArgsConstructor
    @Component
    public static class EmailValidator extends AbstractValidator<UserDto> {
        private final UserRepository userRepository;

        @Override
        protected void doValidate(UserDto dto, Errors errors) {
            if (userRepository.existsByEmail(dto.toEntity().getEmail())) {
                errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class UsernameValidator extends AbstractValidator<UserDto> {
        private final UserRepository userRepository;

        @Override
        protected void doValidate(UserDto dto, Errors errors) {
            if (userRepository.existsByUsername(dto.toEntity().getUsername())) {
                errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
            }
        }
    }
}
