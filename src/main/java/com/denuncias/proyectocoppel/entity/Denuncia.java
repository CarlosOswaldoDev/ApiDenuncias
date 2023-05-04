package com.denuncias.proyectocoppel.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table( name = "denuncia" )
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class Denuncia implements Serializable {
    private static final long serialVersionUID = 1L;


    //FOLIO
    @Id                                                 // clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  el valor de la clave primaria se generará automáticamente en la base de datos
    @Column(name = "idFolio")                           //  se asigna a la columna "idFolio" en la tabla de la base de datos
    private int idFolio;                                // actúa como clave primaria en entidad Denuncia


    // PASO 1: DATOS DE LA DENUNCIA
    @NotBlank
    @Column(name = "empresa", length = 100, nullable = false)
    @NotNull(message = "El campo empresa no puede ser nulo")
    @NotEmpty(message = "El campo empresa no puede estar vacío")
    private String empresa;

    @NotBlank
    @Column(name = "pais", length = 100, nullable = false)
    @NotNull(message = "El campo pais no puede ser nulo")
    @NotEmpty(message = "El campo pais no puede estar vacío")
    private String pais;

    @NotBlank
    @Column(name = "estado", length = 100, nullable = false)
    @NotNull(message = "El campo estado no puede ser nulo")
    @NotEmpty(message = "El campo estado no puede estar vacío")
    private String estado;

    @NotNull(message = "El campo estado no puede ser nulo")
    @Column(name = "numcentro", length = 100, nullable = false)
    private Integer numcentro;

    // PASO 2: CONTACTO
    @Column(name = "anonimo")
    private Boolean anonimo;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;
    @Column(name = "correoElectronico")
    private String correoElectronico;
    @Column(name = "telefono", length = 10)
    private Long telefono;



    // PASO 3: DETALLE DE DENUNCIA
    @NotBlank
    @Column(name = "detalleDenuncia", length = 10000, nullable = false)
    @NotNull(message = "El campo detalleDenuncia no puede ser nulo")
    @NotEmpty(message = "El campo detalleDenuncia no puede estar vacío")
    private String detalleDenuncia;

    //@NotBlank
    @Column(name = "fechaSuceso", nullable = false)
    private LocalDate fechaSuceso;

    @NotBlank
    @Column(name = "contraseña", length = 100, nullable = false)
    private String password;

    // PORTAL ADMIN
    //@NotBlank
    @Column(name = "estatus", length = 100, nullable = false)
    private Boolean estatus;


    // PREPERSIST para cuando persista un objeto a la base de datos
    @PrePersist
    public void saveDate() {
        fechaSuceso = LocalDate.now();
    }
}
