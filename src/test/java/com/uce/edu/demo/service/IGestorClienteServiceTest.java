package com.uce.edu.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVIP;
@SpringBootTest
class IGestorClienteServiceTest {

	@Autowired
	private IGestorClienteService gestorClienteService;
	
	@Test
	@Transactional
	@Rollback(true)
	void testBuscarPorApellido() {
		List<Cliente> clientes=this.gestorClienteService.buscarPorApellido("Rodrigues");
		assertNotEquals(clientes.size(), 0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testReporteClientesVip() {
		List<ClienteVIP> clientes= this.gestorClienteService.reporteClientesVip();
		assertNotEquals(clientes.size(), 0);
	}

}
