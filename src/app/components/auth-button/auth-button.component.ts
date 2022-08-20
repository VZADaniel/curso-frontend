import { Component, Inject, OnInit } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { DOCUMENT } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
	selector: 'app-auth-button',
	templateUrl: './auth-button.component.html',
	styleUrls: ['./auth-button.component.css'],
})
export class AuthButtonComponent implements OnInit {
	constructor(
		@Inject(DOCUMENT) public document: Document,
		public auth: AuthService
	) {}

	ngOnInit(): void {}

	public login():void{
		this.auth.loginWithRedirect();
	}

	logout():void{
		this.auth.logout({ returnTo: document.location.origin })
	}
}
