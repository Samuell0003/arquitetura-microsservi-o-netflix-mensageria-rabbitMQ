package com.iftm.newsletter.controllers;

import com.iftm.newsletter.models.News;
import com.iftm.newsletter.models.dto.NewsDTO;
import com.iftm.newsletter.models.dto.SaveNewsDTO;
import com.iftm.newsletter.services.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
//@CrossOrigin
public class NewsController {

    private NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<NewsDTO>> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> findById(@PathVariable("id") String id) {
        return  service.findById(id);
    }

    @PostMapping
    public ResponseEntity<NewsDTO> save(@RequestBody SaveNewsDTO news) {
        return service.save(news);
    }

    @PutMapping
    public ResponseEntity<NewsDTO> update(@RequestBody  NewsDTO news) {
        return service.update(news);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id, @RequestBody String recipientToken) {
        return service.delete(id, recipientToken);
    }
}
