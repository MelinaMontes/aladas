package ar.com.ada.api.aladas.services;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Vuelo.EstadoVueloEnum;
import ar.com.ada.api.aladas.models.request.NuevoEstadoVuelo;
import ar.com.ada.api.aladas.repos.VueloRepository;

@Service
public class VueloService {

    @Autowired
    private VueloRepository repo;

    @Autowired
    private AeropuertoService aeroService;

    public void crear(Vuelo vuelo) {

        vuelo.setEstadoVueloId(EstadoVueloEnum.GENERADO);
        repo.save(vuelo);

    }

    public Vuelo crear(Date fecha, Integer capacidad, String aeropuertoOrigenIATA, String aeropuertoDestinoIATA,
            BigDecimal precio, String codigoMoneda) {

        Vuelo vuelo = new Vuelo();
        vuelo.setFecha(fecha);
        vuelo.setCapacidad(capacidad);

        Aeropuerto aeropuertoOrigen = aeroService.buscarPorCodigoIATA(aeropuertoOrigenIATA);

        Aeropuerto aeropuertoDestino = aeroService.buscarPorCodigoIATA(aeropuertoDestinoIATA);

        vuelo.setAeropuertoOrigen(aeropuertoOrigen.getAeropuertoId());
        vuelo.setAeropuertoDestino(aeropuertoDestino.getAeropuertoId());

        vuelo.setPrecio(precio);
        vuelo.setCodigoMoneda(codigoMoneda);

        crear(vuelo);
        return vuelo;

    }

    public ValidacionVueloDataEnum validar(Vuelo vuelo) {

        if (!validarPrecio(vuelo))
            return ValidacionVueloDataEnum.ERROR_PRECIO;

        if (!validarAeropuertoOrigenDifDestino(vuelo))
            return ValidacionVueloDataEnum.ERROR_MISMO_AEROPUERTO;

        // if (!validarFecha(vuelo))
        // return ValidacionVueloDataEnum.ERROR_FECHA;

        // if (!validarCapacidadMinima(vuelo))
        // return ValidacionVueloDataEnum.ERROR_CAPACIDAD_MINIMA;

        return ValidacionVueloDataEnum.OK;
    }

    public boolean validarPrecio(Vuelo vuelo) {

        if (vuelo.getPrecio() == null) {
            return false;
        }
        if (vuelo.getPrecio().doubleValue() > 0)
            return true;

        return false;
    }

    public boolean validarAeropuertoOrigenDifDestino(Vuelo vuelo) {
        return vuelo.getAeropuertoDestino() != vuelo.getAeropuertoOrigen();
    }

    public enum ValidacionVueloDataEnum {
        OK, ERROR_PRECIO, ERROR_MISMO_AEROPUERTO, ERROR_AEROPUERTO_ORIGEN, ERROR_AEROPUERTO_DESTINO, ERROR_FECHA,
        ERROR_MONEDA, ERROR_CAPACIDAD_MINIMA, ERROR_CAPACIDAD_MAXIMA, ERROR_GENERAL
    }

    public Vuelo buscarPorId(Integer id) {
        return repo.findByVueloId(id);
    }

    public void actualizar(Vuelo vuelo) {

        repo.save(vuelo);

    }

    public List<Vuelo> traerVuelosAbiertos() {
        return repo.findByEstadoVueloId(EstadoVueloEnum.ABIERTO.getValue());
    }

    // chequear

    /*
     * public boolean validarFecha(Vuelo vuelo) { boolean correcto = false;
     * 
     * try { //Formato de fecha (d??a/mes/a??o) SimpleDateFormat formatoFecha = new
     * SimpleDateFormat("dd/MM/yyyy"); formatoFecha.setLenient(false);
     * //Comprobaci??n de la fecha formatoFecha.parse(this.dia + "/" + this.mes + "/"
     * + this.anio); correcto = true; } catch (ParseException e) { //Si la fecha no
     * es correcta, pasar?? por aqu?? correcto = false; }
     * 
     * return correcto; }
     */
    // public boolean validarCapacidadMinima(Vuelo vuelo){}

}
