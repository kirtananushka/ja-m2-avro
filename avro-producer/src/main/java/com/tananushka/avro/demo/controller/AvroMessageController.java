package com.tananushka.avro.demo.controller;

import com.tananushka.avro.demo.dto.AvroRequest;
import com.tananushka.avro.demo.service.AvroMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class AvroMessageController {

    private final AvroMessageService avroMessageService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody AvroRequest avroRequest) {
        avroMessageService.sendMessage(avroRequest);
        Map<String, String> response = Map.of("response", "Message sent to the Kafka topic");
        return ResponseEntity.accepted().body(response);
    }
}