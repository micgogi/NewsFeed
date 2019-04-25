import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './signup/signup.component';

import { LoginComponent } from './login/login.component';
import { NewsApiComponent } from './news-api/news-api.component';
import { SearchHistoryComponent } from './search-history/search-history.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [

  {
    path: 'signup',
    component: SignupComponent
  },

  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'news',
    component: NewsApiComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent

  }, {
    path: 'searchHistory',
    component: SearchHistoryComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'mic/admin',
    component: AdminComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
