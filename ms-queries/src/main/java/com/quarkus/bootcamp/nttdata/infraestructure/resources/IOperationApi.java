package com.quarkus.bootcamp.nttdata.infraestructure.resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.AccountD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.OperationD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/operation")
public interface IOperationApi {
  @GET
  List<OperationD> getAll(@QueryParam("accountId") Long accountId);
}
