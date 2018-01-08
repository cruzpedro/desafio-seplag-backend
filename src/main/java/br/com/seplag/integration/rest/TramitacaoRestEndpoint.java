package br.com.seplag.integration.rest;

import br.com.seplag.exception.BusinessException;
import br.com.seplag.model.Tramitacao;
import br.com.seplag.service.TramitacaoService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/tramitacao")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class TramitacaoRestEndpoint {

    @Inject
    private TramitacaoService service;

    @POST
    public Response save(@Valid Tramitacao tramitacao) {
        return Response.ok(service.save(tramitacao)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid Tramitacao tramitacao) {
        if (!id.equals(tramitacao.getId())) {
            return Response.status(Response.Status.CONFLICT).entity(tramitacao).build();
        }
        return Response.ok(service.update(tramitacao)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            throw new BusinessException("Falha ao tentar excluir um tramitacao");
        }
    }

    @GET
    public Response getAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.get(id)).build();
    }
}
