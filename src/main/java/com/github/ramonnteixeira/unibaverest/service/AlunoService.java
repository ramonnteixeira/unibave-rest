package com.github.ramonnteixeira.unibaverest.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.ramonnteixeira.unibaverest.model.Aluno;

public interface AlunoService {

    Page<Aluno> lista(Pageable pageable, String nome);

    Optional<Aluno> busca(Long id);

    Aluno adiciona(Aluno aluno);

    Aluno atualiza(Long codigo, Aluno aluno);

    void deleta(Long id);

}
