package br.com.seplag.integration.rest;

import br.com.seplag.exception.BusinessException;
import br.com.seplag.model.Categoria;
import br.com.seplag.service.CategoriaService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/categoria")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class CategoriaRestEndpoint {

    @Inject
    private CategoriaService service;

    @POST
    public Response save(@Valid Categoria categoria) {
        return Response.ok(service.save(categoria)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid Categoria categoria) {
        if (!id.equals(categoria.getId())) {
            return Response.status(Response.Status.CONFLICT).entity(categoria).build();
        }
        return Response.ok(service.update(categoria)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            throw new BusinessException("Falha ao tentar excluir um categoria");
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
