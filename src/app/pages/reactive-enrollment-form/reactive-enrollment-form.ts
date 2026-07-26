import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors } from '@angular/forms';
import { CourseService } from '../../services/course';

// Task 5 Step 53: Custom Synchronous Validator
export function noCourseCode(control: AbstractControl): ValidationErrors | null {
  const value = String(control.value || '');
  if (value.toUpperCase().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

// Task 5 Step 55: Custom Async Validator simulating an API check
export function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise((resolve) => {
    setTimeout(() => {
      const email = String(control.value || '');
      if (email.toLowerCase().includes('test@')) {
        resolve({ emailTaken: true });
      } else {
        resolve(null);
      }
    }, 800);
  });
}

@Component({
  selector: 'app-reactive-enrollment-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reactive-enrollment-form.html',
  styleUrl: './reactive-enrollment-form.css'
})
export class ReactiveEnrollmentForm implements OnInit {
  enrollForm!: FormGroup;
  submitted = false;

  constructor(
    private fb: FormBuilder,
    private courseService: CourseService
  ) {}

  ngOnInit(): void {
    // Task 5 Step 49: Build FormGroup
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: new FormControl('', {
        validators: [Validators.required, Validators.email],
        asyncValidators: [simulateEmailCheck],
        updateOn: 'blur'
      }),
      courseId: ['', [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  addCourse(): void {
    this.additionalCourses.push(new FormControl('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    if (this.enrollForm.valid) {
      this.submitted = true;

      console.log('Form Submit value:', this.enrollForm.value);
      console.log('Form Submit getRawValue():', this.enrollForm.getRawValue());

      // Task 8 Step 81: Wire createCourse to enrollment form submit handler
      const formVal = this.enrollForm.getRawValue();
      const dummyCourse = {
        name: `Course for ${formVal.studentName}`,
        code: `REG${formVal.courseId}`,
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

  onReset(): void {
    this.enrollForm.reset({
      preferredSemester: 'Odd',
      agreeToTerms: false
    });
    this.additionalCourses.clear();
    this.submitted = false;
  }
}
