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

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVIP;
import com.uce.edu.demo.service.IGestorClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private IGestorClienteService gestorClienteService;
	
//************************************************************************************************************************************
	//Vista para la pagina cliente
	@GetMapping("/paginaCliente")
	public String paginaCliente(Cliente cliente) {
		return "vistaPaginaCliente";
	}
	
	//Vista para la pagina donde se registrara al cliente
	@GetMapping("/registrarCliente")
	public String registrarCliente(Cliente cliente, String fecha) {
		return "vistaRegistrarClientePorCliente";
	}

	// Metodo para registrar al cliente
	@PostMapping("/registrar")
	public String registrar(Cliente cliente) {
		this.gestorClienteService.crearCliente(cliente, "C");
		return "vistaPaginaCliente";
	}
//*****************************************************************************************************************
	// Vista para la pagina donde se pedira la cedula de un cliente para actualizar
	@GetMapping("/actualizarCliente")
	public String buscarCedulaClienteActualizar(Cliente cliente, Model modelo) {
		return "vistaActualizarClientePorCliente";
	}
	
	@GetMapping("/actualizarClientePorCliente")
	public String actualizarClienteEncontrado(@RequestParam("cedula") String cedula, Model modelo) {
		Cliente clie =this.gestorClienteService.buscarCedula(cedula);
		modelo.addAttribute("cliente", clie);
		return "actualizarClientePorCedula";
	}
	
	// Vista para la pagina donde se actualiza a un cliente
	@PostMapping("/actualizar/{idCedula}")
	public String actualizarClientePorCliente(@PathVariable("idCedula") String cedula, Cliente cliente) {
		cliente.setCedula(cedula);
		Cliente cliente2 = this.gestorClienteService.buscarCedula(cedula);
		cliente.setId(cliente2.getId());
		cliente.setRegistro(cliente2.getRegistro());
		this.gestorClienteService.actualizar(cliente);
		return "vistaPaginaCliente";
	}
//**************************************************************************************************************************	

	// Vista para la pagina donde se registrara al cliente por empleado
	@GetMapping("/registrarClienteEmpleado")
	public String registrarClienteEmpleado(Cliente cliente) {
		return "vistaRegistrarClientePorEmpleado";
	}

	// Metodo para registrar al cliente
	@PostMapping("/registrarEmpleado")
	public String registrarPorEmpleado(Cliente cliente) {
		this.gestorClienteService.crearCliente(cliente, "E");
		return "redirect:/gestorPaginas/paginaEmpleado";
	}
//**************************************************************************************************************************	

	//Pagina donde se proyectara las reservas
	@GetMapping("/listaReporteClientesVip")
	public String reporteReservas(Model modelo) {
		List<ClienteVIP> clienteVIPs = this.gestorClienteService.reporteClientesVip();
		modelo.addAttribute("clientes", clienteVIPs);
		return "vistaListaClientesVip";
	}
//**************************************************************************************************************************	
	// pagina para ingresar el apellido a buscar
	@GetMapping("/buscarClientesPorApellido")
	public String buscarCliente(String apellido) {
		return "vistaParaBuscarClienteApellido";
	}

	// Vista para la pagina con la lista de apellidos
	@GetMapping("/buscarTodos")
	public String buscarClientePorApellido(@RequestParam("apellido") String apellido, Model modelo) {
		List<Cliente> clie = this.gestorClienteService.buscarPorApellido(apellido);
		modelo.addAttribute("clientes", clie);
		return "vistaVisualizarElimActCliente";
	}

	// vista para la pagina donde se visualiza todos los datos del cliente
	@GetMapping("/visualizarCliente/{cedula}")
	public String VisualizarCliente(@PathVariable("cedula") String cedula, Model modelo) {
		Cliente clie = this.gestorClienteService.buscarCedula(cedula);
		modelo.addAttribute("cliente", clie);
		return "visualizarCliente";
	}

	// vista para la pagina donde se actualiza los datos del cliente (por el id)
	@GetMapping("/actualizarClienteId/{id}")
	public String actualizarClienteId(@PathVariable("id") Integer id, Model modelo) {
		Cliente clie = this.gestorClienteService.buscarID(id);
		modelo.addAttribute("cliente", clie);
		return "vistaActualizarClienteId";
	}

	// metodo para actualizar el cliente
	@PutMapping("/actualizarID/{id}")
	public String actualizarID(@PathVariable("id") Integer id, Cliente cliente) {
		cliente.setId(id);
		Cliente cliente2=this.gestorClienteService.buscarID(id);
		cliente.setRegistro(cliente2.getRegistro());
		this.gestorClienteService.actualizar(cliente);
		return "vistaParaBuscarClienteApellido";
	}
	
//******************************************************************************************************************************************
	@DeleteMapping("/eliminarID/{id}")
	public String eliminarID(@PathVariable("id") Integer id, Model modelo) {
		Cliente cliente2=this.gestorClienteService.buscarID(id);
		String text=this.gestorClienteService.eliminar(cliente2.getCedula());
		modelo.addAttribute("texto",text);
		return "vistaEliminar";
	}
	
}
