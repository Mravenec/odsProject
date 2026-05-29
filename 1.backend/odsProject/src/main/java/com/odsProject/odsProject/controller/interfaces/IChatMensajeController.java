package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface IChatMensajeController {

    ResponseEntity<List<Map<String, Object>>> listMessages(
            @PathVariable Integer id,
            Integer actorUserId,
            String actorRole);

    ResponseEntity<Map<String, Object>> sendMessage(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> body);

    ResponseEntity<Map<String, Object>> editMessage(
            @PathVariable Integer id,
            @PathVariable Integer msgId,
            @RequestBody Map<String, Object> body);
}
