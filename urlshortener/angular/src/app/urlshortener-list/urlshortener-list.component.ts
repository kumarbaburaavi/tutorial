import { Component, OnInit } from '@angular/core';
import { Urlshortener } from '../model/urlshortener';
import { UrlshortenerService } from '../service/urlshortener.service';

@Component({
  selector: 'app-urlshortener-list',
  templateUrl: './urlshortener-list.component.html',
  styleUrls: ['./urlshortener-list.component.css']
})
export class UrlshortenerListComponent implements OnInit {

  urlshorteners: Urlshortener[];

  constructor(private urlshortenerService: UrlshortenerService) {

  }

  ngOnInit() {
    this.urlshortenerService.findAll().subscribe(data => {
      this.urlshorteners = data;
    });
  }
}
