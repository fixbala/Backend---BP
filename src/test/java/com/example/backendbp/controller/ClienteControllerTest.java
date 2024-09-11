package com.example.backendbp.controller;

import com.example.backendbp.entity.Cliente;
import com.example.backendbp.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testGetAllClientes() throws Exception {
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateCliente() throws Exception {
        String clienteJson = "{ \"nombre\": \"Juan Perez\", \"genero\": \"Masculino\", \"edad\": 30, \"identificacion\": \"123456789\", \"direccion\": \"Calle Falsa 123\", \"telefono\": \"555-1234\", \"clienteId\": \"C001\", \"contraseña\": \"password\", \"estado\": true }";

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Perez"));
    }
}
