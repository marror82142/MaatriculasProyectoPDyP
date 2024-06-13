import { Injectable } from '@angular/core';
import {programa} from './programa';
import {of, Observable} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class programaService {
  private endpointUrl = 'http://localhost:8080/api/programas';
  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});
  constructor(private http: HttpClient ) { }

  getProgramas(): Observable<programa[]>{
    return this.http.get<programa[]>(this.endpointUrl);
  }

  getPrograma(codigo: number): Observable<programa>{
    return this.http.get<programa>(`${this.endpointUrl}/${codigo}`)
  }

  create(programa: programa): Observable<programa>{
    return this.http.post<programa>(this.endpointUrl, programa, {headers: this.httpHeaders});
  }

  search(programa: programa): Observable<programa>{
    return this.http.put<programa>(`${this.endpointUrl}/buscar/${programa.codigo}`, programa, {headers: this.httpHeaders})
  }
  update(programa: programa): Observable<programa>{
    return this.http.put<programa>(`${this.endpointUrl}/${programa.id}`, programa, {headers: this.httpHeaders})
  }

  delete(cedula: number): Observable<programa>{
    return this.http.delete<programa>(`${this.endpointUrl}/${cedula}`, {headers: this.httpHeaders})
  }
  
}
