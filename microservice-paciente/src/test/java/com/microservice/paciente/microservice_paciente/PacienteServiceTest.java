package com.microservice.paciente;

import com.microservice.paciente.microservice_paciente.model.Paciente;
import com.microservice.paciente.microservice_paciente.repository.PacienteRepository;
import com.microservice.paciente.microservice_paciente.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @InjectMocks
    private PacienteService pacienteService;

    @Mock
    private PacienteRepository pacienteRepository;

    private Paciente crearPacienteDemo() {
        return Paciente.builder()
                .id_paciente(1)
                .rut("21.601.807-9")
                .nombres("Vicente")
                .apellidos("Donoso")
                .fechaNacimiento(new Date(93, 0, 1)) // 1 de enero 1993
                .correo("vicente@correo.com")
                .build();
    }

    @Test
    public void testFindAll() {
        // Configurar mock
        when(pacienteRepository.findAll()).thenReturn(List.of(crearPacienteDemo()));

        List<Paciente> pacientes = pacienteService.findAll();

        assertNotNull(pacientes);
        assertEquals(1, pacientes.size());
        assertEquals("Vicente", pacientes.get(0).getNombres());
    }

    @Test
    public void testFindById() {
        // Configurar datos de prueba
        Integer id = 1;
        Paciente pacienteDemo = crearPacienteDemo();
        
        // Configurar mock
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(pacienteDemo));

        // Ejecutar método
        Paciente encontrado = pacienteService.findById(id);

        // Verificar resultados
        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId_paciente());
        assertEquals("Donoso", encontrado.getApellidos());
    }

    @Test
    public void testFindByIdNotFound() {
        // Configurar mock para caso negativo
        when(pacienteRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Verificar excepción
        assertThrows(RuntimeException.class, () -> pacienteService.findById(99));
    }

    @Test
    public void testSave() {
        // Configurar datos de prueba
        Paciente nuevoPaciente = crearPacienteDemo();
        
        // Configurar mock
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(nuevoPaciente);

        // Ejecutar método
        Paciente guardado = pacienteService.save(nuevoPaciente);

        // Verificar resultados
        assertNotNull(guardado);
        assertEquals("21.601.807-9", guardado.getRut());
        assertEquals("vicente@correo.com", guardado.getCorreo());
    }

    @Test
    public void testDeleteById() {
        // Configurar datos de prueba
        Integer id = 1;
        
        // Configurar mock
        doNothing().when(pacienteRepository).deleteById(id);

        // Ejecutar método
        pacienteService.deleteById(id);

        // Verificar interacción
        verify(pacienteRepository, times(1)).deleteById(id);
    }
}