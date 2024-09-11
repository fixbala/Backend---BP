package com.example.backendbp.repository;

import com.example.backendbp.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testCreateCliente() {
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

        Cliente savedCliente = clienteRepository.save(cliente);
        assertNotNull(savedCliente.getId());
    }
}
