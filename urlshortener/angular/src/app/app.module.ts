import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UrlshortenerService } from './service/urlshortener.service';
import { UrlshortenerFormComponent } from './urlshortener-form/urlshortener-form.component';
import { UrlshortenerListComponent } from './urlshortener-list/urlshortener-list.component';

@NgModule({
  declarations: [
    AppComponent,
    UrlshortenerFormComponent,
    UrlshortenerListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UrlshortenerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
