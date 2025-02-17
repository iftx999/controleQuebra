package com.example.perdas.service;

import com.example.perdas.model.Funcionario;
import com.example.perdas.model.Item;
import com.example.perdas.model.Quebra;
import com.example.perdas.repository.FuncionarioRepository;
import com.example.perdas.repository.ItemRepository;
import com.example.perdas.repository.QuebraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class QuebraService {

    @Autowired
    private QuebraRepository quebraRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Quebra registrarQuebra(Quebra quebra) {
        Item item = itemRepository.findById(quebra.getItem().getId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado!"));

        if (item.getQuantidade() < quebra.getQuantidade()) {
            throw new RuntimeException("Quantidade insuficiente em estoque!");
        }

        item.setQuantidade(item.getQuantidade() - quebra.getQuantidade());
        itemRepository.save(item);

        return quebraRepository.save(quebra);
    }

    // Método para calcular o valor total de todas as quebras
    public BigDecimal calcularValorTotalQuebras() {
        return quebraRepository.findAll().stream()
                .map(Quebra::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Somando o valor total de todas as quebras
    }

    // Método para calcular a quantidade total de itens quebrados
    public long calcularQuantidadeTotalQuebras() {
        return quebraRepository.findAll().stream()
                .mapToLong(Quebra::getQuantidade)
                .sum(); // Somando a quantidade de itens quebrados
    }

    // Método para encontrar o funcionário que mais quebrou itens
    public Funcionario funcionarioQueMaisQuebrou() {
        return funcionarioRepository.findAll().stream()
                .max((f1, f2) -> Long.compare(calcularQuantidadeQuebrasFuncionario(f1), calcularQuantidadeQuebrasFuncionario(f2))) // Comparando a quantidade de quebras de cada funcionário
                .orElse(null);
    }

    // Método auxiliar para calcular a quantidade de quebras de um funcionário
    private long calcularQuantidadeQuebrasFuncionario(Funcionario funcionario) {
        return quebraRepository.findAll().stream()
                .filter(q -> q.getFuncionario().equals(funcionario))
                .mapToLong(Quebra::getQuantidade)
                .sum(); // Somando as quebras feitas por um funcionário
    }
}
