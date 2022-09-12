package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;



public class ReporteReservas {
	
	//campos de la reserva
	private Integer id;
	
	private String numero;
	
	private String estado;
	
	private LocalDateTime fechaInicio;
	
	private LocalDateTime fechaFin;
	
	//campos del cliente
	private String cedula;
	
	private String nombre;
	
	//campos del vehiculo
	private String placa;
	
	private String modelo;
	
	private BigDecimal avaluo;
	
	
	
	public ReporteReservas() {
		
	}

	public ReporteReservas(Integer id, String numero, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			String cedula, String nombre, String placa, String modelo, BigDecimal avaluo) {
		
		this.id = id;
		this.numero = numero;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cedula = cedula;
		this.nombre = nombre;
		this.placa = placa;
		this.modelo = modelo;
		this.avaluo = avaluo;
	}

	
	@Override
	public String toString() {
		return "ReporteReservas [id=" + id + ", numero=" + numero + ", estado=" + estado + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", cedula=" + cedula + ", nombre=" + nombre + ", placa="
				+ placa + ", modelo=" + modelo + ", avaluo=" + avaluo + "]";
	}
	

	//GET Y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(BigDecimal avaluo) {
		this.avaluo = avaluo;
	}
	
	
	
	
	
}
