package com.example.backendbp.repository;

import com.example.backendbp.cuenta.entity.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
    List<Movimientos> findByCuentaId(Long cuentaId);
    List<Movimientos> findByTipoMovimiento(String tipoMovimiento);
}