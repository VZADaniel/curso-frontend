import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '@auth0/auth0-angular';
import { EmpleadosComponent } from './components/empleados/empleados.component';
import { FormularioComponent } from './components/empleados/formulario/formulario.component';
import { HomeComponent } from './components/home/home.component';
import { Pagina404Component } from './components/pagina404/pagina404.component';
import { StarwarsComponent } from './components/starwars/starwars.component';

const routes: Routes = [
	{
		path: '',
		title: 'Home',
		component: HomeComponent
	},
	{ path: 'home', redirectTo: '' },
	{
		path: 'starwars',
		title: 'Star Wars',
		component: StarwarsComponent
	},
	{
		path: 'empleados',
		title: 'Empleados',
		component: EmpleadosComponent,
		canActivate: [AuthGuard]
	},
	{
		path: 'empleados/formulario',
		title: 'Empleados | Agregar',
		component: FormularioComponent,
		canActivate: [AuthGuard]
	},
	{
		path: 'empleados/formulario/:id',
		title: 'Empleados | Editar',
		component: FormularioComponent,
		canActivate: [AuthGuard]
	},
	{
		path: '404',
		title: 'Página no encontrada!',
		component: Pagina404Component,
	},
	{ path: '**', redirectTo: '404' },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule],
})
export class AppRoutingModule {}
