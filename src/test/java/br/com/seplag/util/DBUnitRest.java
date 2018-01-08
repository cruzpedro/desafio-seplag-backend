package br.com.seplag.util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/database")
public class DBUnitRest {

    @GET
    @Path("create/{dataset}")
    public Response createDataset(@PathParam("dataset") String dataset) {
        try {
            DBUnitUtils.createDataset(dataset);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível criar o dataset. Menssagem:" + e.getMessage() + ". Cause:" + e.getCause()).build();
        }
        return Response.ok("dataset criado com sucesso!").build();
    }

    @GET
    @Path("delete/{dataset}")
    public Response deleteDataset(@PathParam("dataset") String dataset) {
        try {
            DBUnitUtils.deleteDataset(dataset);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível remover o dataset. Menssagem:" + e.getMessage() + ". Cause:" + e.getCause()).build();
        }
        return Response.ok("dataset removido com sucesso!").build();
    }
}
