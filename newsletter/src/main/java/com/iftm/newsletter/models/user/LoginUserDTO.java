package com.iftm.newsletter.models.user;

public record LoginUserDTO(String avatar, String email, String firstName, String lastName, String password, UserRole role) {
}
