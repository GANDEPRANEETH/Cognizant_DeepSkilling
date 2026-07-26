import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import { CourseService } from '../../services/course';
import { CourseSummaryWidget } from '../../components/course-summary-widget/course-summary-widget';
import { selectAllCourses } from '../../store/course/course.selectors';
import { loadCourses } from '../../store/course/course.actions';
import { Course } from '../../models/course.model';

@Component({
  selector: 'app-home',
  imports: [CommonModule, FormsModule, CourseSummaryWidget],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  courses$!: Observable<Course[]>;

  constructor(
    private store: Store,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    console.log('HomeComponent initialised — courses loaded');
    this.courses$ = this.store.select(selectAllCourses);
    // Dispatch loadCourses to populate the store initially
    this.store.dispatch(loadCourses());
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
    
    // Add a new course on the server to demonstrate shared singleton/API state (Task 6 Step 62)
    this.courseService.getCourses().subscribe(courses => {
      const newId = courses.length + 1;
      const dummyCourse = {
        name: `New Advanced Course ${newId}`,
        code: `CS${100 * newId}`,
        credits: 4,
        gradeStatus: 'pending' as const
      };

      this.courseService.createCourse(dummyCourse).subscribe(() => {
        // Re-dispatch load to update the store reactively
        this.store.dispatch(loadCourses());
      });
    }).unsubscribe();
  }
}
