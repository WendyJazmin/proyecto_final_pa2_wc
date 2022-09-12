package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.ReporteReservas;

public interface IGestorReportesService {
	
	public List<ReporteReservas> buscarReservasFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
