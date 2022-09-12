package com.uce.edu.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
@SpringBootTest
@Transactional
@Rollback(true)
class IGestorReservaVehiculoServiceTest {
	
	@Autowired
	private IReservaRepository reservaRepository;
	
	@Autowired
	private IVehiculoService vehiculoService;
	
	@Autowired
	private IGestorReservaVehiculoService gestorReservaVehiculoService;

	@Test
	void testReservarVehiculo() {
		this.gestorReservaVehiculoService.reservarVehiculo("E48-ZP", "1258585236", LocalDateTime.of(2022, 2, 26, 0, 0),
				LocalDateTime.of(2022, 2, 2, 0, 0), "1404586358");
	}
	
	@Test
	void testRetirarVehiculoReservado() {
		this.gestorReservaVehiculoService.retirarVehiculoReservado("200");
	}

	@Test
	void testRegresarVehiculoReservado() {
		

		Reserva reservaInicio = this.reservaRepository.buscarPorNumero("200");
		Vehiculo vehiculoInicio = reservaInicio.getVehiculo();
		vehiculoInicio.setEstado("D");
		
		this.vehiculoService.actualizar(vehiculoInicio);
		
		Reserva reservaFin = this.reservaRepository.buscarPorNumero("200");
		Vehiculo vehiculoFin = reservaFin.getVehiculo();
		
		assertThat(vehiculoFin.getEstado()).isEqualTo(vehiculoInicio.getEstado());
	}
}
