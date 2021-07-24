package ar.com.ada.api.aladas.entities;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ususario_id")
    private Integer UsuarioId;

    @NaturalId
    private String username;

    private String password;

    private String email;

    @Column (name = "fecha_login")
    private Date fechaLogin;

    @Column (name = " tipo_usuario")
    private Integer tipoUsuario;

    @OneToOne
    @JoinColumn (name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "pasajero_id", referencedColumnName = "pasajero_id")
    private Pasajero pasajero;

    public enum TipoUsuarioEnum {
        STAFF(1) , PASAJERO(2);

        private final Integer value;

        private TipoUsuarioEnum (Integer value) {
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }

        public static TipoUsuarioEnum parse(Integer id) { 
            TipoUsuarioEnum status = null;
            for (TipoUsuarioEnum item : TipoUsuarioEnum.values()) {
                if (item.getValue().equals (id)) {
                    status = item;
                    break;
                }

            }
            return status;
        }
    }

    public Integer getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        UsuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(Date fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario.getValue();
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }
    
    
    
}
