package com.github.ramonnteixeira.unibaverest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Aluno {

    @Id
    @GeneratedValue
    private Long codigo;
    
    @NotNull
    @Size(min = 3, max = 100)
    @Column(length=100, nullable=false)
    private String nome;
    
    @NotNull
    @Size(min = 3, max = 100)
    @Column(length=100, nullable=false)
    private String cidade;
    
    private Integer idade;

}
