
        List<Paciente> pacientes = carreraService.findAll(); 
        
        asserNotNull(pacientes); 
        assertEquals(1, pacientes.size());

        @Test
