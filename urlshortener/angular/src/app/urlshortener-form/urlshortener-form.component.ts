import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UrlshortenerService } from '../service/urlshortener.service';
import { Urlshortener } from '../model/urlshortener';

@Component({
  selector: 'app-urlshortener-form',
  templateUrl: './urlshortener-form.component.html',
  styleUrls: ['./urlshortener-form.component.css']
})
export class UrlshortenerFormComponent {
	
  urlshortener: Urlshortener;

  constructor(private route: ActivatedRoute, private router: Router, private urlshortenerService: UrlshortenerService) {
    this.urlshortener = new Urlshortener();
  }

  onSubmit() {
    this.urlshortenerService.save(this.urlshortener).subscribe(result => this.gotoUrlList());
  }

  gotoUrlList() {
    this.router.navigate(['/shorten-url/api/v1/geturls']);
  }
}
