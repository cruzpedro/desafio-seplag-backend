package br.com.seplag.integration.rest;

import br.com.seplag.exception.BusinessException;
import br.com.seplag.model.Beneficio;
import br.com.seplag.model.Movimento;
import br.com.seplag.service.BeneficioService;
import br.com.seplag.service.MovimentoService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/movimento")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class MovimentoRestEndpoint {

    @Inject
    private MovimentoService service;

    @Inject
    private BeneficioService beneficioService;

    @POST
    public Response save(@Valid Movimento movimento) {
        return Response.ok(service.save(movimento)).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid Movimento movimento) {
        if (!id.equals(movimento.getId())) {
            return Response.status(Response.Status.CONFLICT).entity(movimento).build();
        }
        return Response.ok(service.update(movimento)).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.remove(id);
            return Response.ok().build();
        } catch (Exception e) {
            throw new BusinessException("Falha ao tentar excluir um movimento");
        }
    }

    @GET
    public Response getAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("buscarPorBeneficio/{idBeneficio}")
    public Response buscarPorBeneficio(@PathParam("idBeneficio") Long id) {
        Beneficio beneficio = beneficioService.get(id);
        return  Response.ok(service.buscarPorBeneficio(beneficio)).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.get(id)).build();
    }
}
