package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVIP;
@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	//CRUD
	@Override
	@Transactional(value = TxType.MANDATORY)
	public void ingresar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cliente);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscarID(id));
	}

	@Override
	//@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarID(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cliente);
	}

	//Buscar con la cedula al cliente y devuelve al cliente junto con sus reservas
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente buscarCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cedula =:datoCedula", Cliente.class);
		myQuery.setParameter("datoCedula", cedula);
		
		Cliente cliente=myQuery.getSingleResult();
		try {
			cliente.getReservas().size();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cliente;
	}
	
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Cliente> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("SELECT c FROM Cliente c WHERE c.apellido =:datoApellido", Cliente.class);
		myQuery.setParameter("datoApellido", apellido);
		return myQuery.getResultList();
	}
	
	// -----------------------------------------------------------------
	// Reporte
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<ClienteVIP> reporteClientesVip() {
		// TODO Auto-generated method stub
		
		TypedQuery<ClienteVIP> myQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.repository.modelo.ClienteVIP"
				+ "(cli.cedula, cli.nombre, cli.apellido, cli.registro, SUM(c.valorlIVA), SUM(c.valorTotal)) "
                + "FROM Cliente cli, Reserva r, Cobro c "
                + "WHERE cli=r.cliente AND r=c.reserva AND r.estado='E' "
                + "GROUP BY cli.cedula, cli.nombre, cli.apellido, cli.registro",ClienteVIP.class);
        
		return myQuery.getResultList();
	}

}
