package com.uce.edu.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Cliente;

@SpringBootTest
@Transactional
@Rollback(true)
class ClienteRepositoryImplTest {

	@Autowired
	private IClienteRepository clienteRepository;
	
	
	@Test
	void testIngresar() {
		Cliente cliente1=new Cliente();
		cliente1.setApellido("T");
		cliente1.setCedula("17");
		cliente1.setFechaNacimiento(LocalDateTime.of(1999, 11, 26, 0, 0));
		cliente1.setGenero("M");
		cliente1.setNombre("K");
		cliente1.setRegistro("C");	

		this.clienteRepository.ingresar(cliente1);
		Cliente cliente3=this.clienteRepository.buscarCedula("17");
		assertNotNull(cliente3);
	
	}

	@Test
	void testEliminar() {
		Integer id=100;
		Cliente cliente1=this.clienteRepository.buscarID(id);
		this.clienteRepository.eliminar(id);
		assertNotNull(cliente1);
	
	}

	@Test
	void testBuscarID() {

		assertNotNull(this.clienteRepository.buscarID(100));
	}

	@Test
	void testActualizar() {

		Cliente cliente1=new Cliente();
		cliente1.setId(100);
		cliente1.setApellido("Toapanta");
		cliente1.setCedula("1725845869");
		cliente1.setFechaNacimiento(LocalDateTime.of(1997, 11, 26, 0, 0));
		cliente1.setGenero("M");
		cliente1.setNombre("Kevin");
		cliente1.setRegistro("C");
		
		this.clienteRepository.actualizar(cliente1);
		assertEquals(cliente1.getCedula(), this.clienteRepository.buscarID(100).getCedula());

	}

	@Test
	void testBuscarCedula() {
		assertNotNull(this.clienteRepository.buscarCedula("1725845861"));
		
	}

}
