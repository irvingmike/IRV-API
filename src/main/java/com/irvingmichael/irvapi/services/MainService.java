package com.irvingmichael.irvapi.services;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Main service class for Instant Runoff Voting API
 * @author Aaron Anderson
 */

@Path("/")
public class MainService {

    // Just for retrieving info
    @GET
    @Produces("application/json")
    public Response get() {
        String output = "{ \"result\":\"Success with a get!\" }";
        return Response.status(200).entity(output).build();
    }

    // For New
    @POST
    @Produces("application/json")
    public Response post() {
        String output = "{ \"result\":\"Success with a post!\" }";
        return Response.status(200).entity(output).build();
    }

    // For Updates
    @PUT
    @Produces("application/json")
    public Response put() {
        String output = "{ \"result\":\"Success with a put!\" }";
        return Response.status(200).entity(output).build();
    }

    // For Deleting
    @DELETE
    @Produces("application/json")
    public Response delete() {
        String output = "{ \"result\":\"Success with a delete!\" }";
        return Response.status(200).entity(output).build();
    }
}
