package com.github.ramonnteixeira.unibaverest.service;

import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.ramonnteixeira.unibaverest.model.Aluno;
import com.github.ramonnteixeira.unibaverest.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Inject
    private AlunoRepository repository;
    
    @Override
    public Page<Aluno> lista(Pageable pageable, String nome) {
        if (nome == null) {
            return repository.findAll(pageable);
        }
        
        return repository.findByNomeContaining(pageable, nome); 
    }

    @Override
    public Optional<Aluno> busca(Long id) {
        Aluno aluno = repository.findOne(id);
        return Optional.ofNullable(aluno);
    }

    @Override
    @Transactional
    public Aluno adiciona(Aluno aluno) {
        return repository.save(aluno);
    }

    @Override
    @Transactional
    public Aluno atualiza(Long codigo, Aluno aluno) {
        if (!codigo.equals(aluno.getCodigo())) {
            throw new IllegalArgumentException("Código inválido");
        }
        
        return repository.save(aluno);
    }

    @Override
    @Transactional
    public void deleta(Long id) {
        busca(id).ifPresent(repository::delete);
    }

}
