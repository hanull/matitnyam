package com.wandted.matitnyam.infrastructure.dto;

public record GithubAccessTokenRequest(String clientId,
                                       String clientSecret,
                                       String code) {
}
