package com.example.perdas.service;

import com.example.perdas.model.Area;
import com.example.perdas.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    // Criar uma nova área
    public Area salvarArea(Area area) {
        return areaRepository.save(area);
    }

    // Listar todas as áreas
    public List<Area> listarAreas() {
        return areaRepository.findAll();
    }

    // Buscar área por ID
    public Optional<Area> buscarPorId(Long id) {
        return areaRepository.findById(id);
    }

    // Atualizar uma área existente
    public Area atualizarArea(Long id, Area areaAtualizada) {
        return areaRepository.findById(id).map(area -> {
            area.setNome(areaAtualizada.getNome());
            area.setDescricao(areaAtualizada.getDescricao());
            return areaRepository.save(area);
        }).orElseThrow(() -> new RuntimeException("Área não encontrada"));
    }

    // Deletar área por ID
    public void deletarArea(Long id) {
        areaRepository.deleteById(id);
    }
}
