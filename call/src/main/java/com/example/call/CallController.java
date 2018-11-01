package com.example.call;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
@RestController
public class CallController {

    @Value("${httpbin.url:http://localhost:8000}")
    private String httpbinUrl;

    private final RestTemplate restTemplate;

    public CallController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/call")
    public String sentimentAnalysis() {
        return restTemplate.getForObject(httpbinUrl + "/ip", String.class);
    }
}


