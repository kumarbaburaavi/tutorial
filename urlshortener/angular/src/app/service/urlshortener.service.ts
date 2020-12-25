import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Urlshortener } from '../model/urlshortener';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UrlshortenerService {


  private shortnerUrl: string;
  private shortnerAdd: string;

  constructor(private http: HttpClient) {
    this.shortnerUrl = 'http://localhost:8080/shorten-url/api/v1/geturls';
    this.shortnerAdd = 'http://localhost:8080/shorten-url/api/v1/addurl';
  }

  public findAll(): Observable<Urlshortener[]> {
    return this.http.get<Urlshortener[]>(this.shortnerUrl);
  }

  public save(shortener: Urlshortener) {
    return this.http.post<Urlshortener>(this.shortnerAdd, shortener);
  }

}
