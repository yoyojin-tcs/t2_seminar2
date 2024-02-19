package ru.tinkoff.edu.java.t2_seminar2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class AuditController {

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<String> getLogs(){
        return List.of("First access log record", "Second access log record");
    }

}
