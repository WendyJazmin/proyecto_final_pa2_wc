package com.uce.edu.demo.service;

import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVIP;

@Service
public class GestorClienteServiceImpl implements IGestorClienteService{
	
	private static final Logger LOG = Logger.getLogger(GestorClienteServiceImpl.class.getName());

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	@Qualifier("registro_empleado")
	private IClienteService clienteRegistroEmpleadoService;
	
	@Autowired
	@Qualifier("registro_cliente")
	private IClienteService clienteRegistroClienteService;
	
	@Override
	@Transactional(value = TxType.REQUIRED)
	public void crearCliente(Cliente cliente, String registro) {
		// TODO Auto-generated method stub
		
		if(registro.equals("C")) {
			cliente=this.clienteRegistroClienteService.crear(cliente);
		}else if(registro.equals("E")) {
			cliente=this.clienteRegistroEmpleadoService.crear(cliente);
		}
		this.clienteRepository.ingresar(cliente);
		LOG.info("Se a creado al cliente: "+cliente.getCedula());
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public String eliminar(String cedula) {
		// TODO Auto-generated method stub
		Cliente cliente=this.clienteRepository.buscarCedula(cedula);
		if(cliente.getReservas().size()==0) {
			this.clienteRepository.eliminar(cliente.getId());
			LOG.info("Se a eliminado al cliente: "+cedula);
			return "Se a eliminado al cliente con cedula: "+cedula;
		}else {
			LOG.info("NO se a eliminado al cliente: "+cedula);
			return "No se pudo eliminar al cliente: "+cedula+" porque tiene reservas realizas";
		}
		
	}

	@Override
	public Cliente buscarID(Integer id) {
		// TODO Auto-generated method stub
		LOG.info("Se a buscado al cliente: "+id);
		return this.clienteRepository.buscarID(id);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		LOG.info("Se a actualizado al cliente: "+cliente.getCedula());
		this.clienteRepository.actualizar(cliente);
	}

	@Override
	public List<Cliente> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		LOG.info("Se a buscado a los clientes con apellido: "+apellido);
		return this.clienteRepository.buscarPorApellido(apellido);
	}

	@Override
	public Cliente buscarCedula(String cedula) {
		// TODO Auto-generated method stub
		LOG.info("Se al cliente con cedula: "+cedula);
		return this.clienteRepository.buscarCedula(cedula);
	}

	@Override
	public List<ClienteVIP> reporteClientesVip() {
		// TODO Auto-generated method stub
		LOG.info("Se a realizado el reporte de clientes VIP");
		return this.clienteRepository.reporteClientesVip().stream()
				.sorted(Comparator.comparing(ClienteVIP::getValorTotal).reversed()).toList();
	}

}
