import {Component, OnInit} from '@angular/core';

import {matricula} from './matricula';
import {matriculaService} from './matricula.service';
import {usuarioService} from '../usuarios/usuario.service';
import {programaService} from '../programas/programa.service';

import swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import * as moment from 'moment';
import { usuario } from '../usuarios/usuario';
import { programa } from '../programas/programa';

import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
  selector: 'app-usuarios',
  templateUrl: './matricula.component.html'
})

export class matriculaComponent implements OnInit {
  public usuarioActual=JSON.parse(localStorage.getItem("usuarioActual"));
  matriculas: matricula[] = [];
  public matricula: matricula = new matricula;
  public matriculaEditar: matricula = null;
  estados:string[]=["Activo","Inactivo"];
  usuarios:usuario[]=[]
  programas:programa[]=[]
  

  selectUser:any;
  selectProg:any;
  usuario:String;
  programa:String;

  public title = "Crear Matricula";
  constructor(private matriculaService: matriculaService,
              private usuarioService: usuarioService,
              private programaService: programaService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(){
    this.matriculaService.getMatriculas().subscribe(
      matriculas => this.matriculas = matriculas
    ); 

    this.usuarioService.getUsuarios().subscribe(
      usuarios => this.usuarios = usuarios
    );
    
    this.programaService.getProgramas().subscribe(
      programas => this.programas = programas
    );
  }

  public create(): void{    
    if(this.matriculaEditar && this.matriculaEditar.usuario!=null){
        this.update();
    }else{
        console.log("1")
        if(this.matriculas.length > 0){
          this.matriculas.forEach(m => {
            console.log("w")
            if(m.usuario.cedula == this.selectUser.cedula && m.programa.codigo == this.selectProg.codigo){
              swal.fire('Error', `Matricula ya existente`, 'error')
            }else{
              console.log("3")
              this.matriculaService.create(this.matricula)
              .subscribe(matricula => {
                this.matriculaService.getMatriculas().subscribe(
                  matriculas => this.matriculas = matriculas
                );
                this.router.navigate(['/matriculas'])
                swal.fire('Nueva Matricula', `Matricula creada`, 'success')
              }
              );
            }
          });
        }else{
          this.matriculaService.create(this.matricula)
          .subscribe(matricula => {                             
            this.matriculaService.getMatriculas().subscribe(
              matriculas => this.matriculas = matriculas
            );
            this.router.navigate(['/matriculas'])
            swal.fire('Nueva Matricula', `Matricula creada`, 'success')
          }
          );    
        }
    }
  }

  update():void{
    this.matriculaService.update(this.matriculaEditar)
    .subscribe( matriculaEditar => {
      this.router.navigate(['/matriculas'])
      swal.fire('matricula actualizada', `usuario ${matriculaEditar.usuario.nombre} actualizado`, 'success')
    }
    )
    this.matriculaEditar =null;
  }

  edit(matriculaEditar: matricula):void{
    console.log("entro");
    console.log("matriculaEditar.id"+ matriculaEditar.id);
    console.log("matriculaEditar.usuario" + matriculaEditar.usuario);
    console.log("matriculaEditar.programa"+ matriculaEditar.programa);
    console.log("matriculaEditar.fechaMatricula" + matriculaEditar.fechaMatricula);
    console.log("matriculaEditar.valor" + matriculaEditar.valor);
    console.log("matriculaEditar.estado" + matriculaEditar.estado);
    this.matriculaEditar = matriculaEditar;
  }

  selectPrograma(event: Event): void{
    const target = event.target as HTMLInputElement;  
    console.log("selecciona : " + target.value +" "+ typeof(target.value));

    this.programas.find(p =>{
      if(p.codigo == this.selectProg.codigo){
        this.matricula.programa = p;
      }
    });

    console.log(this.matricula.programa.codigo);
    
  }
  selectUsuario(event: Event): void{
    const target = event.target as HTMLInputElement;  
    console.log("selecciona : " + this.selectUser.cedula +target+" "+ typeof(target.value));
    
    this.usuarios.find(u => {
      if(u.cedula == this.selectUser.cedula){
        this.matricula.usuario = u;
      }
    });

    console.log(this.matricula.usuario.cedula);
    
  }

  exportTable(): void{
    let d = new Date();
    let l = d.toLocaleDateString();
    var a:any = [];

    
    a.push([
      "Estado", 
      "Fecha matricula", 
      "Id", 
      "Nombre programa", 
      "Nombre Estudiante", 
      "Valor"]); 
    
    
    this.matriculas.forEach(function (value) {
      a.push([
        value.estado, 
        String(value.fechaMatricula), 
        String(value.id), 
        value.programa.nombre, 
        value.usuario.nombre, 
        String(value.valor)]); 
    });

    console.log(this.matriculas.length);
    console.log(a);

    var CsvString = '"sep=,"\r\n';
    a.forEach(function(RowItem, RowIndex) {
      RowItem.forEach(function(ColItem, ColIndex) {
        CsvString += ColItem + ',';
      });
      CsvString += "\r\n";
    });
    CsvString = "data:application/csv," + encodeURIComponent(CsvString);
    var x = document.createElement("A");
    x.setAttribute("href", CsvString );
    x.setAttribute("download","Matriculas_"+l+".csv");
    document.body.appendChild(x);
    x.click();
  }
}


