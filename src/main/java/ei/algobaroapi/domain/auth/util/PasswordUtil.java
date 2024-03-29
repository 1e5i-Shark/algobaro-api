package ei.algobaroapi.domain.auth.util;

import ei.algobaroapi.domain.auth.exception.AuthInputException;
import ei.algobaroapi.domain.auth.exception.common.AuthErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class PasswordUtil {

    private static final int PASSWORD_LENGTH = 8;
    private static final String SPECIAL_CHARACTERS_REGEX = ".*[!@#$%^&*()].*";

    private final PasswordEncoder passwordEncoder;

    public String validateAndEncryptPassword(String rawPassword) {
        if (!isValidPassword(rawPassword)) {
            throw AuthInputException.of(AuthErrorCode.INVALID_PASSWORD);
        }
        return passwordEncoder.encode(rawPassword);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= PASSWORD_LENGTH && password.matches(SPECIAL_CHARACTERS_REGEX);
    }

    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
