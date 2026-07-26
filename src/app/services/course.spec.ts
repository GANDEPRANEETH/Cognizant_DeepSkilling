import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CourseService } from './course';
import { Course } from '../models/course.model';

describe('CourseService', () => {
  let service: CourseService;
  let httpMock: HttpTestingController;

  const mockCourses: Course[] = [
    { id: 1, name: 'Course One', code: 'C1', credits: 3, gradeStatus: 'passed' },
    { id: 2, name: 'Course Two', code: 'C2', credits: 4, gradeStatus: 'pending' }
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CourseService]
    });
    service = TestBed.inject(CourseService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch all courses with getCourses()', () => {
    service.getCourses().subscribe(courses => {
      expect(courses.length).toBe(2);
      expect(courses).toEqual(mockCourses);
    });

    const req = httpMock.expectOne('http://localhost:3000/courses');
    expect(req.request.method).toBe('GET');
    req.flush(mockCourses);
  });

  it('should handle errors gracefully and propagate an error message after retries', () => {
    service.getCourses().subscribe({
      next: () => fail('Expected getCourses to fail with 500 error'),
      error: (err) => {
        expect(err.message).toBe('Failed to load courses. Please try again.');
      }
    });

    // 1st attempt
    const req1 = httpMock.expectOne('http://localhost:3000/courses');
    req1.flush('Failed to load', { status: 500, statusText: 'Internal Server Error' });
    
    // 2nd attempt (1st retry)
    const req2 = httpMock.expectOne('http://localhost:3000/courses');
    req2.flush('Failed to load', { status: 500, statusText: 'Internal Server Error' });

    // 3rd attempt (2nd retry)
    const req3 = httpMock.expectOne('http://localhost:3000/courses');
    req3.flush('Failed to load', { status: 500, statusText: 'Internal Server Error' });
  });
});
