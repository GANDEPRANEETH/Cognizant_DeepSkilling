import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import { Course } from '../../models/course.model';
import { selectAllCourses } from '../../store/course/course.selectors';

@Component({
  selector: 'app-course-summary-widget',
  imports: [CommonModule],
  templateUrl: './course-summary-widget.html',
  styleUrl: './course-summary-widget.css'
})
export class CourseSummaryWidget implements OnInit {
  courses$!: Observable<Course[]>;

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.courses$ = this.store.select(selectAllCourses);
  }
}
