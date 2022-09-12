package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.modelo.ReporteReservas;

@Service
public class GestorReportesServiceImpl implements IGestorReportesService{
	
	private static final Logger LOG = Logger.getLogger(GestorClienteServiceImpl.class.getName());
	
	@Autowired
	private IReservaRepository reservaRepository;

	@Override
	public List<ReporteReservas> buscarReservasFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		LOG.info("Se realizao la consulta de reporte de las reservas");
		return this.reservaRepository.buscarReservasFechas(fechaInicio, fechaFin);
	}

}
