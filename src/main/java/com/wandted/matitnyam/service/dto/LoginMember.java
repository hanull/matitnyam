package com.wandted.matitnyam.service.dto;

public class LoginMember {

    private final long id;
    private final String role;

    public LoginMember(final long id, final String role) {
        this.id = id;
        this.role = role;
    }
}
