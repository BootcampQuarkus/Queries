package com.quarkus.bootcamp.nttdata.infraestructure.resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.ShoppingD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/shopping")
public interface IShoppingApi {
  @GET
  List<ShoppingD> getAll(@QueryParam("accountId") Long accountId);
}
