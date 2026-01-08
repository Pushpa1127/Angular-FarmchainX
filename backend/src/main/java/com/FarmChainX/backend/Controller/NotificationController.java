package com.FarmChainX.backend.Controller;

import com.FarmChainX.backend.Model.Notification;
import com.FarmChainX.backend.Service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Create notification
    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    // Get all notifications
    @GetMapping("/{userId}/{role}")
    public List<Notification> getUserNotifications(
            @PathVariable String userId,
            @PathVariable String role) {
        return notificationService.getUserNotifications(userId, role);
    }

    // Get unread notifications
    @GetMapping("/{userId}/{role}/unread")
    public List<Notification> getUnread(
            @PathVariable String userId,
            @PathVariable String role) {
        return notificationService.getUnreadNotifications(userId, role);
    }

    // Unread count
    @GetMapping("/{userId}/{role}/count")
    public long getUnreadCount(
            @PathVariable String userId,
            @PathVariable String role) {
        return notificationService.getUnreadCount(userId, role);
    }

    // Mark one as read
    @PutMapping("/{id}/read")
    public void markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
    }

    // Mark all as read
    @PutMapping("/{userId}/{role}/read-all")
    public void markAllAsRead(
            @PathVariable String userId,
            @PathVariable String role) {
        notificationService.markAllAsRead(userId, role);
    }
}
