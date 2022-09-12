package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class ClienteVIP {
	
	private String cedula;

	private String nombre;
	
	private String apellido;
	
	private String registro;
	
	private BigDecimal valorIVA;
	
	private BigDecimal valorTotal;
	
	public ClienteVIP() {
		// TODO Auto-generated constructor stub
	}

	
	
	public ClienteVIP(String cedula, String nombre, String apellido, String registro, BigDecimal valorIVA,
			BigDecimal valorTotal) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.registro = registro;
		this.valorIVA = valorIVA;
		this.valorTotal = valorTotal;
	}
	
	@Override
	public String toString() {
		return "ClienteVIP [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", registro="
				+ registro + ", valorIVA=" + valorIVA + ", valorTotal=" + valorTotal + "]";
	}

	//SET y GET
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public BigDecimal getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(BigDecimal valorIVA) {
		this.valorIVA = valorIVA;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
