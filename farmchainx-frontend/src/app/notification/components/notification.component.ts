import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../services/notification.service';
import { Notification } from '../models/notification.model';


@Component({
  selector: 'app-notifications',
  templateUrl: './notification.component.html'
})
export class NotificationComponent implements OnInit {

  notifications: Notification[] = [];
  unreadCount = 0;

  userId = '123';      // from auth
  role = 'FARMER';     // or DISTRIBUTOR

  constructor(private notificationService: NotificationService) {}

  ngOnInit(): void {
    this.loadNotifications();
    this.loadUnreadCount();
  }

  loadNotifications() {
    this.notificationService.getNotifications(this.userId, this.role)
      .subscribe(data => this.notifications = data);
  }

  loadUnreadCount() {
    this.notificationService.getUnreadCount(this.userId, this.role)
      .subscribe(count => this.unreadCount = count);
  }

  markAsRead(id: number) {
    this.notificationService.markAsRead(id).subscribe(() => {
      this.loadNotifications();
      this.loadUnreadCount();
    });
  }

  markAllAsRead() {
    this.notificationService.markAllAsRead(this.userId, this.role)
      .subscribe(() => {
        this.loadNotifications();
        this.loadUnreadCount();
      });
  }
}
