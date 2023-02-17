package com.quarkus.bootcamp.nttdata.infraestructure.resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.CreditD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/credits")
public interface ICreditApi {
  @GET
  List<CreditD> getAll(@QueryParam("customerId") Long customerId);
}
