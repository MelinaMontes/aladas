package ar.com.ada.api.aladas.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pasaje")
public class Pasaje {

    @Id
    @Column (name = "pasaje_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pasajeId;

    @OneToOne
    @JoinColumn (name = "reserva_id", referencedColumnName = "pasaje_id")
    private Reserva reserva;

    @Column (name = "fecha_emision")
    private Date fechaEmision;

    @Column (name = "info_pago")
    private String infoPago;


}
