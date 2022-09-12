package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.ReporteReservas;
import com.uce.edu.demo.repository.modelo.Reserva;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void ingresar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarID(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.merge(reserva);
	}
	
	
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarPorNumero(String numero) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r WHERE r.numero =:numero", Reserva.class);
			myQuery.setParameter("numero", numero);
			Reserva reserva = myQuery.getSingleResult();
			reserva.getCliente().toString();
			reserva.getVehiculo().toString();
			return reserva;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarPorFechaPlaca(LocalDateTime inicio, LocalDateTime fin, String placa) {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
				"SELECT r FROM Reserva r INNER JOIN r.vehiculo v WHERE r.fechaInicio >=:inicio AND r.fechaFin<=:fin  AND v.placa=:placa",
				Reserva.class);
		myQuery.setParameter("inicio", inicio);
		myQuery.setParameter("fin", fin);
		myQuery.setParameter("placa", placa);
		return myQuery.getResultList();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Reserva> buscarTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("SELECT r FROM Reserva r", Reserva.class);
		return myQuery.getResultList();
	}
	// -----------------------------------------------------------------
	// Reporte

	@Override
    @Transactional(value = TxType.NOT_SUPPORTED)
    public List<ReporteReservas> buscarReservasFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {

       TypedQuery<ReporteReservas> myQuery = this.entityManager
                .createQuery("SELECT NEW com.uce.edu.demo.repository.modelo.ReporteReservas"
                        +"(r.id,r.numero,r.estado,r.fechaInicio,r.fechaFin,c.cedula,c.nombre,v.placa,v.modelo,v.avaluo) "
                        + "FROM Reserva r "
                        +"JOIN Cliente c ON r.cliente = c "
                        +"JOIN Vehiculo v ON r.vehiculo = v "
                        +"WHERE r.fechaInicio BETWEEN :fechaInicio AND :fechaFin "
                        +"OR r.fechaFin BETWEEN :fechaInicio AND :fechaFin", ReporteReservas.class);
        
        myQuery.setParameter("fechaInicio", fechaInicio);
        myQuery.setParameter("fechaFin", fechaFin);
        
        return myQuery.getResultList();
    }
    

}
