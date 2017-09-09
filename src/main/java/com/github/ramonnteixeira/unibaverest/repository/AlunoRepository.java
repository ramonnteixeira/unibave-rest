package com.github.ramonnteixeira.unibaverest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.ramonnteixeira.unibaverest.model.Aluno;

@Repository
public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long> {

    Page<Aluno> findByNomeContaining(Pageable pageable, String nome);

}
