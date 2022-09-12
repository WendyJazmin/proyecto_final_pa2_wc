package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.ReporteReservas;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IGestorReportesService;
import com.uce.edu.demo.service.IGestorReservaVehiculoService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	private IGestorReportesService gestorReportesService;
	@Autowired
	private IGestorReservaVehiculoService gestorReservaVehiculoService;
	
	@Autowired
	private IVehiculoService vehiculoService;
	
	@Autowired
	private IReservaService reservaServiceImpl;
	
	// Vista para la pagina donde se buscara las reservas
	@GetMapping("/buscarReservas")
	public String paginaPrincipal() {
		return "vistaReservasBuscadas";
	}

	// Vista para la pagina donde se reservara vehiculos
	@GetMapping("/reservarVehiculo")
	public String reservarVehiculo(Cliente cliente, Vehiculo vehiculo, Reserva reserva, Cobro cobro) {
		return "vistaReservarVehiculos";
	}

//************************************************************************************************************************************
	//Pagina donde se pone el rango de fechas del reporte de las reservas
	@GetMapping("/reservaReporte")
	public String reporteReservasParamertros(Model modelo) {
		return "vistaReporteReservas";
	}
	
	//Pagina donde se proyectara los reportes de las reservas
	@GetMapping("/listaReportes")
	public String reporteReservas(
			@RequestParam("fechaI") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaI, 
			@RequestParam("fechaF") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaF,
			Model modelo) {
		List<ReporteReservas> reporteReservas=this.gestorReportesService.buscarReservasFechas(fechaI, fechaF);
		modelo.addAttribute("reportes",reporteReservas);
		return "vistaListaReportes";
	}
	
//************************************************************************************************************************************
	@PostMapping("/insertar")
	public String nuevaReserva(Cliente cliente, Vehiculo vehiculo, Reserva reserva, Cobro cobro, Model modelo) {
		String informacion =this.gestorReservaVehiculoService.reservarVehiculo(vehiculo.getPlaca(), cliente.getCedula(), reserva.getFechaInicio(), reserva.getFechaFin(), cobro.getTarjetaCredito());
		modelo.addAttribute("texto",informacion);
		return "vistaReservarProceso";
	}
	
	@GetMapping("/verificarReservas")
	public String redireccionar(Cliente cliente, Vehiculo vehiculo, Reserva reserva, Cobro cobro) {
		if(this.reservaServiceImpl.buscarPorFechaPlaca(reserva.getFechaInicio(), reserva.getFechaFin(), vehiculo.getPlaca()).size()>0) {
			return "redirect:/reserva/reservarVehiculo";
		}else {
			return "vistaTarjetaCredito";
		}
		
	}
	
	@GetMapping("/buscarReservaNumero")
	public String vistaBuscarPorNumero(Reserva modelo) {
		return "vistaBuscarPorNumero";
	}
	
	@GetMapping("/reservaEncontrada")
	public String reserbaEncontrada(@RequestParam("numero") String numero, Model modelo) {
		Reserva reserva= this.reservaServiceImpl.buscarPorNumero(numero);
		Vehiculo vehiculo = reserva.getVehiculo();
		Cliente cliente = reserva.getCliente();
		modelo.addAttribute("reserva", reserva);
		modelo.addAttribute("vehiculo", vehiculo);
		modelo.addAttribute("cliente", cliente);
		return "vistaReservaEncontrada";
	}
	
	@PutMapping("/retirarVehiculoReservado")
	public String retirarVehiculoReservado(@RequestParam("numero") String numero) {
		System.out.println(numero);
		this.gestorReservaVehiculoService.retirarVehiculoReservado(numero);
		return "redirect:/gestorPaginas/paginaEmpleado";
	}
	
//************************************************************************************************************************************
	@PutMapping("/regresarVehiculo")
	public String regresarVehiculo(@RequestParam("numero") String numero) {
		this.gestorReservaVehiculoService.regresarVehiculoReservado(numero);
		return "redirect:/gestorPaginas/paginaEmpleado";
	}
	
//************************************************************************************************************************************
	//Retirar un vehiculo sin reserva
	@GetMapping("/buscarSinReserva")
	public String vistaBuscarSinReserva(Vehiculo vehiculo) {
		return "VistaBuscarSinReserva";
	}
	
	@GetMapping("/listaVehiculos")
	public String devolverVehiculosBuscados(Vehiculo vehiculo, Model model) {
		List<Vehiculo> listaVehiculos = this.vehiculoService.buscarVehiculos(vehiculo.getMarca(), vehiculo.getModelo());
		model.addAttribute("vehiculos", listaVehiculos);
		return "vistaListaVehiculosSinReserva";
	}
	
	@GetMapping("/reservarVehiculoSinRes/{placa}")
	public String reservarVehiculoSinRes(@PathVariable("placa") String placa,Cliente cliente, Vehiculo vehiculo, Reserva reserva, Cobro cobro) {
		return "vistaReservarVehiculosSinRes";
	}
	
	@GetMapping("/verificarReservasSinRes")
	public String redireccionarSinRes(Cliente cliente, Vehiculo vehiculo, Reserva reserva, Cobro cobro) {
		if(this.reservaServiceImpl.buscarPorFechaPlaca(reserva.getFechaInicio(), reserva.getFechaFin(), vehiculo.getPlaca()).size()>0) {
			return "redirect:/reserva/reservarVehiculoSinRes/"+vehiculo.getPlaca();
		}else {
			return "vistaTarjetaCreditoSinRes";
		}
	}
	
	@PostMapping("/insertarSinRes")
	public String nuevaReservaSinRes(Cliente cliente, Vehiculo vehiculo, Reserva reserva, Cobro cobro) {
		this.gestorReservaVehiculoService.reservarVehiculo(vehiculo.getPlaca(), cliente.getCedula(), reserva.getFechaInicio(), reserva.getFechaFin(), cobro.getTarjetaCredito());
		return "redirect:/reserva/buscarReservaNumeroSinRes";
	}
	
	@GetMapping("/buscarReservaNumeroSinRes")
	public String vistaBuscarPorNumeroSinRes(Model modelo, Reserva reserva) {
		System.out.println();
		reserva = this.reservaServiceImpl.buscarPorNumero(this.reservaServiceImpl.numeroReserva());
		modelo.addAttribute("reserva", reserva);
		return "vistaBuscarPorNumeroSinRes";
	}
}
