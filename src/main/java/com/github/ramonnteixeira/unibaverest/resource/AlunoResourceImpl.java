package com.github.ramonnteixeira.unibaverest.resource;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.github.ramonnteixeira.unibaverest.dto.AlunoDTO;
import com.github.ramonnteixeira.unibaverest.model.Aluno;
import com.github.ramonnteixeira.unibaverest.service.AlunoService;

@Component
public class AlunoResourceImpl implements AlunoResource {

    @Inject
    private AlunoService service;

    @Override
    public Response lista(final String nome, final int page, 
            final int limit, final String sort, final String direction) {
        Pageable pageable = new PageRequest(page, limit, Sort.Direction.fromString(direction), sort);
        return Response.ok(service.lista(pageable, nome)).build();
    }

    @Override
    public Response busca(final Long id) {
        return Response.ok(new AlunoDTO(service.busca(id).orElseThrow(NotFoundException::new))).build();
    }

    @Override
    public Response adiciona(final Aluno aluno) {
        return Response.ok(service.adiciona(aluno)).build();
    }

    @Override
    public Response atualiza(final Long codigo, final Aluno aluno) {       
        return Response.ok(service.atualiza(codigo, aluno)).build();
    }

    @Override
    public Response deleta(final Long id) {
        service.deleta(id);
        return Response.noContent().build();
    }

}
