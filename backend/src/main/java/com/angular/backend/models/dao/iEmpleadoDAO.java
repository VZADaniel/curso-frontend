package com.angular.backend.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.angular.backend.models.entity.Empleado;

@Repository
public interface iEmpleadoDAO extends CrudRepository<Empleado, Long> {

	public List<Empleado> findByGenero(String genero);

	public List<Empleado> findByApellidoContainsAllIgnoreCase(String apellido);

	@Query("select e from Empleado e where e.apellido like %?1%")
	public List<Empleado> findByApellido(String apellido);

	public Empleado findByEmail(String email);
}
