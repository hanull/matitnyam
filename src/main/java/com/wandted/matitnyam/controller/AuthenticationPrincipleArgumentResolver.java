package com.wandted.matitnyam.controller;

import com.wandted.matitnyam.infrastructure.JwtTokenProvider;
import com.wandted.matitnyam.service.dto.LoginMember;
import com.wandted.matitnyam.support.JwtTokenExtractor;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class AuthenticationPrincipleArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationPrinciple.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter,
                                  final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest,
                                  final WebDataBinderFactory binderFactory) {
        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        final String token = JwtTokenExtractor.extract(Objects.requireNonNull(request));

        final Claims clams = jwtTokenProvider.getClams(token);
        final long id = (long) clams.get("id");
        final String role = String.valueOf(clams.get("role"));

        return new LoginMember(id, role);
    }
}
