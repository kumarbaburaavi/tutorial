import { TestBed, inject } from '@angular/core/testing';

import { UrlshortenerService } from './urlshortener.service';

describe('UrlshortenerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UrlshortenerService]
    });
  });

  it('should be created', inject([UrlshortenerService], (service: UrlshortenerService) => {
    expect(service).toBeTruthy();
  }));
});
