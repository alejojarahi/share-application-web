import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import all components used in this application for the routing
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './User/login/login.component';
import { RegisterComponent } from './User/register/register.component';
import { UserListComponent } from './Administrador/user-list/user-list.component';
import { UserAddComponent } from './Administrador/user-add/user-add.component';
import { UserEditComponent } from './Administrador/user-edit/user-edit.component';

// Add all components used in this project for the routing and print in the route a component
const routes: Routes = [
  {path: '', component: HomeComponent},               // Route home of the application
  {path: 'login', component: LoginComponent},         // Route for that the users not logged is login in the application
  {path: 'register', component: RegisterComponent},   // Route for that the users not logged is register in the application
  {path: 'listUser', component: UserListComponent},   // Route for the show list all users registed in the aplication
  {path: 'addUser', component: UserAddComponent},     // Route for that the administrator manual insert new users
  {path: 'editUser', component: UserEditComponent},   // Route for that the administrador manual edit users
  {path: '**', redirectTo: '', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // Array of all routes used in the application
  exports: [RouterModule]
})

export class AppRoutingModule { }
