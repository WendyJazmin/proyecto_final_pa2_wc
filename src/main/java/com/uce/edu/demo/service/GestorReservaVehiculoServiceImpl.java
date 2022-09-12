package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@Service
public class GestorReservaVehiculoServiceImpl implements IGestorReservaVehiculoService {

	private static final Logger LOG = Logger.getLogger(GestorClienteServiceImpl.class.getName());

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private IReservaRepository reservaRepository;
	

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String reservarVehiculo(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin, String tarjetaCredito) {
		// TODO Auto-generated method stub
		
		if (reservaRepository.buscarPorFechaPlaca(inicio, fin, placa).size()>0) {
			
			
			LOG.info("El vehiculo no esta disponible en estas fechas");
			return "El vehiculo no esta disponible en estas fechas";
			
		} else {
			
			LOG.info("El vehiculo si esta disponible");

			LOG.info("Se encontro el vehiculo");
			Vehiculo vehiculo = this.vehiculoService.buscarPlaca(placa);
			LOG.info("Se encontro el vehiculo");
			Cliente cliente = this.clienteRepository.buscarCedula(cedula);
			Reserva reserva = new Reserva();
			Cobro cobro = new Cobro();

			Integer numDias = (int) ChronoUnit.DAYS.between(inicio, fin);
			
			cobro.setSubTotal(vehiculo.getValorDia().multiply(new BigDecimal(numDias)));
			cobro.setValorlIVA(cobro.getSubTotal().multiply(new BigDecimal(0.12)));
			cobro.setValorTotal(cobro.getSubTotal().add(cobro.getValorlIVA()));
			cobro.setReserva(reserva);
			cobro.setTarjetaCredito(tarjetaCredito);
			cobro.setFechaCobro(LocalDateTime.now());
			
			reserva.setCliente(cliente);
			reserva.setEstado("G");
			reserva.setFechaFin(fin);
			reserva.setFechaInicio(inicio);
			reserva.setVehiculo(vehiculo);
			reserva.setNumero(String.valueOf(this.reservaRepository.buscarTodos().size()));
			reserva.setCobro(cobro);
			
			LOG.info("Se inserto la reserva y el cobro");
			this.reservaRepository.ingresar(reserva);
			
			return "Su número de reserva es: "+reserva.getNumero();
		}

	}


	@Override
	@Transactional(value = TxType.REQUIRED)
	public void retirarVehiculoReservado(String numeroReserva) {
		// TODO Auto-generated method stub
		Reserva reserva = this.reservaRepository.buscarPorNumero(numeroReserva);
		Vehiculo vehiculo = reserva.getVehiculo();
				
		if(reserva != null) {
			if(reserva.getVehiculo().getEstado().equalsIgnoreCase("D")) {
				vehiculo.setEstado("ND");
				reserva.setEstado("E");
				LOG.info("Si se puede retirar el vehiculo");
				this.reservaRepository.actualizar(reserva);
				this.vehiculoService.actualizar(vehiculo);
			}else {
				LOG.info("No se puede retirar el vehiculo");
			}
		}
		
	}


	@Override
	@Transactional(value = TxType.REQUIRED)
	public void regresarVehiculoReservado(String numeroReserva) {
		// TODO Auto-generated method stub
		
		Reserva reserva = this.reservaRepository.buscarPorNumero(numeroReserva);
		Vehiculo vehiculo = reserva.getVehiculo();
		
		vehiculo.setEstado("D");
		this.vehiculoService.actualizar(vehiculo);
		LOG.info("El vehiculo fue regresado");
	}
	

}
