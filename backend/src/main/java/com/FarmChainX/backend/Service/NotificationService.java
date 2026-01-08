package com.FarmChainX.backend.Service;

import com.FarmChainX.backend.Model.Notification;
import com.FarmChainX.backend.Repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Create notification
    public Notification createNotification(Notification notification) {
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        return notificationRepository.save(notification);
    }

    // Get all notifications for user
    public List<Notification> getUserNotifications(String userId, String role) {
        return notificationRepository
                .findByUserIdAndUserRoleOrderByCreatedAtDesc(userId, role);
    }

    // Get unread notifications
    public List<Notification> getUnreadNotifications(String userId, String role) {
        return notificationRepository
                .findByUserIdAndUserRoleAndIsReadFalse(userId, role);
    }

    // Count unread
    public long getUnreadCount(String userId, String role) {
        return notificationRepository
                .countByUserIdAndUserRoleAndIsReadFalse(userId, role);
    }

    // Mark as read
    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notificationRepository.save(notification);
    }

    // Mark all as read
    public void markAllAsRead(String userId, String role) {
        List<Notification> notifications =
                notificationRepository.findByUserIdAndUserRoleAndIsReadFalse(userId, role);

        notifications.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(notifications);
    }

    // Delete old notifications (optional cron usage)
    public void deleteOlderThan(LocalDateTime date) {
        notificationRepository.deleteNotificationsOlderThan(date);
    }
}
