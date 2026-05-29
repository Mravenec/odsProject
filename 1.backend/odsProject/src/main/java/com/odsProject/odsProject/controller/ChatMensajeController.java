package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.controller.interfaces.IChatMensajeController;
import com.odsProject.odsProject.service.interfaces.IChatMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ChatMensajeController implements IChatMensajeController {

    @Autowired
    private IChatMensajeService chatMensajeService;

    @Override
    @GetMapping("/{id}/chat/messages")
    public ResponseEntity<List<Map<String, Object>>> listMessages(
            @PathVariable Integer id,
            @RequestParam Integer actorUserId,
            @RequestParam String actorRole) {
        try {
            return ResponseEntity.ok(
                    chatMensajeService.listMessages(id, actorUserId, actorRole));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @Override
    @PostMapping("/{id}/chat/messages")
    public ResponseEntity<Map<String, Object>> sendMessage(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> body) {
        try {
            Integer actor = toInt(body.get("actorUserId"));
            String role = body.get("actorRole") != null ? String.valueOf(body.get("actorRole")) : "";
            String cuerpo = body.get("cuerpo") != null ? String.valueOf(body.get("cuerpo")) : "";
            return ResponseEntity.ok(chatMensajeService.sendMessage(id, actor, role, cuerpo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    @Override
    @PutMapping("/{id}/chat/messages/{msgId}")
    public ResponseEntity<Map<String, Object>> editMessage(
            @PathVariable Integer id,
            @PathVariable Integer msgId,
            @RequestBody Map<String, Object> body) {
        try {
            Integer actor = toInt(body.get("actorUserId"));
            String cuerpo = body.get("cuerpo") != null ? String.valueOf(body.get("cuerpo")) : "";
            return ResponseEntity.ok(chatMensajeService.editMessage(id, msgId, actor, cuerpo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    private static Integer toInt(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.intValue();
        return Integer.valueOf(String.valueOf(v));
    }
}
