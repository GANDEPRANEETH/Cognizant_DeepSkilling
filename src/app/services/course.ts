import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Wait, let's make sure path is correct: @angular/common/http
import { Observable, throwError } from 'rxjs';
import { map, catchError, tap, retry } from 'rxjs/operators';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private apiUrl = 'http://localhost:3000/courses';

  constructor(private http: HttpClient) {}

  getCourses(): Observable<Course[]> {
    // Task 8 Step 79, 83, 84, 85, 86
    return this.http.get<Course[]>(this.apiUrl).pipe(
      retry(2), // Retry failed requests up to 2 times before throwing error
      /*
        WHY tap IS PREFERRED OVER map FOR SIDE EFFECTS:
        - `tap` is designed specifically for side effects (logging, debugging, triggering external actions). 
          It does not modify the emitted stream values.
        - `map` is meant for transforming the data stream. Using side effects inside `map` violates 
          the single responsibility principle of the operator and can cause unexpected bugs if the return value is accidentally mutated or omitted.
      */
      tap(courses => console.log('Courses loaded:', courses.length)),
      map(courses => courses.filter(c => c.credits > 0)), // Filter out courses with 0 credits
      catchError(err => {
        console.error('API Error:', err);
        return throwError(() => new Error('Failed to load courses. Please try again.'));
      })
    );
  }

  getCourseById(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.apiUrl}/${id}`);
  }

  createCourse(course: Omit<Course, 'id'>): Observable<Course> {
    return this.http.post<Course>(this.apiUrl, course);
  }

  updateCourse(course: Course): Observable<Course> {
    return this.http.put<Course>(`${this.apiUrl}/${course.id}`, course);
  }

  deleteCourse(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
