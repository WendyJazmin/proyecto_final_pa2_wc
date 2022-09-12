package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVIP;

public interface IVehiculoRepository {
	
	//CRUD
	public void ingersar(Vehiculo vehiculo);
	public void eliminar(Integer id);
	public Vehiculo buscarID(Integer id);
	public void actualizar(Vehiculo vehiculo);
	public Vehiculo buscarPlaca(String placa);
	public List<Vehiculo> buscarVehiculos(String marca, String modelo);
	
	public List<Vehiculo> buscarPorMarca(String marca);
	 public List<Vehiculo> buscarTodos();
	//Reporte
	public List<VehiculoVIP> reporteVehiculosVip(String anio, String mes);
}
