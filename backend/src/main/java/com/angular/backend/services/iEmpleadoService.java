package com.angular.backend.services;

import java.util.List;

import com.angular.backend.models.entity.Empleado;

public interface iEmpleadoService {
	public List<Empleado> findAll();

	public Empleado save(Empleado empleado);

	public void delete(Long id);

	public Empleado findById(Long id);

	public List<Empleado> findByGenero(String genero);

	public List<Empleado> findByApellido(String apellido);
}
