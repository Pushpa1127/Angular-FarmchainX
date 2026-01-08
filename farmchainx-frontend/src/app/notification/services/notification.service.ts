import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notification } from '../models/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private baseUrl = 'http://localhost:8080/api/notifications';

  constructor(private http: HttpClient) {}

  getNotifications(userId: string, role: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}/${userId}/${role}`);
  }

  getUnreadNotifications(userId: string, role: string): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}/${userId}/${role}/unread`);
  }

  getUnreadCount(userId: string, role: string): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/${userId}/${role}/count`);
  }

  markAsRead(id: number): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${id}/read`, {});
  }

  markAllAsRead(userId: string, role: string): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${userId}/${role}/read-all`, {});
  }
}
