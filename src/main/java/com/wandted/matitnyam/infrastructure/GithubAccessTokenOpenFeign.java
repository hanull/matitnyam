package com.wandted.matitnyam.infrastructure;

import com.wandted.matitnyam.infrastructure.dto.GithubAccessTokenRequest;
import com.wandted.matitnyam.infrastructure.dto.GithubAccessTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "githubAccessTokenOpenFeign", url = "${oauth.github.accessTokenUrl}")
public interface GithubAccessTokenOpenFeign {

    @PostMapping
    GithubAccessTokenResponse getAccessToken(@RequestBody GithubAccessTokenRequest request);
}
