import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NotificationService } from '../../services/notification';

@Component({
  selector: 'app-notification',
  imports: [CommonModule, FormsModule],
  providers: [NotificationService], // Task 6 Step 67: Component-level provider
  templateUrl: './notification.html',
  styleUrl: './notification.css'
})
export class Notification {
  newMsg = '';

  /*
    WHY COMPONENT-LEVEL PROVIDING CREATES A SEPARATE INSTANCE:
    When we list a service in the `providers` array of a component's decorator,
    Angular's Dependency Injection (DI) system creates a new instance of this service
    specifically for this component's injector instance.
    - Each instance of this component will get its own private, isolated instance of the service.
    - Any child components of this component will share this scoped service instance (hierarchical DI).
    - It is NOT shared with sibling components or components in other parts of the application tree,
      unlike services provided in 'root' which are singletons shared globally.
  */
  constructor(public notificationService: NotificationService) {}

  add(): void {
    if (this.newMsg.trim()) {
      this.notificationService.addNotification(this.newMsg);
      this.newMsg = '';
    }
  }
}
