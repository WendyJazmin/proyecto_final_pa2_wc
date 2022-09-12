package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVIP;

public interface IClienteRepository {
	
	//CRUD
	public void ingresar(Cliente cliente);
	public void eliminar(Integer id);
	public Cliente buscarID(Integer id);
	public void actualizar(Cliente cliente);
	public Cliente buscarCedula(String cedula);
	
	public List<Cliente> buscarPorApellido(String apellido);
	
	//Reporte
	public List<ClienteVIP> reporteClientesVip();

}
