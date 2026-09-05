package com.example.demo.Service;

import com.example.demo.model.Tarefa;
import com.example.demo.Repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository TarefaRepository;

    // 1. Cadastrar uma nova tarefa
    public Tarefa salvar(Tarefa tarefa) {
        return TarefaRepository.save(tarefa);
    }

    // 2. Listar todas as tarefas
    public List<Tarefa> listarTodas() {
        return TarefaRepository.findAll();
    }

    // 3. Buscar uma tarefa pelo ID
    public Optional<Tarefa> buscarPorId(Long id) {
        return TarefaRepository.findById(id);
    }

    // 4. Atualizar uma tarefa
    public Optional<Tarefa> atualizar(Long id, Tarefa tarefaAtualizada) {
        return TarefaRepository.findById(id).map(tarefaExistente -> {
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
            tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
            return TarefaRepository.save(tarefaExistente);
        });
    }

    // 5. Remover uma tarefa
    public boolean deletar(Long id) {
        if (TarefaRepository.existsById(id)) {
            TarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}