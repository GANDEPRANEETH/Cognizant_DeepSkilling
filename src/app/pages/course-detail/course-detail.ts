import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CourseService } from '../../services/course';
import { EnrollmentService } from '../../services/enrollment';
import { Course } from '../../models/course.model';
import { CreditLabelPipe } from '../../pipes/credit-label-pipe';

@Component({
  selector: 'app-course-detail',
  imports: [CommonModule, RouterModule, CreditLabelPipe],
  templateUrl: './course-detail.html',
  styleUrl: './course-detail.css'
})
export class CourseDetail implements OnInit {
  course: Course | undefined;

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService,
    public enrollmentService: EnrollmentService
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      const courseId = Number(idParam);
      this.courseService.getCourseById(courseId).subscribe({
        next: (course) => {
          this.course = course;
        },
        error: (err) => {
          console.error('Error fetching course detail:', err);
        }
      });
    }
  }

  toggleEnroll(): void {
    if (this.course) {
      const id = this.course.id;
      if (this.enrollmentService.isEnrolled(id)) {
        this.enrollmentService.unenroll(id);
      } else {
        this.enrollmentService.enroll(id);
      }
    }
  }
}
