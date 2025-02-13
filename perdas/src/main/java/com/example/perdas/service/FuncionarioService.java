package com.example.perdas.service;

import com.example.perdas.model.Funcionario;
import com.example.perdas.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Criar um novo funcionário
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Buscar funcionário por ID
    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    // Atualizar um funcionário existente
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setCargo(funcionarioAtualizado.getCargo());
            return funcionarioRepository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    // Deletar funcionário por ID
    public void deletarFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
}