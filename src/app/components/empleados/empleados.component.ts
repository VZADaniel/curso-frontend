import { Component, Input, OnInit } from '@angular/core';
import { EmpleadosService } from 'src/app/services/empleados.service';
import { Empleado } from 'src/app/models/empleado';
import Swal from 'sweetalert2';

@Component({
	selector: 'app-empleados',
	templateUrl: './empleados.component.html',
	styleUrls: ['./empleados.component.css'],
})
export class EmpleadosComponent implements OnInit {
	@Input() empleados: Empleado[] = [];
	@Input() mensaje: string = '';
	titulo: string = 'Listado Empleados';

	optionSort: { property: string | null; order: string } = {
		property: null,
		order: 'asc',
	};

	constructor(private empleadoService: EmpleadosService) {}

	ngOnInit(): void {
		this.obtenerEmpleados();
	}

	obtenerEmpleados(): void {
		this.empleadoService.getEmpleados().subscribe((data) => {
			this.empleados = data.empleados;
			this.mensaje = data.mensaje;
		});
	}

	ordenarListadoEmpleados(property: string): void {
		const { order } = this.optionSort;
		this.optionSort = {
			property,
			order: order === 'asc' ? 'desc' : 'asc',
		};
		console.log(this.optionSort);
	}

	eliminarEmpleado(empleado: Empleado): void {
		const swalWhitBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-primary',
				cancelButton: 'btn btn-danger'
			}
		})

		swalWhitBootstrapButtons.fire({
			title: 'Estas seguro?',
			text: `Seguro que desea eliminar al empleado ${empleado.nombre} ${empleado.apellido}`,
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: 'Si, eliminar',
			cancelButtonText: "Me arrepiento!"
		}).then((result) => {
			if (result.isConfirmed) {
				this.empleadoService.deleteEmpleado(empleado.id).subscribe(response => {
					this.empleados = this.empleados.filter(emp => emp.id != empleado.id);
					swalWhitBootstrapButtons.fire('Eliminado!', response.mensaje, 'success');
				})
			}
		});
	}
}
