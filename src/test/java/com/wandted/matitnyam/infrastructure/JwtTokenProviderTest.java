package com.wandted.matitnyam.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.wandted.matitnyam.domain.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {

    private static final String KEY = "testkeytestkeytestkeytestkeytestkey";
    private static final long VALIDITY_TIME = 3600000L;

    @DisplayName("토큰을 생성한다.")
    @Test
    void create() {
        final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(KEY, VALIDITY_TIME);

        final String token = jwtTokenProvider.createToken(1L, MemberRole.USER);

        assertThat(token).isNotNull();
    }

    @DisplayName("올바르지 않은 토큰인 경우, false를 반환한다.")
    @Test
    void inValidToken() {
        final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(KEY, 0);
        final String token = jwtTokenProvider.createToken(1L, MemberRole.USER);

        final boolean actual = jwtTokenProvider.isValid(token);

        assertThat(actual).isFalse();
    }

    @DisplayName("올바른 토큰인 경우, true를 반환한다.")
    @Test
    void validToken() {
        final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(KEY, VALIDITY_TIME);
        final String token = jwtTokenProvider.createToken(1L, MemberRole.USER);

        final boolean actual = jwtTokenProvider.isValid(token);

        assertThat(actual).isTrue();
    }
}
