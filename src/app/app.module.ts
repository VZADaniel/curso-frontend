import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { UnderConstructionComponent } from './components/under-construction/under-construction.component';
import { Pagina404Component } from './components/pagina404/pagina404.component';
import { StarwarsComponent } from './components/starwars/starwars.component';
import { HttpClientModule } from '@angular/common/http';
import { EmpleadosComponent } from './components/empleados/empleados.component';
import { OrderListPipe } from './pipes/order-list.pipe';
import { TruncateLetterPipe } from './pipes/truncate-letter.pipe';
import { FormularioComponent } from './components/empleados/formulario/formulario.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { registerLocaleData } from '@angular/common';
import localeES from '@angular/common/locales/es-CL';

import { environment } from 'src/environments/environment';
import { AuthModule } from '@auth0/auth0-angular';
import { AuthButtonComponent } from './components/auth-button/auth-button.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

registerLocaleData(localeES, 'es');
@NgModule({
	declarations: [
		AppComponent,
		HeaderComponent,
		FooterComponent,
		HomeComponent,
		UnderConstructionComponent,
		Pagina404Component,
		StarwarsComponent,
		EmpleadosComponent,
		OrderListPipe,
		TruncateLetterPipe,
		FormularioComponent,
		AuthButtonComponent,
		UserProfileComponent,
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule,
		ReactiveFormsModule,
		FormsModule,
		AuthModule.forRoot(environment.auth0),
	],
	providers: [{ provide: LOCALE_ID, useValue: 'es-CL' }],
	bootstrap: [AppComponent],
})
export class AppModule {}
