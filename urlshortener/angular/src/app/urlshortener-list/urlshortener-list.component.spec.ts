import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UrlshortenerListComponent } from './urlshortener-list.component';

describe('UrlshortenerListComponent', () => {
  let component: UrlshortenerListComponent;
  let fixture: ComponentFixture<UrlshortenerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UrlshortenerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UrlshortenerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
