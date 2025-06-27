package com.microservice.paciente; 
import com.microservice.paciente.microservice_paciente.model.Paciente; 
import com.microservice.paciente.microservice_paciente.repository.PacienteRepository; 
import com.microservice.paciente.microservice_paciente.service.PacienteService;

import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.extension.ExtendWith; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date; import java.util.List; 
import java.util.Optional;

import static org.mockito.Mockito.; 
import static org.junit.jupiter.api.Assertions.; 
import org.mockito.InjectMocks; 
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservice.paciente.microservice_paciente.repository.PacienteRepository; 
import com.microservice.paciente.microservice_paciente.service.PacienteService;

import jakarta.inject.Inject;

@ExtendWith({MockitoExtension.class}) public class PacienteServiceTest {

@InjectMocks
private PacienteService pacienteService;
@Mock 
private PacienteRepository pacienteRepository; 
public PacienteServiceTest(){
}
@Test
public void testFindAll(){
    when(pacienteRepository.findAll()).thenReturn(List.off(new Paciente()));



    List<Paciente> pacientes = carreraService.findAll(); 
    
    asserNotNull(pacientes); 
    assertEquals(1, pacientes.size());

    @Test
