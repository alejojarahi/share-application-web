import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module'; // Routing for used multiple routes in the application
import { HttpClientModule } from '@angular/common/http'; // Model for used request http

// Forms use functions in html and components for 'way binding'
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

// Principal component
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

// List components for view Administrator users
import { UserListComponent } from './Administrador/user-list/user-list.component';
import { UserAddComponent } from './Administrador/user-add/user-add.component';
import { UserEditComponent } from './Administrador/user-edit/user-edit.component';

// Login and register system for news users
import { LoginComponent } from './User/login/login.component';
import { RegisterComponent } from './User/register/register.component';

// Configurate services used in the application
import { UserService } from './Service/user.service';

@NgModule({
  declarations: [ // names of components used in this application
    AppComponent,
    UserListComponent,
    UserAddComponent,
    UserEditComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, // Usted the directive route for the routing of components
    FormsModule,      // Used the directive imported NgModel
    HttpClientModule  // Used the CRUD connection with the database
  ],
  providers: [ // Registed services for used in all components of the application
    UserService
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
