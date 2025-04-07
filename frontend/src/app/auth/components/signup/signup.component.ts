import { Component } from '@angular/core';
import {NgClass} from '@angular/common';
import {FormGroup, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-signup',
  imports: [
    NgClass,
    ReactiveFormsModule
  ],
  templateUrl: './signup.component.html',
  standalone: true,
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  loading: any;
  // @ts-ignore
  submitted: (() => Error[]) | number | Array<RuleTester.TestCaseError | string> | jasmine.errors | ValidationError[] | any[] | Errors;
  signupForm: FormGroup | undefined;

  onSubmit() {

  }
}
