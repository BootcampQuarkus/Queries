package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.AccountOperation;
import com.quarkus.bootcamp.nttdata.domain.entity.CreditOperation;
import com.quarkus.bootcamp.nttdata.domain.entity.LineOfCreditOperation;
import com.quarkus.bootcamp.nttdata.domain.entity.Products;
import com.quarkus.bootcamp.nttdata.domain.service.QueriesService;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.bson.Document;
import org.jboss.logging.Logger;

@Path("/query")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QueriesResources {
  @Inject
  Logger log;
  @Context
  HttpHeaders http;
  @Inject
  QueriesService service;
  @Inject
  ReactiveMongoClient client;

  private ReactiveMongoCollection<Document> getCollection() {
    return client.getDatabase("db-queries").getCollection("audits");
  }

  @GET
  @Path("/{id}")
  public Products getAll(@PathParam("id") Long id) {
    Document document = new Document()
          .append("header", http.getRequestHeaders().toString());
    getCollection().insertOne(document).onItem().ignore().andContinueWithNull();
    Products products = service.getAll(id);
    document = new Document()
          .append("response", products.toString());
    getCollection().insertOne(document).onItem().ignore().andContinueWithNull();
    return products;
  }

  @GET
  @Path("/account/{id}")
  public AccountOperation getAccount(@PathParam("id") Long accountId) {
    return service.getAccount(accountId);
  }

  @GET
  @Path("/credit/{id}")
  public CreditOperation getCredit(@PathParam("id") Long id) {
    return service.getCredit(id);
  }

  @GET
  @Path("/lineofcredit/{id}")
  public LineOfCreditOperation getLineOfCredit(@PathParam("id") Long id) {
    return service.getLineOfCredit(id);
  }

}
