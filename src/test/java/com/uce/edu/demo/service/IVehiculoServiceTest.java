package com.uce.edu.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.modelo.Vehiculo;
@SpringBootTest
class IVehiculoServiceTest {
	
	@Autowired
	private IVehiculoService vehiculoService;

	@Test
	@Transactional
	@Rollback(true)
	void testIngersar() {
		Vehiculo v=new Vehiculo();
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
		
		Supplier<Boolean> supplier=() -> {
			try {

				this.vehiculoService.ingersar(v);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		};
		assertTrue(supplier.get());
		
	}

	@Test
	@Transactional
	@Rollback(true)
	void testEliminar() {
		Supplier<Boolean> supplier=() -> {
			try {

				this.vehiculoService.eliminar("E48-ZP");
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		};
		assertTrue(supplier.get());
	}

	@Test
	@Transactional
	@Rollback(true)
	void testBuscar() {
		Vehiculo vehiculo=this.vehiculoService.buscar(100);
		assertNotNull(vehiculo);
	}

	@Test
	@Transactional
	@Rollback(true)
	void testActualizar() {
		Vehiculo v=vehiculoService.buscarPlaca("E48-ZP");
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
		
		Supplier<Boolean> supplier=() -> {
			try {

				this.vehiculoService.actualizar(v);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		};
		assertTrue(supplier.get());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	void testBuscarVehiculos() {
		List<Vehiculo> clientes=this.vehiculoService.buscarVehiculos("KIA","SPORTAGE");
		assertNotEquals(clientes.size(), 0);
	}

	@Test
	@Transactional
	@Rollback(true)
	void testBuscarPorMarca() {
		List<Vehiculo> clientes=this.vehiculoService.buscarPorMarca("KIA");
		assertNotEquals(clientes.size(), 0);
	}

}
