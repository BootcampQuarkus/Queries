package com.quarkus.bootcamp.nttdata.infraestructure.resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.products.AccountD;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/accounts")
public interface IAccountApi {
  @GET
  Uni<List<AccountD>> getAll(@QueryParam("customerId") Long customerId);

  @GET
  @Path("/{id}")
  Uni<AccountD> getById(@PathParam("id") Long id);
}
