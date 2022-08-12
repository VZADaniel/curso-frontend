package com.angular.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angular.backend.models.dao.iEmpleadoDAO;
import com.angular.backend.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements iEmpleadoService {

	@Autowired
	private iEmpleadoDAO empleadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findByApellido(String apellido) {
		return empleadoDao.findByApellidoContainsAllIgnoreCase(apellido);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findByGenero(String genero) {
		return empleadoDao.findByGenero(genero);
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Long id) {
		return empleadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empleadoDao.deleteById(id);
	}
}
