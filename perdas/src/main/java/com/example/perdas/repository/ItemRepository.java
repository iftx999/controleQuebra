package com.example.perdas.repository;

import com.example.perdas.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
