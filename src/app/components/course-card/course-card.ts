import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';

import { CreditLabelPipe } from '../../pipes/credit-label-pipe';
import { enrollInCourse, unenrollFromCourse } from '../../store/enrollment/enrollment.actions';
import { selectEnrolledIds } from '../../store/enrollment/enrollment.selectors';

@Component({
  selector: 'app-course-card',
  imports: [CommonModule, CreditLabelPipe],
  templateUrl: './course-card.html',
  styleUrl: './course-card.css'
})
export class CourseCard implements OnInit, OnChanges {
  @Input() course!: {
    id: number;
    name: string;
    code: string;
    credits: number;
    gradeStatus: 'passed' | 'failed' | 'pending';
    enrolled?: boolean;
  };

  @Output() enrollRequested = new EventEmitter<number>();

  isExpanded = false;
  isEnrolled$!: Observable<boolean>;

  constructor(private store: Store) {}

  ngOnInit(): void {
    // Task 9 Step 100: Use selectEnrolledIds to check if this course is registered
    this.isEnrolled$ = this.store.select(selectEnrolledIds).pipe(
      map(ids => ids.includes(this.course.id))
    );
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course']) {
      const prev = changes['course'].previousValue;
      const curr = changes['course'].currentValue;
      console.log('Course changed:', {
        previous: prev,
        current: curr
      });
    }
  }

  toggleExpand(): void {
    this.isExpanded = !this.isExpanded;
  }

  // Getter for card classes
  get cardClasses() {
    return {
      'card--full': (this.course?.credits ?? 0) >= 4,
      'expanded': this.isExpanded
    };
  }

  // Dynamic border color style
  get borderStyle() {
    let color = '#64748b';
    if (this.course?.gradeStatus === 'passed') {
      color = '#10b981';
    } else if (this.course?.gradeStatus === 'failed') {
      color = '#ef4444';
    }
    return {
      'border-left': `6px solid ${color}`
    };
  }

  onEnroll(): void {
    const courseId = this.course.id;
    // Dispatch action to toggle enrollment state in store safely by taking only the first emission
    this.store.select(selectEnrolledIds).pipe(
      take(1)
    ).subscribe(ids => {
      if (ids.includes(courseId)) {
        this.store.dispatch(unenrollFromCourse({ courseId }));
      } else {
        this.store.dispatch(enrollInCourse({ courseId }));
      }
    });

    this.enrollRequested.emit(courseId);
  }
}
