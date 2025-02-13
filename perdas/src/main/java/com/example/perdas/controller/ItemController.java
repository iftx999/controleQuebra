package com.example.perdas.controller;

import com.example.perdas.model.Item;
import com.example.perdas.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Criar um novo item
    @PostMapping
    public ResponseEntity<Item> criarItem(@RequestBody Item item) {
        Item novoItem = itemService.salvarItem(item);
        return ResponseEntity.ok(novoItem);
    }

    // Buscar todos os itens
    @GetMapping
    public ResponseEntity<List<Item>> listarItens() {
        List<Item> itens = itemService.listarItens();
        return ResponseEntity.ok(itens);
    }

    // Buscar item por ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarItemPorId(@PathVariable Long id) {
        Optional<Item> item = itemService.buscarPorId(id);
        return item.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar um item existente
    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable Long id, @RequestBody Item itemAtualizado) {
        try {
            Item item = itemService.atualizarItem(id, itemAtualizado);
            return ResponseEntity.ok(item);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar item por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        itemService.deletarItem(id);
        return ResponseEntity.noContent().build();
    }
}