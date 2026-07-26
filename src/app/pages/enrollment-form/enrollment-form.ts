import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { CourseService } from '../../services/course';

@Component({
  selector: 'app-enrollment-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './enrollment-form.html',
  styleUrl: './enrollment-form.css'
})
export class EnrollmentForm {
  studentName = '';
  studentEmail = '';
  courseId: number | null = null;
  preferredSemester = 'Odd';
  agreeToTerms = false;

  submitted = false;

  constructor(private courseService: CourseService) {}

  onSubmit(form: NgForm): void {
    if (form.valid) {
      console.log('Form Submitted!', form.value);
      this.submitted = true;

      // Task 8 Step 81: Wire createCourse to enrollment form submit handler
      const dummyCourse = {
        name: `Course for ${this.studentName}`,
        code: `REG${this.courseId || '99'}`,
        credits: 3,
        gradeStatus: 'pending' as const
      };

      this.courseService.createCourse(dummyCourse).subscribe({
        next: (createdCourse) => {
          console.log('Demo Course created successfully via API:', createdCourse);
        },
        error: (err) => {
          console.error('Error creating course:', err);
        }
      });
    }
  }

  onReset(form: NgForm): void {
    form.resetForm({
      preferredSemester: 'Odd',
      agreeToTerms: false
    });
    this.submitted = false;
  }
}
