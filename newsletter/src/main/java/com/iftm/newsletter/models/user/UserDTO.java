package com.iftm.newsletter.models.user;

public record UserDTO(String login, String password, UserRole role) {
}
