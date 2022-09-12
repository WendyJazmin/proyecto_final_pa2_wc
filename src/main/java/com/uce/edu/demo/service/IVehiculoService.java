package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVIP;

public interface IVehiculoService {
	
	public void ingersar(Vehiculo v);
	public String eliminar(String placa);
	public Vehiculo buscar(Integer id);
	public void actualizar(Vehiculo v);
	public Vehiculo buscarPlaca(String placa);
	public List<Vehiculo> buscarVehiculos(String marca, String modelo);
	
	public List<Vehiculo> buscarPorMarca(String marca);
	public List<Vehiculo> buscarPorMarcaDisponible(String marca);
	public List<Vehiculo> buscarTodos();
	//Reporte
	public List<VehiculoVIP> reporteVehiculosVip(String anio, String mes);

}
