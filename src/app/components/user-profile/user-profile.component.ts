import { Component, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';

@Component({
	selector: 'app-user-profile',
	templateUrl: './user-profile.component.html',
	styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent implements OnInit {
	user: any = {};
	constructor(public authService: AuthService) {}

	ngOnInit(): void {
		this.authService.user$.subscribe((success: any) => {
			this.user = success;
			console.log(this.user);
		});
	}
}
