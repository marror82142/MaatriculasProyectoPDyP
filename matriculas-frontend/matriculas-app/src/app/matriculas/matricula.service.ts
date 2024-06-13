import { Injectable } from '@angular/core';
import {matricula} from './matricula';
import {of, Observable} from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class matriculaService {
  private endpointUrl = 'http://localhost:8080/api/matriculas';
  private httpHeaders = new HttpHeaders({'Content-Type':'application/json'});
  constructor(private http: HttpClient ) { }

  getMatriculas(): Observable<matricula[]>{
    return this.http.get<matricula[]>(this.endpointUrl);
  }

  getMatricula(id: number): Observable<matricula>{
    return this.http.get<matricula>(`${this.endpointUrl}/${id}`)
  }

  create(matricula: matricula): Observable<matricula>{
    return this.http.post<matricula>(this.endpointUrl, matricula, {headers: this.httpHeaders});
  }

  search(matricula: matricula): Observable<matricula>{
    return this.http.put<matricula>(`${this.endpointUrl}/buscar/${matricula.id}`, matricula, {headers: this.httpHeaders})
  }
  update(matricula: matricula): Observable<matricula>{
    return this.http.put<matricula>(`${this.endpointUrl}/${matricula.id}`, matricula, {headers: this.httpHeaders})
  }

  delete(id: number): Observable<matricula>{
    return this.http.delete<matricula>(`${this.endpointUrl}/${id}`, {headers: this.httpHeaders})
  }
}
