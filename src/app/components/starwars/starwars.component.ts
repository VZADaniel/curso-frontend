import { Component, OnInit } from '@angular/core';
import { StarwarsService } from 'src/app/services/starwars.service';

@Component({
	selector: 'app-starwars',
	templateUrl: './starwars.component.html',
	styleUrls: ['./starwars.component.css'],
})
export class StarwarsComponent implements OnInit {
	public people: Array<any> = [];

	constructor(private starwarsService: StarwarsService) {}

	ngOnInit(): void {
		return this.starwarsService
			.getPeople()
			.subscribe((people: any) => this.people = people.results);
	}
}
