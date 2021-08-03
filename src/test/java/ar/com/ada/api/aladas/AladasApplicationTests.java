package ar.com.ada.api.aladas;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Vuelo.EstadoVueloEnum;
import ar.com.ada.api.aladas.services.AeropuertoService;
import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;

@SpringBootTest
class AladasApplicationTests {

	@Autowired
	VueloService vueloService;

	@Autowired
	AeropuertoService aeropuertoService;

	@Test
	void vueloTestPrecioNegativo() {

		Vuelo vueloConPrecioNegativo = new Vuelo();
		vueloConPrecioNegativo.setPrecio(new BigDecimal(-100));

		// afirma que sea verdadero
		assertFalse(vueloService.validarPrecio(vueloConPrecioNegativo));

	}

	@Test
	void vueloTestPrecioOk() {

		Vuelo vueloConPrecioOk = new Vuelo();
		vueloConPrecioOk.setPrecio(new BigDecimal(100));

		assertTrue(vueloService.validarPrecio(vueloConPrecioOk));
	}

	@Test
	void aeropuertoValidarCodigoIATAOk() {

		String codigoIATAOk = "EZE";
		String codigoIATAOk2 = "";
		String codigoIATAOk3 = "N67";

		Aeropuerto aeropuerto = new Aeropuerto();
		aeropuerto.setCodigoIATA(codigoIATAOk);

		Aeropuerto aeropuerto2 = new Aeropuerto();
		aeropuerto.setCodigoIATA(codigoIATAOk2);

		Aeropuerto aeropuerto3 = new Aeropuerto();
		aeropuerto.setCodigoIATA(codigoIATAOk3);

		assertTrue(aeropuertoService.validarCodigoIATA(aeropuerto));
		assertTrue(aeropuertoService.validarCodigoIATA(aeropuerto2));

		assertFalse(aeropuertoService.validarCodigoIATA(aeropuerto3));
	}
	@Test
	void vueloValidarVueloMismoDestionoUsandoGeneral() {
		Vuelo vuelo = new Vuelo();
		vuelo.setPrecio(new BigDecimal(1000));
		vuelo.setEstadoVueloId(EstadoVueloEnum.GENERADO);
		vuelo.setAeropuertoOrigen(116);
		vuelo.setAeropuertoDestino(116);

		assertEquals( ValidacionVueloDataEnum.ERROR_MISMO_AEROPUERTO, vueloService.validar(vuelo));
	}

	@Test
	void aeropuertoValidarCodigoIATANoOK() {}

	@Test
	void vueloVerificarValidacionAeropuertoOrigenDestino() {}

	@Test
	void aeropuertoTestBuscadorIATA() {}

	
	/*
	 * @Test
	 * 
	 *  void vueloTestFechaOk() {
	 * 
	 * Vuelo vueloConFechaOk = new Vuelo(); vueloConFechaOk.setFecha((new Date()));
	 * 
	 * assertTrue(vueloService.validarFecha(vueloConFechaOk)); }
	 */
}


