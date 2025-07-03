package com.microservice.paciente.microservice_paciente;

import com.microservice.paciente.microservice_paciente.model.Paciente;
import com.microservice.paciente.microservice_paciente.repository.PacienteRepository;
import com.microservice.paciente.microservice_paciente.service.PacienteService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    public void testFindAll() {
        Paciente paciente = new Paciente();
        when(pacienteRepository.findAll()).thenReturn(List.of(paciente));

        List<Paciente> pacientes = pacienteService.findAll();

        assertNotNull(pacientes);
        assertEquals(1, pacientes.size());

        verify(pacienteRepository, times(1)).findAll();
    }

    @Test
    public void testGetPatientById() {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(1);

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(paciente));

        Optional<Paciente> result = pacienteService.getPatientById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getIdPaciente());

        verify(pacienteRepository, times(1)).findById(1);
    }

    @Test
    public void testSave() {
        Paciente paciente = new Paciente();
        paciente.setRut("12345678-9");

        when(pacienteRepository.save(paciente)).thenReturn(paciente);

        Paciente saved = pacienteService.save(paciente);

        assertNotNull(saved);
        assertEquals("12345678-9", saved.getRut());

        verify(pacienteRepository, times(1)).save(paciente);
    }

    @Test
    public void testDelete() {
        int id = 1;

        doNothing().when(pacienteRepository).deleteById(id);

        pacienteService.delete(id);

        verify(pacienteRepository, times(1)).deleteById(id);
    }
}
