import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

import { usuarioComponent } from './usuarios/usuario.component';
import { programaComponent } from './programas/programa.component';
import { matriculaComponent } from './matriculas/matricula.component';

import { usuarioService } from './usuarios/usuario.service';
import { programaService } from './programas/programa.service';
import { matriculaService } from './matriculas/matricula.service';


import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './usuarios/login.component';
import { PdfComponent } from './pdf/pdf.component';

const routes: Routes = [
  {path: '', component: LoginComponent, pathMatch:'full'},
  {path: 'login', component: LoginComponent},
  {path: 'usuarios', component: usuarioComponent},
  {path: 'programas', component: programaComponent},
  {path: 'matriculas', component: matriculaComponent},
  {path: 'pdf', component: PdfComponent},
  
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    usuarioComponent,
    LoginComponent,
    programaComponent,
    matriculaComponent,
    PdfComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,    
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [usuarioService,programaService,matriculaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
