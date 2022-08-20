package com.angular.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angular.backend.models.entity.Empleado;
import com.angular.backend.services.iEmpleadoService;

@RestController
@CrossOrigin
@RequestMapping("/api/empleados")
public class EmpleadoRestController {

	@Autowired
	private iEmpleadoService empleadoService;

	@GetMapping(value = "", produces = "application/json")
	public ResponseEntity<?> listAll() {
		List<Empleado> empleados = null;
		Map<String, Object> response = new HashMap<>();

		try {
			empleados = empleadoService.findAll();
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", ex.getMessage() + ":" + ex.getMostSpecificCause());

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (empleados.size() == 0) {
			response.put("mensaje", "No hay empleados registrados en la base de datos");
			response.put("empleados", empleados);

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		response.put("mensaje", "Actualmente la base de datos cuenta con " + empleados.size() + " registros");
		response.put("empleados", empleados);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();

		try {
			empleado = empleadoService.findById(id);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", ex.getMessage() + ":" + ex.getMostSpecificCause());

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (empleado == null) {
			response.put("mensaje", "El empelado ID: " + id + " no existen en nuestros registros");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		response.put("empleados", empleado);

		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);

	}

	@GetMapping(value = "/genero={genero}", produces = "application/json")
	public List<Empleado> findByGender(@PathVariable String genero) {
		List<Empleado> empleadosPorGenero = empleadoService.findByGenero(genero);

		return empleadosPorGenero;
	}

	@GetMapping(value = "/apellido/{apellido}", produces = "application/json")
	public List<Empleado> findByLastname(@PathVariable String apellido) {
		List<Empleado> empleadosPorApellido = empleadoService.findByApellido(apellido);

		return empleadosPorApellido;
	}

	@PostMapping(value = "", produces = "application/json")
	public ResponseEntity<?> create(@RequestBody Empleado empleado) {
		Empleado empleadoNuevo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			List<Empleado> empleados = empleadoService.findAll();
			Empleado empleadoDb = empleados.stream()
					.filter(empl -> empl.getEmail().equalsIgnoreCase(empleado.getEmail()))
					.findAny().orElse(null);
			if (empleadoDb != null) {
				response.put("mensaje",
						"Error al realizar el registro de un nuevo empleado. El correo ingresado ya existe");

				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}

			empleadoNuevo = empleadoService.save(empleado);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", ex.getMessage() + ":" + ex.getMostSpecificCause());

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		response.put("empleado", empleadoNuevo);
		response.put("mensaje", "El empleado ha"
				+ empleadoNuevo.getNombre().concat(" ").concat(empleadoNuevo.getApellido()) + " sido registrado");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> update(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado empleadoActual = empleadoService.findById(id);
		Empleado empleadoActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (empleadoActual == null) {
			response.put("mensaje",
					"No se puede editar. El empleado ID: " + id + " no se encuentra en nuestros registros.");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			empleado.setId(id);
			empleadoActualizado = empleadoService.save(empleado);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", ex.getMessage() + ":" + ex.getMostSpecificCause());

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El empleado ha sido actualizado con exito");
		response.put("empleado", empleadoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Empleado empleado = empleadoService.findById(id);
		Map<String, Object> response = new HashMap<>();

		if (empleado == null) {
			response.put("mensaje",
					"No se puede eliminar. El empleado ID: " + id + " no se encuentra en nuestros registros.");

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			empleadoService.delete(id);
		} catch (DataAccessException ex) {
			response.put("mensaje", "Error al realizar la consulta");
			response.put("error", ex.getMessage() + ":" + ex.getMostSpecificCause());

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Empleado eliminado exitosamente");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
