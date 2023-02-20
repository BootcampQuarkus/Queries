package com.quarkus.bootcamp.nttdata.infraestructure.resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.LineOfCreditD;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/linesofcredit")
public interface ILineOfCreditApi {
  @GET
  Uni<List<LineOfCreditD>> getAll(@QueryParam("customerId") Long customerId);

  @GET
  @Path("/{id}")
  Uni<LineOfCreditD> getById(@PathParam("id") Long id);
}
