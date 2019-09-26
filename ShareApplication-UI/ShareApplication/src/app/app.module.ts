import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module'; // Routing for used multiple routes in the application
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'; // Model for used request http

// Forms use functions in html and components for 'way binding'
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Principal component
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

// List components for view Administrator users
import { UserListComponent } from './Administrador/user-list/user-list.component';
import { UserEditComponent } from './Administrador/user-edit/user-edit.component';

// Login and register system for news users
import { LoginComponent } from './User/login/login.component';
import { RegisterComponent } from './User/register/register.component';

// Configurate services used in the application
import { UserService } from './Service/user.service';
import { AuthService } from './Service/Auth/auth.service';
import { TokenService } from './Service/Auth/token.service';
import { SessionService } from './Service/session.service';
import { InterceptorService} from './Service/interceptor.service';

// Configure directives
import { PasswordValidationDirective } from './Validations/directive/password-validation.directive';

@NgModule({
  declarations: [ // names of components used in this application
    AppComponent,
    UserListComponent,
    UserEditComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    PasswordValidationDirective // Directive password validation
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, // Usted the directive route for the routing of components
    FormsModule,      // Used the directive imported NgModel
    ReactiveFormsModule, // Usted the directive for form with FormBuilder (The logic is in the controller)
    HttpClientModule  // Used the CRUD connection with the database
  ],
  providers: [ // Registed services for used in all components of the application
    /*UserService,
    AuthService,
    TokenService,
    SessionService,*/
    { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true } // Service for add the token in all the http requests
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
