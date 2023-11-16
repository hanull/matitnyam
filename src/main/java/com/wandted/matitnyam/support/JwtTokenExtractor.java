package com.wandted.matitnyam.support;

import com.wandted.matitnyam.exception.InvalidTokenException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import org.springframework.http.HttpHeaders;

public class JwtTokenExtractor {

    private static final String TOKEN_TYPE = "Bearer";

    public static String extract(final HttpServletRequest request) {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(token)) {
            throw new InvalidTokenException("토큰값으로 null을 사용할 수 없습니다.");
        }

        if (!token.toLowerCase().startsWith(TOKEN_TYPE.toLowerCase())) {
            throw new InvalidTokenException("토큰 타입이 올바르지 않습니다.");
        }

        return token.substring(TOKEN_TYPE.length() + 1);
    }
}
