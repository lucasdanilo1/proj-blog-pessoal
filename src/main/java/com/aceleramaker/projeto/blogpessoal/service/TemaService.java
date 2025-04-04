package com.aceleramaker.projeto.blogpessoal.service;

import com.aceleramaker.projeto.blogpessoal.controller.schema.AtualizaTemaDTO;
import com.aceleramaker.projeto.blogpessoal.controller.schema.TemaDTO;
import com.aceleramaker.projeto.blogpessoal.model.Tema;
import com.aceleramaker.projeto.blogpessoal.model.exception.EntidadeNaoEncontradaException;
import com.aceleramaker.projeto.blogpessoal.repository.TemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemaService {

    private final TemaRepository temaRepository;

    public TemaService(TemaRepository temaRepository) {
        this.temaRepository = temaRepository;
    }

    public TemaDTO criar(Tema tema) {
        return new TemaDTO(temaRepository.save(tema));
    }

    public TemaDTO atualizar(Long id, AtualizaTemaDTO dto) {
        var tema = temaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Tema não encontrado"));

        if (dto.descricao() != null) tema.setDescricao(dto.descricao());

        return new TemaDTO(temaRepository.save(tema));
    }

    public void deletar(Long id) {
        temaRepository.deleteById(id);
    }

    public List<Tema> listarTodos() {
        return temaRepository.findAll();
    }
}