package com.github.ramonnteixeira.unibaverest.service;

import java.util.List;
import java.util.Optional;

import com.github.ramonnteixeira.unibaverest.model.Aluno;

public interface AlunoService {

    List<Aluno> lista(String nome);

    Optional<Aluno> busca(Long id);

    Aluno adiciona(Aluno aluno);

    Aluno atualiza(Long codigo, Aluno aluno);

    void deleta(Long id);

}
