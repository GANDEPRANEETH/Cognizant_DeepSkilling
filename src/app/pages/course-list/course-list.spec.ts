import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { provideMockStore, MockStore } from '@ngrx/store/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CourseList } from './course-list';
import { Course } from '../../models/course.model';

describe('CourseList', () => {
  let component: CourseList;
  let fixture: ComponentFixture<CourseList>;
  let store: MockStore;

  const mockCourses: Course[] = [
    { id: 1, name: 'Intro CS', code: 'CS101', credits: 3, gradeStatus: 'passed' },
    { id: 2, name: 'Data Structures', code: 'CS201', credits: 4, gradeStatus: 'pending' }
  ];

  const mockActivatedRoute = {
    snapshot: {
      queryParamMap: {
        get: () => null
      }
    },
    queryParamMap: of({ get: () => null })
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CourseList],
      providers: [
        provideMockStore({
          initialState: {
            course: {
              courses: mockCourses,
              loading: false,
              error: null
            },
            enrollment: {
              enrolledCourseIds: []
            }
          }
        }),
        { provide: ActivatedRoute, useValue: mockActivatedRoute }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(CourseList);
    component = fixture.componentInstance;
    store = TestBed.inject(MockStore);
  });

  it('should create', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });

  // Step 109: Verify cards render based on initial state
  it('should render course cards based on store state', () => {
    fixture.detectChanges();

    const cards = fixture.debugElement.queryAll(By.css('app-course-card'));
    expect(cards.length).toBe(2);
  });

  // Step 110: Verify loading indicator works on state change
  it('should display loading spinner when loading state is active', () => {
    // Set loading state to true
    store.setState({
      course: {
        courses: [],
        loading: true,
        error: null
      },
      enrollment: {
        enrolledCourseIds: []
      }
    });

    fixture.detectChanges();

    const spinner = fixture.debugElement.query(By.css('.loading-state'));
    expect(spinner).toBeTruthy();
  });
});
