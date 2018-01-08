package br.com.seplag.integration.rest;

import br.com.seplag.exception.BusinessException;
import br.com.seplag.model.Beneficio;
import br.com.seplag.service.BeneficioService;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/beneficio")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class BeneficioRestEndPoint {

    @Inject
    private BeneficioService service;

    @POST
    public Response save(@Valid Beneficio beneficio, MultipartFormDataInput input) {
        service.save(beneficio, input);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid Beneficio beneficio) {
        if (!id.equals(beneficio.getId())) {
            return Response.status(Response.Status.CONFLICT).entity(beneficio).build();
        }
        return Response.ok(service.update(beneficio)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            throw new BusinessException("Falha ao tentar excluir um beneficio");
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
