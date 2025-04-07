import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/components/login/login.component';
import { SignupComponent } from './auth/components/signup/signup.component';
import { AppRoutingModule } from './app-routing.module';
import { DemoAngularMaterialModule } from './demoAngularMaterialModule';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    DemoAngularMaterialModule,
    AppComponent,
    LoginComponent,
    SignupComponent,
  ],
  providers: [],
  
})
export class AppModule { }
