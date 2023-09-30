package com.iftm.serverlogs.services;

import com.iftm.serverlogs.models.Logs;
import com.iftm.serverlogs.models.dto.LogsDTO;
import com.iftm.serverlogs.models.dto.NewsDTO;
import com.iftm.serverlogs.repositories.ServerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ServerLogsService {
    private ServerRepository repository;

    public ServerLogsService(ServerRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<List<LogsDTO<NewsDTO>>> findAll() {
        var logsDb = repository.findAll();
        if (logsDb == null || logsDb.size() == 0)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(logsDb.stream().map(e ->
                new LogsDTO<NewsDTO>(e)
        ).collect(Collectors.toList()));
    }

    public ResponseEntity<LogsDTO<NewsDTO>> save(Logs logs) {
        return ResponseEntity.ok(new LogsDTO(repository.save(logs)));
    }
}
