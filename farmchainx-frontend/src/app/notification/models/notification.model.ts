export interface Notification {
  id: number;
  userId: string;
  userRole: string;
  title: string;
  message: string;
  notificationType: string;
  relatedTicketId?: string;
  ticketId?: number;
  read: boolean;
  createdAt: string;
}
