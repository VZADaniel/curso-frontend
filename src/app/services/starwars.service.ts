import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root',
})
export class StarwarsService {
	private apiStarWars = 'https://swapi.dev/api/';

	constructor(private http: HttpClient) {
		console.log('Inicio Servicio Http');
	}

	getPeople(): any {
		return this.http.get(this.apiStarWars + 'people');
	}
}
