package com.microservice.paciente.microservice_paciente.controller;

import com.microservice.paciente.microservice_paciente.dto.PacienteDTO;
import com.microservice.paciente.microservice_paciente.model.Paciente;
import com.microservice.paciente.microservice_paciente.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteDTO>> getAllPatients() {
        List<PacienteDTO> pacientes = pacienteService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/saludar")
    public String hola() {
        return "Hola";
    }

    @GetMapping("/{idPaciente}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer idPaciente) {
        Optional<Paciente> paciente = pacienteService.getPatientById(idPaciente);

        if (paciente.isPresent()) {
            return ResponseEntity.ok()
                    .header("mi-encabezado", "valor")
                    .body(convertToDTO(paciente.get()));
        } else {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("message", "No se encontró el paciente con ID: " + idPaciente);
            errorBody.put("status", "404");
            errorBody.put("timestamp", LocalDateTime.now().toString());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PacienteDTO pacienteDTO) {
        try {
            Paciente paciente = convertToEntity(pacienteDTO);
            Paciente pacienteGuardado = pacienteService.save(paciente);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(pacienteGuardado.getIdPaciente())
                    .toUri();

            return ResponseEntity.created(location).body(convertToDTO(pacienteGuardado));

        } catch (DataIntegrityViolationException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "El correo ya está registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteDTO> update(@PathVariable int idPaciente,
                                              @RequestBody @Valid PacienteDTO pacienteDTO) {
        try {
            Paciente paciente = convertToEntity(pacienteDTO);
            paciente.setIdPaciente(idPaciente);

            Paciente actualizado = pacienteService.save(paciente);
            return ResponseEntity.ok(convertToDTO(actualizado));

        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<?> eliminar(@PathVariable int idPaciente) {
        try {
            pacienteService.delete(idPaciente);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    // ------------------ Conversión Entity <-> DTO ------------------

    private PacienteDTO convertToDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .idPaciente(paciente.getIdPaciente())
                .rut(paciente.getRut())
                .nombres(paciente.getNombres())
                .apellidos(paciente.getApellidos())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .correo(paciente.getCorreo())
                .build();
    }

    private Paciente convertToEntity(PacienteDTO dto) {
        return Paciente.builder()
                .rut(dto.getRut())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .fechaNacimiento(dto.getFechaNacimiento())
                .correo(dto.getCorreo())
                .build();
    }
}
