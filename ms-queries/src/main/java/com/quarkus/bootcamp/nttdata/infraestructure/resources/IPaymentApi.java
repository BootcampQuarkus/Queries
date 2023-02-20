package com.quarkus.bootcamp.nttdata.infraestructure.resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.PaymentD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/payment")
public interface IPaymentApi {
  @GET
  List<PaymentD> getAll(@QueryParam("productId") Long productId);
}
