package edu.escuelaing.parcialfinal.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.parcialfinal.model.Client;

@RestController
@RequestMapping("/api")
public class IndexController {
    private final Client client;

    public IndexController() {
        this.client = new Client();
    }

    @GetMapping(value = "/status", produces = "application/json")
    public String status() {
        return String.format("{\"status\":\"Greetings from Spring Boot. %s, %s. The server is Running!\"}",
                LocalDate.now(), LocalTime.now());
    }

    @GetMapping("/rest")
    public String search(@RequestParam(value = "quer", defaultValue = "MSFT") String val,
                         @RequestParam(value = "type", defaultValue = "TIME_SERIES_DAILY_ADJUSTED") String type) throws IOException {
        return client.invoke(val, type);
    }
}
