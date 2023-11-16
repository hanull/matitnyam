package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.infrastructure.JwtTokenProvider;
import com.wandted.matitnyam.support.JwtTokenExtractor;
import feign.Request.HttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        if (isPreflight(request)) {
            return true;
        }

        final String token = JwtTokenExtractor.extract(request);

        return jwtTokenProvider.isValid(token);
    }

    private boolean isPreflight(final HttpServletRequest request) {
        return request.getMethod().equals(HttpMethod.OPTIONS.toString());
    }
}
