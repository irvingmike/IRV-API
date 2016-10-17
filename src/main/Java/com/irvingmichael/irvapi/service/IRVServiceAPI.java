package com.irvingmichael.irvapi.service;

/**
 * Created by Aaron Anderson on 10/17/16.
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/API-Service")
public class IRVServiceAPI {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getMessage() {
        // Return a simple message
        String output = "Hello World";
        return Response.status(200).entity(output).build();
    }
}
