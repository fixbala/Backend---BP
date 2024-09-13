package com.example.backendbp.controller;

import com.example.backendbp.entity.Cliente;
import com.example.backendbp.repository.ClienteRepository;
import com.example.backendbp.service.ClienteMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMessageProducer clienteMessageProducer;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteRepository.save(cliente);
        clienteMessageProducer.sendClienteMessage(savedCliente); // Enviar mensaje al crear un cliente
        return savedCliente;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            Cliente updatedCliente = cliente.get();
            updatedCliente.setNombre(clienteDetails.getNombre());
            updatedCliente.setGenero(clienteDetails.getGenero());
            updatedCliente.setEdad(clienteDetails.getEdad());
            updatedCliente.setIdentificacion(clienteDetails.getIdentificacion());
            updatedCliente.setDireccion(clienteDetails.getDireccion());
            updatedCliente.setTelefono(clienteDetails.getTelefono());
            updatedCliente.setContraseña(clienteDetails.getContraseña());
            updatedCliente.setEstado(clienteDetails.isEstado());
            clienteRepository.save(updatedCliente);
            clienteMessageProducer.sendClienteMessage(updatedCliente); // Enviar mensaje al actualizar un cliente
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        clienteMessageProducer.sendClienteMessage(new Cliente()); // Enviar mensaje al eliminar un cliente
        return ResponseEntity.noContent().build();
    }
}
