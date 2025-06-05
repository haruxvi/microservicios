package com.microservice.paciente.microservice_paciente.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    
    private int id_paciente;
    private String rut;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String correo;
}
