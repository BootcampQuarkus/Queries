package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.AccountOperation;
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

import java.util.List;

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
  public Products getCredit(@PathParam("id") Long id) {
    return service.getCredit(id);
  }

  @GET
  @Path("/lineofcredit/{id}")
  public Products getLineOfCredit(@PathParam("id") Long id) {
    return service.getLineOfCredit(id);
  }

  /*
  @GET
  @Path("/{id}")
  public Uni<String> getAll(@PathParam("id") Long id) {
    return Uni.createFrom().item("hello");
  }
  @GET
  @Path("/{id}")
  public Response getLineOfCredit(@PathParam("id") Long id) {
  }
  @GET
  @Path("/{id}")
  public Response getCredit(@PathParam("id") Long id) {
  }
  */
}
