package ar.com.ada.api.aladas.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.aladas.entities.Pasaje;
import ar.com.ada.api.aladas.entities.Reserva;
import ar.com.ada.api.aladas.entities.Reserva.EstadoReservaEnum;
import ar.com.ada.api.aladas.repos.PasajeRepository;

@Service
public class PasajeService {

    @Autowired
    PasajeRepository repo;

    @Autowired
    ReservaService reservaService;

    @Autowired
    VueloService vueloService;


    public Pasaje emitir(Integer reservaId) {

        Pasaje pasaje = new Pasaje();
        pasaje.setFechaEmision(new Date());

        Reserva reserva = reservaService.buscarPorId(reservaId);
        reserva.setEstadoReservaId(EstadoReservaEnum.EMITIDA);
        reserva.ascociarPasaje(pasaje);
        Integer nuevaCapacidad = reserva.getVuelo().getCapacidad()-1;
        reserva.getVuelo().setCapacidad(nuevaCapacidad);

        vueloService.actualizar(reserva.getVuelo());

        return pasaje;
    }


}
