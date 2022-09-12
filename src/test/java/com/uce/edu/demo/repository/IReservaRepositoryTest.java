package com.uce.edu.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.ReporteReservas;
import com.uce.edu.demo.repository.modelo.Reserva;
@SpringBootTest
@Transactional
@Rollback(true)
class IReservaRepositoryTest {

	@Autowired
	private IReservaRepository reservaRepository;
	
	@Test
	void testIngresar() {
		
		Reserva reserva1=new Reserva();
		reserva1.setEstado("ND");
		reserva1.setFechaFin(LocalDateTime.of(2022, 10, 10, 0, 0));
		reserva1.setFechaInicio(LocalDateTime.of(2022, 11, 10, 0, 0));
		reserva1.setNumero("001");
		
		this.reservaRepository.ingresar(reserva1);
		Reserva reserva2= this.reservaRepository.buscarID(100);
		assertNotNull(reserva2);	
		
	}

	@Test
	void testBuscarID() {
		assertNotNull(this.reservaRepository.buscarID(100));
	}
	
	@Test
	void testBuscarReservasFechas() {
		List<ReporteReservas> reservas= this.reservaRepository.buscarReservasFechas(LocalDateTime.of(2021, 01, 01, 0, 0), LocalDateTime.of(2022, 02, 25, 0, 0));
		assertNotEquals(reservas.size(), 0);
	}
	@Test
	void testActualizar() {
		Reserva reserva1=this.reservaRepository.buscarID(100);
		reserva1.setEstado("ND");
		reserva1.setFechaFin(LocalDateTime.of(2022, 10, 10, 0, 0));
		reserva1.setFechaInicio(LocalDateTime.of(2022, 11, 10, 0, 0));
		reserva1.setNumero("001");
		this.reservaRepository.actualizar(reserva1);
		Reserva reserva2=this.reservaRepository.buscarID(100);
		assertEquals(reserva2.getNumero(),reserva1.getNumero());
	}

	@Test
	void testBuscarPorNumero() {
		
		Reserva reserva = this.reservaRepository.buscarPorNumero("200");
		assertNotNull(reserva);

	}

	@Test
	void testBuscarPorFechaPlaca() {
		
		List<Reserva> reservas = this.reservaRepository.buscarPorFechaPlaca(LocalDateTime.of(2022, 1, 6, 0, 0), LocalDateTime.of(2022, 1, 8, 0, 0), "AAA");
		assertNotNull(reservas);
	}

	@Test
	void testBuscarTodos() {
		List<Reserva> reservas = this.reservaRepository.buscarTodos();
		assertNotNull(reservas);
	}

}
