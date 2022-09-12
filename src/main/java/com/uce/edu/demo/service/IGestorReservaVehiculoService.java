package com.uce.edu.demo.service;

import java.time.LocalDateTime;

public interface IGestorReservaVehiculoService {
	
	public String reservarVehiculo(String placa, String cedula, LocalDateTime inicio, LocalDateTime fin, String tarjetaCredito);
	
	public void retirarVehiculoReservado(String numeroReserva);
	
	public void regresarVehiculoReservado(String numeroReserva);
	
}
