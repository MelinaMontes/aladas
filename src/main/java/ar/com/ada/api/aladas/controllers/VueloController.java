package ar.com.ada.api.aladas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.models.request.NuevoEstadoVuelo;
import ar.com.ada.api.aladas.models.request.response.GenericResponse;

import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;

@RestController
public class VueloController {

    @Autowired
    VueloService service;

    @PostMapping("/api/vuelos")
    public ResponseEntity<GenericResponse> nuevoVuelo(@RequestBody Vuelo vuelo) {

        GenericResponse respuesta = new GenericResponse();
        ValidacionVueloDataEnum resultadoValidado = service.validar(vuelo);

        if (resultadoValidado == ValidacionVueloDataEnum.OK) {

            service.crear(vuelo);

            respuesta.isOk = true;
            respuesta.id = vuelo.getVueloId();
            respuesta.message = "Nuevo vuelo creado con exito";

            return ResponseEntity.ok(respuesta);

        } else {
            respuesta.isOk = false;
            respuesta.message = "Error(" + resultadoValidado.toString() + ")";

            return ResponseEntity.badRequest().body(respuesta);
        }

    }

    @PutMapping("/api/vuelos/{id}/estados")
    public ResponseEntity<GenericResponse> actualizar(@PathVariable Integer id,
            @RequestBody NuevoEstadoVuelo nuevoEstadoVuelo) {

        GenericResponse respuesta = new GenericResponse();

        respuesta.isOk = true;
        Vuelo vuelo = service.buscarPorId(id);
        vuelo.setEstadoVueloId(nuevoEstadoVuelo.estado);

        respuesta.message = "Estado del vuelo modificado";

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("/api/vuelos/abiertos")
    public ResponseEntity<List<Vuelo>> getVuelosAbiertos() {
        return ResponseEntity.ok(service.traerVuelosAbiertos());
    }
}
