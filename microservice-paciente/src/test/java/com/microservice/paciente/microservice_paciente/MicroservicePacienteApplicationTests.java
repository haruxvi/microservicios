package com.microservice.paciente;

import com.microservice.paciente.microservice_paciente.model.Paciente;
import com.microservice.paciente.microservice_paciente.repository.PacienteRepository;
import com.microservice.paciente.microservice_paciente.service.PacienteService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @InjectMocks
    private PacienteService pacienteService;

    @Mock
    private PacienteRepository pacienteRepository;

    @Test
    public void testFindAll() {
        when(pacienteRepository.findAll()).thenReturn(List.of(new Paciente()));

        List<Paciente> pacientes = pacienteService.findAll();
        assertNotNull(pacientes);
        assertEquals(1, pacientes.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(id);
        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));

        Paciente found = pacienteService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Juan");
        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente saved = pacienteService.save(paciente);
        assertNotNull(saved);
        assertEquals("Juan", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        doNothing().when(pacienteRepository).deleteById(id);

        pacienteService.deleteById(id);
        verify(pacienteRepository, times(1)).deleteById(id);
    }
}
