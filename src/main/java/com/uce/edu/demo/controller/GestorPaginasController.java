package com.uce.edu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestorPaginas")
public class GestorPaginasController {
	
	//Vista para la pagina principal
	@GetMapping("/paginaPrincipal")
	public String paginaPrincipal() {
		return "vistaPaginaPrincipal";
	}
	
	//Vista para la pagina de empleado
	@GetMapping("/paginaEmpleado")
	public String paginaEmpleado() {
		return "vistaPaginaEmpleado";
	}
	
	//Vista para la pagina de reportes
	@GetMapping("/paginaReportes")
	public String paginaReportes() {
		return "vistaPaginaReportes";
	}

}
