package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;

public class VehiculoVIP {
	private String placa;
	
	private String modelo;
	
	private String marca;
	
	private String anioFabricacion;
	
	private String paisFabricacion;
	
	private String estado;
	
	private BigDecimal subTotal;
	
	private BigDecimal valorTotal;

	public VehiculoVIP(String placa, String modelo, String marca, String anioFabricacion, String paisFabricacion,
			String estado, BigDecimal subTotal, BigDecimal valorTotal) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anioFabricacion = anioFabricacion;
		this.paisFabricacion = paisFabricacion;
		this.estado = estado;
		this.subTotal = subTotal;
		this.valorTotal = valorTotal;
	}

	//To String
	@Override
	public String toString() {
		return "VehiculoVIP [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", anioFabricacion="
				+ anioFabricacion + ", paisFabricacion=" + paisFabricacion + ", estado=" + estado + ", subTotal="
				+ subTotal + ", valorTotal=" + valorTotal + "]";
	}

	//GET y SET
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(String anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public String getPaisFabricacion() {
		return paisFabricacion;
	}

	public void setPaisFabricacion(String paisFabricacion) {
		this.paisFabricacion = paisFabricacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
