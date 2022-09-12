package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVIP;
@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void ingersar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarID(id));
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarID(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	//Buscar con la placa al vehiculo y devuelve al vehiculo junto con su reserva
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Vehiculo buscarPlaca(String placa) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.placa =:datoPlaca", Vehiculo.class);
		myQuery.setParameter("datoPlaca", placa);
		
		Vehiculo vehiculo=myQuery.getSingleResult();
		try {
			vehiculo.getReservas().size();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vehiculo;
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarVehiculos(String marca, String modelo) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery(
				"SELECT v FROM Vehiculo v WHERE v.marca =:datoMarca AND v.modelo =:datoModelo", Vehiculo.class);
		myQuery.setParameter("datoMarca", marca);
		myQuery.setParameter("datoModelo", modelo);

		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarPorMarca(String marca) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.marca =:datoMarca", Vehiculo.class);
		myQuery.setParameter("datoMarca", marca);
		
		return myQuery.getResultList();
	}
	
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Vehiculo> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v ", Vehiculo.class);
		
		return myQuery.getResultList();
	}
	// -----------------------------------------------------------------
	// Reporte

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<VehiculoVIP> reporteVehiculosVip(String anio, String mes) {
		// TODO Auto-generated method stub
		Integer anio1=Integer.parseInt(anio);
		Integer mes1=Integer.parseInt(mes);
		LocalDateTime fechaF = LocalDateTime.of(anio1, mes1, 1, 0, 0);
		
		TypedQuery<VehiculoVIP> myQuery = this.entityManager.createQuery("SELECT NEW com.uce.edu.demo.repository.modelo.VehiculoVIP(v.placa, v.modelo, v.marca, v.anioFabricacion, v.paisFabricacion, v.estado, SUM(c.subTotal), SUM(c.valorTotal)) "
                + "FROM Vehiculo v, Reserva r, Cobro c "
                + "WHERE v=r.vehiculo AND r=c.reserva "
                + "AND r.fechaFin <=:datoFechaFin "
                + "GROUP BY v.placa,v.modelo, v.marca, v.anioFabricacion, v.paisFabricacion, v.estado ",VehiculoVIP.class);
        
        myQuery.setParameter("datoFechaFin", fechaF);
		
		return myQuery.getResultList();
	}
}
