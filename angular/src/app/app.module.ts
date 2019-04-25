import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { httpInterceptorProviders } from './auth-interceptor';
import { NewsApiComponent } from './news-api/news-api.component';
import { SearchHistoryComponent } from './search-history/search-history.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    NewsApiComponent,
    SignupComponent,
    LoginComponent,
    SearchHistoryComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [httpInterceptorProviders,AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
