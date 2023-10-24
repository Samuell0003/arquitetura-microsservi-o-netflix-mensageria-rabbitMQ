package com.iftm.newsletter.models.dto;

import com.iftm.newsletter.models.News;

public record SaveNewsDTO(String recipientToken, String image, News news) {
}
