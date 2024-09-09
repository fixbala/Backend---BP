package com.example.backendbp.controller;


import com.example.backendbp.entity.Cliente;
import com.example.backendbp.repository.ClienteRepository;
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

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
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
            return ResponseEntity.ok(updatedCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}