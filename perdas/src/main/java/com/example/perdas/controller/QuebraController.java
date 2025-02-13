package com.example.perdas.controller;

import com.example.perdas.model.DashboardDTO;
import com.example.perdas.model.Quebra;
import com.example.perdas.service.QuebraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class QuebraController {

    @Autowired
    private QuebraService quebraService;

    @PostMapping("/quebras")
    public Quebra registrarQuebra(@RequestBody Quebra quebra) {
        return quebraService.registrarQuebra(quebra);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> mostrarDashboard() {
        BigDecimal valorTotalQuebras = quebraService.calcularValorTotalQuebras();
        long quantidadeTotalQuebras = quebraService.calcularQuantidadeTotalQuebras();
        String funcionarioMaisQuebrou = quebraService.funcionarioQueMaisQuebrou().getNome();

        DashboardDTO dashboardDTO = new DashboardDTO(valorTotalQuebras, quantidadeTotalQuebras, funcionarioMaisQuebrou);

        return ResponseEntity.ok(dashboardDTO);
    }
}