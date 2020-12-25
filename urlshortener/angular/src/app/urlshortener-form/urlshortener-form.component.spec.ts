import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UrlshortenerFormComponent } from './urlshortener-form.component';

describe('UrlshortenerFormComponent', () => {
  let component: UrlshortenerFormComponent;
  let fixture: ComponentFixture<UrlshortenerFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UrlshortenerFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UrlshortenerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
