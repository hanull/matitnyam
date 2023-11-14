package com.wandted.matitnyam.infrastructure;

import com.wandted.matitnyam.infrastructure.dto.GithubAccessTokenRequest;
import com.wandted.matitnyam.infrastructure.dto.GithubAccessTokenResponse;
import com.wandted.matitnyam.infrastructure.dto.GithubProfileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GithubOauthProvider {

    private final String clientId;
    private final String clientSecret;
    private final GithubAccessTokenOpenFeign accessTokenOpenFeign;
    private final GithubProfileOpenFeign profileOpenFeign;

    public GithubOauthProvider(@Value("${oauth.github.clientId}") final String clientId,
                               @Value("${oauth.github.clientSecret}") final String clientSecret,
                               final GithubAccessTokenOpenFeign accessTokenOpenFeign,
                               final GithubProfileOpenFeign profileOpenFeign) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenOpenFeign = accessTokenOpenFeign;
        this.profileOpenFeign = profileOpenFeign;
    }

    public GithubAccessTokenResponse getAccessToken(final String code) {
        return accessTokenOpenFeign.getAccessToken(new GithubAccessTokenRequest(clientId, clientSecret, code));
    }

    public GithubProfileResponse getProfile(final String accessToken) {
        final String authorizationHeader = "Bearer " + accessToken;
        return profileOpenFeign.getProfile(authorizationHeader);
    }
}
