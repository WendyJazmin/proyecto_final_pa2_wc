package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.modelo.Reserva;

@Service
public class ReservaServiceImpl implements IReservaService {
	
	@Autowired
	private IReservaRepository reservaRepository;

	@Override
	public Reserva buscarPorNumero(String numero) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarPorNumero(numero);
	}

	@Override
	public List<Reserva> buscarPorFechaPlaca(LocalDateTime inicio, LocalDateTime fin, String placa) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarPorFechaPlaca(inicio, fin, placa);
	}

	@Override
	public List<Reserva> buscarTodos() {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarTodos();
	}
	
	@Override
	public String numeroReserva() {
		// TODO Auto-generated method stub
		return String.valueOf(this.reservaRepository.buscarTodos().size()-1);
	}

}
