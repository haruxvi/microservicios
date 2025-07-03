package com.microservice.paciente.microservice_paciente.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "paciente")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaciente;

    @Column(name = "rut", unique = true, length = 13, nullable = false)
    @NotBlank(message = "El rut es obligatorio")
    @Pattern(
        regexp = "^[0-9]{7,8}-[0-9Kk]$",
        message = "El rut debe tener formato 12345678-9 (Chile)"
    )
    private String rut;

    @Column(nullable = false)
    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 2, max = 100, message = "Los nombres deben tener entre 2 y 100 caracteres")
    private String nombres;

    @Column(nullable = false)
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private Date fechaNacimiento;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    private String correo;
}
