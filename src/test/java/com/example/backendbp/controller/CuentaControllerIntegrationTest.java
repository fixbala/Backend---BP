package com.example.backendbp.controller;

import com.example.backendbp.entity.Cuenta;
import com.example.backendbp.repository.CuentaRepository;
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
public class CuentaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CuentaRepository cuentaRepository;

    @BeforeEach
    public void setup() {
        cuentaRepository.deleteAll();  // Limpiar la base de datos antes de cada prueba
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("478758");
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(2000);
        cuenta.setEstado(true);
        cuentaRepository.save(cuenta);
    }

    @Test
    public void testGetAllCuentas() throws Exception {
        mockMvc.perform(get("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCuenta").value("478758"));
    }

    @Test
    public void testCreateCuenta() throws Exception {
        String cuentaJson = "{ \"numeroCuenta\": \"585545\", \"tipoCuenta\": \"Corriente\", \"saldoInicial\": 1000, \"estado\": true }";

        ResultActions resultActions = mockMvc.perform(post("/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cuentaJson));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCuenta").value("585545"));
    }
}
