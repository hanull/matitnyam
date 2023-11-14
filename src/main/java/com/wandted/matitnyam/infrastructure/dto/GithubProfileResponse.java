package com.wandted.matitnyam.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubProfileResponse(@JsonProperty("id") String githubId,
                                    @JsonProperty("login") String name,
                                    String email) {
}
