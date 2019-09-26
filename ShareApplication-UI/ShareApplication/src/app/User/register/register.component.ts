import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';

import { map } from 'rxjs/operators';

import { AuthService } from 'src/app/Service/Auth/auth.service'; // Import the service  for used function of register users in database
import { passwordValidation } from 'src/app/Validations/directive/password-validation.directive';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  // Form of the register users
  formRegister: FormGroup;

  // Pattern validation
  emailPattern = '^[a-z0-9._%+-]+@unal.edu.co$';

  // Errors in the back-end
  isRegister = false;
  isRegisterFail = false;
  message = '';

  constructor(private authService: AuthService, public formBuilder: FormBuilder) {
    this.buildForm(); // Excecute the form
  }

  ngOnInit() {
  }

  // Validations of form, sync validations and async validators, this validations run with update blur
  buildForm() {

    this.formRegister = this.formBuilder.group({
      firstname:    ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
      lastname:     ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
      email:        ['', [Validators.required, Validators.email], [this.validateEmail.bind(this)]],
      document:     ['', [Validators.required], [this.validateDocument.bind(this)]],
      password:     ['', [Validators.required, passwordValidation()]],
      confirmation: [true]
    }, { updateOn: 'blur' });
  }

  saveUser() {
    this.authService.createNewUser(this.formRegister.value).subscribe(data => {
      this.isRegister = true;
      this.isRegisterFail = false;
      this.message = data.message;
    },
    (error: any) => { // Control errors in the register back-end
      this.isRegisterFail = true;
      this.isRegister = false;
      if (error.error.status === undefined) {
        this.message = error.error;
      } else {
        this.message = error.error.message;
      }

    });
    this.buildForm();
  }

  // Validation for check if exists email in database
  validateEmail(control: AbstractControl) {
    return this.authService.existsEmail(control.value).pipe(
      map(result => result ? { emailExists: true } : null)
    );
  }

  // Validation for check if exists document in database
  validateDocument(control: AbstractControl) {
    return this.authService.existsDocument(control.value).pipe(
      map(result => result ? { documentExists: true } : null)
    );
  }
}
