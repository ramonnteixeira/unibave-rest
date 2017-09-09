package com.github.ramonnteixeira.unibaverest.dto;

import com.github.ramonnteixeira.unibaverest.model.Aluno;

import lombok.Data;

@Data
public class AlunoDTO {

    private Long codigo;
    private String nome;
    private String cidade;

    public AlunoDTO(final Aluno aluno) {
        this.codigo = aluno.getCodigo();
        this.nome = aluno.getNome();
        this.cidade = aluno.getCidade();
    }
    
}
