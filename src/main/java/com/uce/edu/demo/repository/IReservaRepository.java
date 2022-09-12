package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.ReporteReservas;
import com.uce.edu.demo.repository.modelo.Reserva;

public interface IReservaRepository {
	
	//CRUD
	public void ingresar(Reserva reserva);
	public Reserva buscarID(Integer id);
	public void actualizar(Reserva reserva);

	public Reserva buscarPorNumero(String numero);

	public List<Reserva> buscarPorFechaPlaca(LocalDateTime inicio, LocalDateTime fin, String placa);

	public List<Reserva> buscarTodos();

	//Reporte
	public List<ReporteReservas> buscarReservasFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
