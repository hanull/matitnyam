package com.wandted.matitnyam.infrastructure;

import com.wandted.matitnyam.infrastructure.dto.GithubProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "githubProfileOpenFeign", url = "${oauth.github.profileUrl}")
public interface GithubProfileOpenFeign {

    @GetMapping
    GithubProfileResponse getProfile(@RequestHeader("Authorization") String authorization);
}
