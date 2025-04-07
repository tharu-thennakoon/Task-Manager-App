import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/components/login/login.component';


const routes: Routes = [
    {path:"login", component: LoginComponent},
    {path:"signup",component: LoginComponent},
    {path:"Admin", loadChildren:()=>import("./modules/admin/admin.module").then(e => e.AdminModule)},
    {path:"Employee", loadChildren:()=>import("./modules/employee/employee.module").then(e => e.EmployeeModule)},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
    })
export class AppRoutingModule { }