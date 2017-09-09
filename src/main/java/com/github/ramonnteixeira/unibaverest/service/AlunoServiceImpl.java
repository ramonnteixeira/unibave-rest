package com.github.ramonnteixeira.unibaverest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.ramonnteixeira.unibaverest.model.Aluno;
import com.github.ramonnteixeira.unibaverest.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Inject
    private AlunoRepository repository;
    
    @Override
    public List<Aluno> lista(String nome) {
        return nome != null ? 
                repository.findByNomeContaining(nome) : 
                StreamSupport.stream(repository.findAll().spliterator(), false)
                  .collect(Collectors.toList());
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
