package com.example.backendbp.controller;


import com.example.backendbp.entity.Movimientos;
import com.example.backendbp.repository.MovimientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    @Autowired
    private MovimientosRepository movimientosRepository;

    // Crear un nuevo movimiento
    @PostMapping
    public ResponseEntity<Movimientos> createMovimiento(@RequestBody Movimientos movimiento) {
        Movimientos savedMovimiento = movimientosRepository.save(movimiento);
        return new ResponseEntity<>(savedMovimiento, HttpStatus.CREATED);
    }

    // Obtener todos los movimientos
    @GetMapping
    public ResponseEntity<List<Movimientos>> getAllMovimientos() {
        List<Movimientos> movimientos = movimientosRepository.findAll();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    // Obtener un movimiento por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Movimientos> getMovimientoById(@PathVariable Long id) {
        Optional<Movimientos> movimiento = movimientosRepository.findById(id);
        if (movimiento.isPresent()) {
            return new ResponseEntity<>(movimiento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar un movimiento existente
    @PutMapping("/{id}")
    public ResponseEntity<Movimientos> updateMovimiento(@PathVariable Long id, @RequestBody Movimientos updatedMovimiento) {
        Optional<Movimientos> existingMovimiento = movimientosRepository.findById(id);
        if (existingMovimiento.isPresent()) {
            Movimientos movimiento = existingMovimiento.get();
            movimiento.setTipoMovimiento(updatedMovimiento.getTipoMovimiento());
            movimiento.setValor(updatedMovimiento.getValor());
            movimiento.setFecha(updatedMovimiento.getFecha());
            movimiento.setCuenta(updatedMovimiento.getCuenta());  // Actualizar la cuenta relacionada
            movimiento.setSaldo(updatedMovimiento.getSaldo());

            Movimientos savedMovimiento = movimientosRepository.save(movimiento);
            return new ResponseEntity<>(savedMovimiento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un movimiento por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        Optional<Movimientos> movimiento = movimientosRepository.findById(id);
        if (movimiento.isPresent()) {
            movimientosRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Método adicional: Obtener movimientos por cuenta
    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<Movimientos>> getMovimientosByCuenta(@PathVariable Long cuentaId) {
        List<Movimientos> movimientos = movimientosRepository.findByCuentaId(cuentaId);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    // Método adicional: Obtener movimientos por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Movimientos>> getMovimientosByTipo(@PathVariable String tipo) {
        List<Movimientos> movimientos = movimientosRepository.findByTipoMovimiento(tipo);
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }
}
