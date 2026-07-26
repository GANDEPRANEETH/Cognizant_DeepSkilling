import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, combineLatest } from 'rxjs';
import { map } from 'rxjs/operators';
import { Store } from '@ngrx/store';

import { CourseCard } from '../../components/course-card/course-card';
import { Highlight } from '../../directives/highlight';
import { Course } from '../../models/course.model';
import { loadCourses } from '../../store/course/course.actions';
import { selectAllCourses, selectCoursesError, selectCoursesLoading } from '../../store/course/course.selectors';
import { EnrollmentService } from '../../services/enrollment';

@Component({
  selector: 'app-course-list',
  imports: [CommonModule, FormsModule, CourseCard, Highlight],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css'
})
export class CourseList implements OnInit {
  isLoading$!: Observable<boolean>;
  errorMessage$!: Observable<string | null>;
  courses$!: Observable<Course[]>;
  selectedCourseId: number | null = null;
  searchTerm = '';

  constructor(
    private store: Store,
    public enrollmentService: EnrollmentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const querySearch = this.route.snapshot.queryParamMap.get('search');
    if (querySearch) {
      this.searchTerm = querySearch;
    }

    // Task 9 Step 96: Dispatch NgRx Load Action
    this.store.dispatch(loadCourses());

    this.isLoading$ = this.store.select(selectCoursesLoading);
    this.errorMessage$ = this.store.select(selectCoursesError);

    // Map store course list and apply local search filter reactively
    this.courses$ = combineLatest([
      this.store.select(selectAllCourses),
      this.route.queryParamMap
    ]).pipe(
      map(([courses, queryParams]) => {
        const search = queryParams.get('search') || '';
        this.searchTerm = search;
        if (!search) {
          return courses;
        }
        return courses.filter(c =>
          c.name.toLowerCase().includes(search.toLowerCase()) ||
          c.code.toLowerCase().includes(search.toLowerCase())
        );
      })
    );
  }

  onSearchChange(): void {
    this.router.navigate(['/courses'], {
      queryParams: { search: this.searchTerm || null },
      queryParamsHandling: 'merge'
    });
  }

  trackByCourseId(index: number, course: Course): number {
    return course.id;
  }

  onEnroll(courseId: number): void {
    console.log('Enroll action triggered for course ID:', courseId);
    this.selectedCourseId = courseId;
  }
}
