package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVIP;

public interface IGestorClienteService {
	
	public void crearCliente(Cliente cliente, String registro);

	public String eliminar(String cedula);

	public Cliente buscarID(Integer id);

	public void actualizar(Cliente cliente);
	
	public Cliente buscarCedula(String cedula);

	public List<Cliente> buscarPorApellido(String apellido);
	
	// Reporte
	public List<ClienteVIP> reporteClientesVip();
}
