package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cobro")
public class Cobro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cobro_id_seq")
	@SequenceGenerator(name = "cobro_id_seq", sequenceName = "cobro_id_seq", allocationSize = 1)
	@Column(name = "cob_id")
	private Integer id;
	
	@Column(name = "cob_sub_total")
	private BigDecimal subTotal;
	
	@Column(name = "cob_valor_iva")
	private BigDecimal valorlIVA;
	
	@Column(name = "cob_valor_total")
	private BigDecimal valorTotal;
	
	@Column(name = "cob_tarjeta_credito")
	private String tarjetaCredito;
	
	@Column(name = "cob_fecha_cobro", columnDefinition = "TIMESTAMP")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaCobro;

	//Coneccion con otras entidades
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cob_rese_id")
	private Reserva reserva;
	
	//To String
	@Override
	public String toString() {
		return "Cobro [id=" + id + ", subTotal=" + subTotal + ", valorlIVA=" + valorlIVA + ", valorTotal=" + valorTotal
				+ ", fechaCobro=" + fechaCobro + "]";
	}

	//SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public BigDecimal getValorlIVA() {
		return valorlIVA;
	}

	public void setValorlIVA(BigDecimal valorlIVA) {
		this.valorlIVA = valorlIVA;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(LocalDateTime fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getTarjetaCredito() {
		return tarjetaCredito;
	}

	public void setTarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = tarjetaCredito;
	}
	
}
