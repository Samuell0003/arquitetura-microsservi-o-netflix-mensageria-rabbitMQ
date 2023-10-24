package com.iftm.newsletter.services;

import com.iftm.newsletter.mensages.RabbitMqSendLog;
import com.iftm.newsletter.models.News;
import com.iftm.newsletter.models.NotificationMessage;
import com.iftm.newsletter.models.dto.LogDTO;
import com.iftm.newsletter.models.dto.NewsDTO;
import com.iftm.newsletter.models.dto.SaveNewsDTO;
import com.iftm.newsletter.repositories.NewsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class NewsService {
    private NewsRepository repository;
    @Autowired
    private RabbitMqSendLog rabbitMqSendLog;
    @Autowired
    private FirebaseMessaginsService firebaseMessaginsService;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<NewsDTO>> findAll() {
        var newsDb = repository.findAll();
        if (newsDb.isEmpty())
            ResponseEntity.notFound().build();

        var newsDto = newsDb.stream().map( e ->
            new NewsDTO(e)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(newsDto);
    }

    public ResponseEntity<NewsDTO> findById(String id) {
        if (repository.findById(new ObjectId(id)).isEmpty() || id == null)
            ResponseEntity.notFound().build();

        return ResponseEntity.ok(new NewsDTO(repository.findById(new ObjectId(id)).get()));
    }

    public ResponseEntity<NewsDTO> save(SaveNewsDTO news) {
        var newsDb = new NewsDTO(repository.save(news.news()));
        rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("save", newsDb));
        if (news.recipientToken() != null)
            firebaseMessaginsService.sendNotification(
                    new NotificationMessage(news.recipientToken(), "Noticia salva", news.news().getTitle(), news.image())
            );
        return ResponseEntity.ok(newsDb);
    }

    public ResponseEntity<NewsDTO> update(NewsDTO news) {
        if (news.getId() == null)
            return ResponseEntity.badRequest().build();
        var newsDb = repository.findById(new ObjectId(news.getId()));
        if (newsDb.isEmpty())
            return  ResponseEntity.notFound().build();

        newsDb.get().setTitle(news.getTitle());
        newsDb.get().setDate(news.getDate());
        newsDb.get().setPosts(news.getPosts());
        newsDb.get().setEditorName(news.getEditorName());

        rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("update", new NewsDTO(newsDb.get())));

        return ResponseEntity.ok(new NewsDTO(repository.save(newsDb.get())));
    }

    public ResponseEntity<?> delete(String id, String recipientToken) {
        var newsDb = repository.findById(new ObjectId(id));

        if (!newsDb.isPresent())
            return ResponseEntity.badRequest().build();

        repository.deleteById(new ObjectId(id));
        rabbitMqSendLog.sendLog(new LogDTO<NewsDTO>("delete", new NewsDTO(newsDb.get())));
        if (recipientToken != null)
            firebaseMessaginsService.sendNotification(
                    new NotificationMessage(recipientToken, "Noticia deletada", newsDb.get().getTitle(), "")
            );
        return ResponseEntity.ok("success");
    }

}
