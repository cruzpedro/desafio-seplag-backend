package br.com.seplag.integration.rest;

import br.com.seplag.exception.BusinessException;
import br.com.seplag.model.Usuario;
import br.com.seplag.service.UsuarioService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/usuario")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class UsuarioRestEndpoint {

    @Inject
    private UsuarioService service;

    @POST
    public Response save(@Valid Usuario usuario) {
        return Response.ok(service.save(usuario)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid Usuario usuario) {
        if (!id.equals(usuario.getId())) {
            return Response.status(Response.Status.CONFLICT).entity(usuario).build();
        }
        return Response.ok(service.update(usuario)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            throw new BusinessException("Falha ao tentar excluir um usuario");
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
