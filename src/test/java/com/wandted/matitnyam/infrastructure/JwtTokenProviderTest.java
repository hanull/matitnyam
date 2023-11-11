package com.wandted.matitnyam.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import com.wandted.matitnyam.domain.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {

    @DisplayName("토큰을 생성한다.")
    @Test
    void create() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider("testkeytestkeytestkeytestkeytestkey", 3600000L);

        final String token = jwtTokenProvider.createToken(1L, MemberRole.USER);

        assertThat(token).isNotNull();
    }
}
