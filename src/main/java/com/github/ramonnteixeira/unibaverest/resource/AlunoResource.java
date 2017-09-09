package com.github.ramonnteixeira.unibaverest.resource;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.ramonnteixeira.unibaverest.model.Aluno;

@Path("/alunos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AlunoResource {

	@GET
	Response lista(@QueryParam("nome") final String nome, 
	        @QueryParam("page") @DefaultValue("0") final int page,
	        @QueryParam("limit") @DefaultValue("10") final int limit,
	        @QueryParam("sort") @DefaultValue("codigo") final String sort,
	        @QueryParam("direction") @DefaultValue("asc") final String direction);

	@POST
	Response adiciona(@Valid final Aluno aluno);

	@PUT
    @Path("{codigo}")
	Response atualiza(@PathParam("codigo") final Long codigo, @Valid final Aluno aluno);

	@DELETE
    @Path("{codigo}")
	Response deleta(@PathParam("codigo") final Long id);

	@GET
    @Path("{codigo}")
	Response busca(@PathParam("codigo") final Long id);

}