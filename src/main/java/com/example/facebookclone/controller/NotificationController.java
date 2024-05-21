package com.example.facebookclone.controller;

import com.example.facebookclone.DTO.NotifyDTO;
import com.example.facebookclone.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facebook.api/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping("/{id}")
    public List<NotifyDTO> getNotificationsByAccount(@PathVariable int id) {
        return notificationService.getNotificationsByAccount(id);
    }

    @PostMapping("/create")
    public NotifyDTO createNotification(
            @RequestParam(name = "from_account_id") Integer from_account_id,
            @RequestParam(name = "to_account_id") Integer to_account_id,
            @RequestParam(name = "to_post_id", required = false) Integer to_post_id,
            @RequestParam(name = "to_share_id", required = false) Integer to_share_id,
            @RequestParam(name = "to_comment_post_id", required = false) Integer to_comment_post_id,
            @RequestParam(name = "to_comment_share_id", required = false) Integer to_comment_share_id,
            @RequestParam(name = "notify_type") String notify_type
    ) {
        return  notificationService.createNotification(from_account_id, to_account_id, to_post_id, to_share_id, to_comment_post_id, to_comment_share_id, notify_type);
    }

    @PatchMapping("/update/{id_notify}")
    public NotifyDTO updateNotification(@PathVariable int id_notify) {
        return notificationService.updateNotification(id_notify);
    }

    @DeleteMapping("/delete/{id_notify}")
    public ResponseEntity<String> deleteNotification(@PathVariable int id_notify) {
        notificationService.deleteNotification(id_notify);
        return ResponseEntity.ok("Delete notification successfully");
    }
}
