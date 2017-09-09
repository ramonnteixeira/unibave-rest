package com.github.ramonnteixeira.unibaverest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.ramonnteixeira.unibaverest.model.Aluno;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	List<Aluno> findByNomeContaining(String nome);
	
}
