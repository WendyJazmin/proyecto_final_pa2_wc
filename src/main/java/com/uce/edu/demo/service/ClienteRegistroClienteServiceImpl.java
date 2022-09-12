package com.uce.edu.demo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.modelo.Cliente;
@Service
@Qualifier("registro_cliente")
public class ClienteRegistroClienteServiceImpl implements IClienteService {

	@Override
	public Cliente crear(Cliente cliente) {
		// TODO Auto-generated method stub	
		cliente.setRegistro("C");
		return cliente;
	}
}
