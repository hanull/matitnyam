package com.wandted.matitnyam.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wandted.matitnyam.exception.InvalidTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;

class JwtTokenExtractorTest {

    @DisplayName("토큰 타입이 올바르지 않은 경우, 예외를 발생한다.")
    @Test
    void invalidType() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "basic dddd");

        assertThatThrownBy(() -> JwtTokenExtractor.extract(request))
                .isInstanceOf(InvalidTokenException.class)
                .hasMessage("토큰 타입이 올바르지 않습니다.");
    }

    @DisplayName("올바르게 토큰을 반환한다.")
    @Test
    void extract() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final String expected = "expectedToken";
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + expected);

        final String actual = JwtTokenExtractor.extract(request);

        assertThat(actual).isEqualTo(expected);
    }
}
