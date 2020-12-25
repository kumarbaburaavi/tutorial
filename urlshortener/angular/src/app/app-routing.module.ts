import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UrlshortenerListComponent } from './urlshortener-list/urlshortener-list.component';
import { UrlshortenerFormComponent } from './urlshortener-form/urlshortener-form.component';

const routes: Routes = [
  { path: 'shorten-url/api/v1/geturls', component: UrlshortenerListComponent },
  { path: 'shorten-url/api/v1/addurl', component: UrlshortenerFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
