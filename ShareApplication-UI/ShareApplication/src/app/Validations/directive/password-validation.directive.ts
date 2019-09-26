import { Directive } from '@angular/core';
import { Validators, AbstractControl, ValidatorFn, NG_VALIDATORS, ValidationErrors } from '@angular/forms';

@Directive({
  selector: '[appPasswordValidationDirective]',
  providers: [{provide: NG_VALIDATORS, useExisting: PasswordValidationDirective, multi: true}]
})
export class PasswordValidationDirective implements Validators {

  constructor() { }

  validate(control: AbstractControl): ValidationErrors {
    const password: string = control.value;

    // Conditional for validations lists of the password
    switch (password) {
      // Validation for password with lowercase
      case password.toLowerCase(): {
        return { LowerCase: true };
      }
      // Validation for password with uppercase
      case password.toUpperCase(): {
        return { toUpperCase: true };
      }
    }

    // Validation for password with numbers and letters
    if (!/\d/.test(password)) {
      return { format: true };
    }
    return null;
  }

}

export function passwordValidation(): ValidatorFn {
  return (control: AbstractControl) => {
    const passwordValidationDirective = new PasswordValidationDirective();
    return passwordValidationDirective.validate(control);
  };
}
