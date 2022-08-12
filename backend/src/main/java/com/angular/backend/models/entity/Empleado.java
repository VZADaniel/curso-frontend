package com.angular.backend.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre", nullable = false)
	@Size(min = 2, max = 50)
	private String nombre;

	@Column(name = "apellido", nullable = false)
	@Size(min = 2, max = 50)
	private String apellido;

	@Column(name = "email", unique = true)
	@NotEmpty
	@Email
	private String email;

	private String genero;

	@Column(name = "cargo")
	private String cargo;

	@Column(name = "sueldo")
	private Long sueldo;

	@Column(name = "fecha_contrato")
	@Temporal(TemporalType.DATE)
	@NotNull
	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaContrato;

	@PrePersist
	public void prePersist() {
		this.fechaContrato = new Date();
	}

	public Empleado() {
	}

	public Empleado(@Size(min = 2, max = 50) String nombre, @Size(min = 2, max = 50) String apellido,
			@NotEmpty @Email String email, String genero, String cargo, Long sueldo,
			@NotNull @PastOrPresent Date fechaContrato) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.genero = genero;
		this.cargo = cargo;
		this.sueldo = sueldo;
		this.fechaContrato = fechaContrato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Long getSueldo() {
		return sueldo;
	}

	public void setSueldo(Long sueldo) {
		this.sueldo = sueldo;
	}

	public Date getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

}
