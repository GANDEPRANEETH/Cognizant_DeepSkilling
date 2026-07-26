import { Highlight } from './highlight';
import { ElementRef, Renderer2 } from '@angular/core';

describe('HighlightDirective', () => {
  it('should create an instance', () => {
    const mockEl = {} as ElementRef;
    const mockRenderer = {} as Renderer2;
    const directive = new Highlight(mockEl, mockRenderer);
    expect(directive).toBeTruthy();
  });
});
