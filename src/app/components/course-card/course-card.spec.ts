import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { SimpleChange } from '@angular/core';

import { CourseCard } from './course-card';

describe('CourseCard', () => {
  let component: CourseCard;
  let fixture: ComponentFixture<CourseCard>;
  let store: MockStore;
  const initialState = { enrollment: { enrolledCourseIds: [] } };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseCard],
      providers: [
        provideMockStore({ initialState })
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseCard);
    component = fixture.componentInstance;
    store = TestBed.inject(MockStore);
  });

  // Step 102: should create test
  it('should create', () => {
    component.course = { id: 1, name: 'Test Course', code: 'TEST101', credits: 3, gradeStatus: 'passed' };
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  // Step 103: should render @Input course data
  it('should display course name when course Input is set', () => {
    component.course = { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' };
    fixture.detectChanges();
    
    const titleEl = fixture.debugElement.query(By.css('.course-title')).nativeElement;
    expect(titleEl.textContent).toContain('Data Structures');
  });

  // Step 104: should emit @Output enrollRequested event on button click
  it('should emit enrollRequested with course.id on action click', () => {
    component.course = { id: 1, name: 'Data Structures', code: 'CS101', credits: 4, gradeStatus: 'passed' };
    fixture.detectChanges();

    spyOn(component.enrollRequested, 'emit');

    const button = fixture.debugElement.query(By.css('.btn-action')).nativeElement;
    button.click();

    expect(component.enrollRequested.emit).toHaveBeenCalledWith(1);
  });

  // Step 105: should log previous/current values in ngOnChanges
  it('should log previous and current values in ngOnChanges', () => {
    spyOn(console, 'log');

    const oldCourse = { id: 1, name: 'Old Name', code: 'CS101', credits: 3, gradeStatus: 'passed' as const };
    const newCourse = { id: 1, name: 'New Name', code: 'CS101', credits: 3, gradeStatus: 'passed' as const };

    component.course = newCourse;
    component.ngOnChanges({
      course: new SimpleChange(oldCourse, newCourse, false)
    });

    expect(console.log).toHaveBeenCalledWith('Course changed:', {
      previous: oldCourse,
      current: newCourse
    });
  });
});
