package com.microservice.paciente.microservice_paciente.repository;

//import com.microservice.paciente.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.paciente.microservice_paciente.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer>{
    //Metodos heredados aqui
}
