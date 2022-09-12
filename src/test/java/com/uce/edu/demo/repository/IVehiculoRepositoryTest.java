package com.uce.edu.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVIP;
@SpringBootTest
class IVehiculoRepositoryTest {

	@Autowired
	private IVehiculoRepository vehiculoRepository;
	
	@Test
	@Transactional
	@Rollback(true)
	void testReporteVehiculosVip() {
		List<VehiculoVIP> vehiculos= this.vehiculoRepository.reporteVehiculosVip("2022", "07");
		assertNotEquals(vehiculos.size(), 0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testIngersar() {
		
		Vehiculo v = new Vehiculo();
		v.setPlaca("Subaru");
		v.setModelo("T-CROSS CONFORTLINE AC");
		v.setMarca("Volkswagen");
		v.setAnioFabricacion("2019");
		v.setPaisFabricacion("Japon");
		BigDecimal valor=new BigDecimal(22920);
		v.setCilindraje("16");
		v.setAvaluo(valor.add(valor.multiply(new BigDecimal(0.12))));
		v.setValorDia(valor);
		v.setEstado("D");
		
		this.vehiculoRepository.ingersar(v);
		Vehiculo vehi2 = this.vehiculoRepository.buscarPlaca("Subaru");
		assertNotNull(vehi2);
		
	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testBuscarID() {
		Vehiculo vehiculo1 = this.vehiculoRepository.buscarID(100);
		assertNotNull(vehiculo1);
	}



	@Test
	@Transactional
	@Rollback(true)
	void testBuscarPlaca() {
		Vehiculo vehiculo2 = this.vehiculoRepository.buscarPlaca("E90-7P");
		assertNotNull(vehiculo2);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	void testBuscarVehiculos() {
		List<Vehiculo> vehiculo3 =this.vehiculoRepository.buscarVehiculos("Chevrolet", "Camaro");
		assertNotNull(vehiculo3);
	}

	@Test
	@Transactional
	@Rollback(false)
	void testActualizar() {
		
		Vehiculo vehiculo = this.vehiculoRepository.buscarID(100);
		String nplaca = vehiculo.getPlaca();
		
		vehiculo.setAnioFabricacion("2002");
		vehiculo.setAvaluo(new BigDecimal(20000));
		vehiculo.setCilindraje("3322");
		vehiculo.setEstado("D");
		vehiculo.setMarca("Mazda");
		vehiculo.setModelo("M23e");
		vehiculo.setPaisFabricacion("EE.UU");
		vehiculo.setPlaca("44r-rr");
		//vehiculo.setReservas(null);
		vehiculo.setValorDia(new BigDecimal(70));
		this.vehiculoRepository.actualizar(vehiculo);
		
		Vehiculo vehiculo1 = this.vehiculoRepository.buscarPlaca("44r-rr");
		assertEquals(nplaca,vehiculo1.getPlaca());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testEliminar() {
		
		this.vehiculoRepository.eliminar(100);
		
	}

}
