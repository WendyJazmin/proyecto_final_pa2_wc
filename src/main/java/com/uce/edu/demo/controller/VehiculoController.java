package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVIP;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {
	
	@Autowired
	private IVehiculoService vehiculoService;
	
//************************************************************************************************************************************
	//Vista para la pagina para buscar vehiculo
	@GetMapping("/buscarVehiculos")
	public String buscarVehiculos(Vehiculo vehiculo) {
		return "vistaBuscarVehiculos";
	}

	// Vista para la pagina de la lista de vehiculos disponibles
	@GetMapping("/listaVehiculos")
	public String devolverVehiculosBuscados(Vehiculo vehiculo, Model model) {
		List<Vehiculo> listaVehiculos = this.vehiculoService.buscarVehiculos(vehiculo.getMarca(), vehiculo.getModelo());
		model.addAttribute("vehiculos", listaVehiculos);
		return "vistaListaVehiculosDisponible";
	}
//************************************************************************************************************************************
	// Pagina donde se pone la fecha del reporte de los vehiculos
	@GetMapping("/reservaVehiculosVip")
	public String reporteReservasParamertros(Model modelo) {
		return "vistaReporteVehiculosVip";
	}

	// Pagina donde se proyectara los reportes de las reservas
	@GetMapping("/listaVehiculosVip")
	public String reporteReservas(
			@RequestParam("anio") String anio,
			@RequestParam("mes") String mes,
			Model modelo) {
		List<VehiculoVIP> vehiculoVIPs = this.vehiculoService.reporteVehiculosVip(anio, mes);
		modelo.addAttribute("vehiculos", vehiculoVIPs);
		return "vistaListaVehiculosVip";
	}

	
//************************************************************************************************************************************
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo){
		List<Vehiculo> lista = this.vehiculoService.buscarTodos();
		modelo.addAttribute("vehiculos",lista);
		return "vistaListaVehiculos";
	}
	
	// INGRESAR VEHICULOS
	@GetMapping("/nuevoVehiculo") // ingresar un nuevo vehiculo
	public String paginaNuevoVehiculo(Vehiculo vehiculo) {
		return "vistaIngresarVehiculo";
	}

	@PostMapping("/insertar")
	public String ingresarVehiculo(Vehiculo vehiculo) {
		this.vehiculoService.ingersar(vehiculo);
		return "redirect:/gestorPaginas/paginaEmpleado";
	}

	// VISTA PARA BUSCAR VEHICULOS POR MARCA
	@GetMapping("/buscarPorMarca")
	public String buscarPorMarca(Vehiculo vehiculo) {
		return "vistaBuscarPorMarca";
	}

	@GetMapping("/listaVehiculosPorMarca")
	public String devolverVehiculosPorMarca(Vehiculo vehiculo, Model model) {
		List<Vehiculo> listaVehiculos = this.vehiculoService.buscarPorMarcaDisponible(vehiculo.getMarca());
		model.addAttribute("vehiculos", listaVehiculos);
		return "vistaVehiculosPorMarca";
	}
	//

	// VISUALIZAR VEHICULO
	@GetMapping("/visualizarVehiculo/{idVehiculo}")
	public String visualizarVehiculo(@PathVariable("idVehiculo") Integer id, Model modelo) {
		Vehiculo vehiculo = this.vehiculoService.buscar(id);
		modelo.addAttribute("vehiculo", vehiculo);
		return "vistaVisualizarVehiculo";
	}

	// ACTUALIZAR VEHICULOS
	@GetMapping("/buscarVehiculo/{idVehiculo}")
	public String buscarVehiculo(@PathVariable("idVehiculo") Integer id, Model modelo) {
		Vehiculo vehiculo = this.vehiculoService.buscar(id);
		modelo.addAttribute("vehiculo", vehiculo);
		return "vistaActualizarVehiculo";
	}

	@PutMapping("/actualizar/{idVehiculo}")
	public String actualizarVehiculo(@PathVariable("idVehiculo") Integer id, Vehiculo vehiculo) {
		vehiculo.setId(id);
		this.vehiculoService.actualizar(vehiculo);
		return "redirect:/vehiculo/buscar";
	}
	
//************************************************************************************************************************************
	@DeleteMapping("/eliminarID/{id}")
	public String eliminarID(@PathVariable("id") Integer id, Model modelo) {
		Vehiculo vehiculo2=this.vehiculoService.buscar(id);
		String text=this.vehiculoService.eliminar(vehiculo2.getPlaca());
		modelo.addAttribute("texto",text);
		return "vistaEliminar";
	}
}
