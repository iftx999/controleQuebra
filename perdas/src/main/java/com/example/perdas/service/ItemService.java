package com.example.perdas.service;

import com.example.perdas.model.Item;
import com.example.perdas.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Criar um novo item
    public Item salvarItem(Item item) {
        return itemRepository.save(item);
    }

    // Buscar todos os itens
    public List<Item> listarItens() {
        return itemRepository.findAll();
    }

    // Buscar item por ID
    public Optional<Item> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    // Atualizar um item existente
    public Item atualizarItem(Long id, Item itemAtualizado) {
        return itemRepository.findById(id).map(item -> {
            item.setNome(itemAtualizado.getNome());
            item.setDescricao(itemAtualizado.getDescricao());
            item.setQuantidade(itemAtualizado.getQuantidade());
            item.setValorUnitario(itemAtualizado.getValorUnitario());
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    // Deletar item por ID
    public void deletarItem(Long id) {
        itemRepository.deleteById(id);
    }
}
