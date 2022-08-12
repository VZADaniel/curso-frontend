import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, map, Observable, throwError } from 'rxjs';
import Swal from 'sweetalert2';
import { Empleado } from '../models/empleado';

@Injectable({
  providedIn: 'root'
})
export class EmpleadosService {

  private urlEndPoint: string = 'http://localhost:8080/api/empleados';

  private httpHeaders = new HttpHeaders({'Content-type' : 'application/json'});

  constructor(private http: HttpClient, private router: Router) { }


  getEmpleados() : Observable<any> {
    return this.http.get<any>(this.urlEndPoint);
  }

  getEmpleado(id: number): Observable<Empleado> {
    return this.http.get<Empleado>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate([`/empleados`]);
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    )
  }

  createEmpleado(empleado: Empleado): Observable<any> {
    return this.http.post(this.urlEndPoint, empleado, {headers: this.httpHeaders}).pipe(
      map((response: any) => response.empleado as Empleado),
      catchError(e => {
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }

  updateEmpleado(empleado: Empleado): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${empleado.id}`,empleado,{headers: this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }

  deleteEmpleado(id: number): Observable<Empleado> {
    return this.http.delete<Empleado>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.mensaje);
        Swal.fire(e.error.mensaje,e.error.error,'error');
        return throwError(e);
      })
    );
  }


}
