package com.uce.edu.demo.service;

import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVIP;
@Service
public class VehiculoServiceImpl implements IVehiculoService{
	
	private static final Logger LOG = Logger.getLogger(GestorClienteServiceImpl.class.getName());
	
	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void ingersar(Vehiculo v) {
		// TODO Auto-generated method stub
		this.vehiculoRepository.ingersar(v);
		LOG.info("Se a creado al vehiculo: "+v.getPlaca());
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String eliminar(String placa) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo=this.vehiculoRepository.buscarPlaca(placa);
		if(vehiculo.getReservas().size()==0) {
			this.vehiculoRepository.eliminar(vehiculo.getId());
			LOG.info("Se a eliminado al vehiculo: "+placa);
			return "Se a eliminado al vehiculo con placa: "+placa;
		}else {
			LOG.info("NO se a eliminado al vehiculo: "+placa);
			return "No se pudo eliminar al vehiculo: "+placa+" porque tiene reservas realizas";
		}
	}

	@Override
	public Vehiculo buscar(Integer id) {
		// TODO Auto-generated method stub
		LOG.info("Se a buscado al vehiculo con id: "+id);
		return this.vehiculoRepository.buscarID(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Vehiculo v) {
		// TODO Auto-generated method stub
		this.vehiculoRepository.actualizar(v);
		LOG.info("Se a actualizado al vehiculo con placa: "+v.getPlaca());
	}

	@Override
	public Vehiculo buscarPlaca(String placa) {
		// TODO Auto-generated method stub
		LOG.info("Se a buscado al vehiculo con placa: "+placa);
		return this.vehiculoRepository.buscarPlaca(placa);
	}

	@Override
	public List<Vehiculo> buscarVehiculos(String marca, String modelo) {
		// TODO Auto-generated method stub
		LOG.info("Se a buscado los vehiculos con marca: "+marca+" y modelo: "+modelo);
		return this.vehiculoRepository.buscarVehiculos(marca, modelo)
				.parallelStream().filter(vehi -> vehi.getEstado().equals("D")).toList();
	}

	@Override
	public List<Vehiculo> buscarPorMarca(String marca) {
		// TODO Auto-generated method stub
		LOG.info("Se a buscado a los vehiculos con marca: "+marca);
		return this.vehiculoRepository.buscarPorMarca(marca);
	}
	
	@Override
	public List<Vehiculo> buscarTodos() {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscarTodos();
	}
	
	@Override
	public List<Vehiculo> buscarPorMarcaDisponible(String marca) {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscarPorMarca(marca).parallelStream()
				.filter(vehi -> vehi.getEstado().equals("D")).toList();
	}
	
	// -----------------------------------------------------------------
	// Reporte

	@Override
	public List<VehiculoVIP> reporteVehiculosVip(String anio, String mes) {
		// TODO Auto-generated method stub
		LOG.info("Se a buscado a los vehiculos con año y mes: "+anio+"/"+mes);
		return this.vehiculoRepository.reporteVehiculosVip(anio, mes).stream()
				.sorted(Comparator.comparing(VehiculoVIP::getValorTotal).reversed()).toList();
	}

}
