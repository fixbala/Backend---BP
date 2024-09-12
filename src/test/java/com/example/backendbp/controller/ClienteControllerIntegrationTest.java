package com.example.backendbp.controller;

import com.example.backendbp.entity.Cliente;
import com.example.backendbp.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setup() {
        clienteRepository.deleteAll();  // Limpiar la base de datos antes de cada prueba
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("123456789");
        cliente.setDireccion("Calle Falsa 123");
        cliente.setTelefono("555-1234");
        cliente.setClienteId("C001");
        cliente.setContraseña("password");
        cliente.setEstado(true);
        clienteRepository.save(cliente);
    }

    @Test
    public void testGetAllClientes() throws Exception {
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Perez"));
    }

    @Test
    public void testCreateCliente() throws Exception {
        String clienteJson = "{ \"nombre\": \"Maria Lopez\", \"genero\": \"Femenino\", \"edad\": 25, \"identificacion\": \"987654321\", \"direccion\": \"Avenida Siempre Viva\", \"telefono\": \"555-6789\", \"clienteId\": \"C002\", \"contraseña\": \"password123\", \"estado\": true }";

        ResultActions resultActions = mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(clienteJson));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Maria Lopez"));
    }
}
