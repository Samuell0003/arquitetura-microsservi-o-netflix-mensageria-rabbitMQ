package com.iftm.serverlogs.controller;

import com.iftm.serverlogs.models.dto.LogsDTO;
import com.iftm.serverlogs.models.dto.NewsDTO;
import com.iftm.serverlogs.services.ServerLogsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class ServerLogsController {
    private ServerLogsService service;

    public ServerLogsController(ServerLogsService service) {
        this.service = service;
    }
    @GetMapping()
    public ResponseEntity<List<LogsDTO<NewsDTO>>> findAll() {
        return service.findAll();
    }
}
