import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export const errorHandlerInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401) {
        console.warn('Unauthorized request - redirecting to home...');
        router.navigate(['/']);
      } else if (error.status === 500) {
        console.error('Internal Server Error (500) occurred globally.');
        alert('Global Alert: Internal Server Error occurred. Please try again later.');
      }
      return throwError(() => error);
    })
  );
};
