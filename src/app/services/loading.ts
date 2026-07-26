import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  isLoading$ = this.loadingSubject.asObservable();

  show(): void {
    // Defer execution to the next tick to prevent ExpressionChangedAfterItHasBeenCheckedError
    setTimeout(() => {
      this.loadingSubject.next(true);
    });
  }

  hide(): void {
    setTimeout(() => {
      this.loadingSubject.next(false);
    });
  }
}
