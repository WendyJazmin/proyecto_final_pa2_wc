package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;

public interface IReservaService {


	public Reserva buscarPorNumero(String numero);

	public List<Reserva> buscarPorFechaPlaca(LocalDateTime inicio, LocalDateTime fin, String placa);

	public List<Reserva> buscarTodos();
	
	public String numeroReserva();
	
}
