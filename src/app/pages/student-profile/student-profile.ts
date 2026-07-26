import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';

import { Course } from '../../models/course.model';
import { selectEnrolledCourses } from '../../store/enrollment/enrollment.selectors';
import { Notification } from '../../components/notification/notification';

@Component({
  selector: 'app-student-profile',
  imports: [CommonModule, Notification],
  templateUrl: './student-profile.html',
  styleUrl: './student-profile.css'
})
export class StudentProfile implements OnInit {
  studentInfo = {
    name: 'Jane Doe',
    email: 'jane.doe@university.edu',
    major: 'Computer Science & Engineering',
    gpa: 3.8
  };

  enrolledCourses$!: Observable<Course[]>;

  constructor(private store: Store) {}

  ngOnInit(): void {
    // Task 9 Step 99: Get enrolled courses using cross-slice selector
    this.enrolledCourses$ = this.store.select(selectEnrolledCourses);
  }
}
