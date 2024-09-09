package com.example.backendbp.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "cliente")
public class Cliente extends Persona {
    @Column(unique = true, nullable = false)
    private String clienteId;
    private String contraseña;
    private boolean estado;

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
// Getters y Setters
}