package com.example.perdas.controller;

import com.example.perdas.model.Area;
import com.example.perdas.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/areas")
@CrossOrigin(origins = "*") // Permite chamadas do frontend
public class AreaController {

    @Autowired
    private AreaService areaService;

    // Criar uma nova área
    @PostMapping
    public ResponseEntity<Area> criarArea(@RequestBody Area area) {
        Area novaArea = areaService.salvarArea(area);
        return ResponseEntity.ok(novaArea);
    }

    // Listar todas as áreas
    @GetMapping
    public ResponseEntity<List<Area>> listarAreas() {
        List<Area> areas = areaService.listarAreas();
        return ResponseEntity.ok(areas);
    }

    // Buscar área por ID
    @GetMapping("/{id}")
    public ResponseEntity<Area> buscarAreaPorId(@PathVariable Long id) {
        Optional<Area> area = areaService.buscarPorId(id);
        return area.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar uma área existente
    @PutMapping("/{id}")
    public ResponseEntity<Area> atualizarArea(@PathVariable Long id, @RequestBody Area areaAtualizada) {
        try {
            Area area = areaService.atualizarArea(id, areaAtualizada);
            return ResponseEntity.ok(area);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar área por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArea(@PathVariable Long id) {
        areaService.deletarArea(id);
        return ResponseEntity.noContent().build();
    }
}
