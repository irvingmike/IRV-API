package com.irvingmichael.irvapi.services;

import com.google.gson.Gson;
import com.irvingmichael.irvapi.entity.Voter;
import com.irvingmichael.irvapi.persistance.VoterDao;
import com.irvingmichael.irvapi.util.Secure;
import com.irvingmichael.irvapi.util.AuthToken;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Main service class for Instant Runoff Voting API
 * @author Aaron Anderson
 */

@Path("/")
public class MainService {

    private final Logger log = Logger.getLogger("debugLogger");

    @GET
    @Path("/getVoterByEmail")
    @Produces("application/json")
    public Response getVoterByEmail(@QueryParam("authtoken") String authtoken,
                                    @QueryParam("email") String suppliedEmail) {
        if (AuthToken.valid(authtoken)) {
            VoterDao voterDao = new VoterDao();
            Voter voter = voterDao.getVoterByEmail(suppliedEmail);
            Gson gson = new Gson();
            String jsonString = gson.toJson(voter);
            return Response.status(200).entity(jsonString).build();
        } else {
            return Response.status(400).entity("{ \"authtoken\":\"Bad token supplied\" }").build();
        }
    }

    @POST
    @Path("/createNewVoter")
    @Produces("text/plain")
    public Response createNewVoter(@QueryParam("authtoken") String authtoken,
                                   @QueryParam("firstname") String firstname,
                                   @QueryParam("lastname") String lastname,
                                   @QueryParam("email") String email,
                                   @QueryParam("password") String password) {
        if (AuthToken.valid(authtoken)) {
            Voter newVoter = new Voter(firstname, lastname, email);
            VoterDao voterDao = new VoterDao();
            int voterId = voterDao.create(newVoter);
            voterDao.setPasswordInDB(voterId, Secure.hash(password));
            Gson gson = new Gson();
            String jsonString = gson.toJson(voterId);
            return Response.status(200).entity(jsonString).build();
        } else {
            return Response.status(400).entity("{ \"authtoken\":\"Bad token supplied\" }").build();
        }
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(@QueryParam("username") String username,
                          @QueryParam("password") String password) {
        VoterDao voterDao = new VoterDao();
        if (voterDao.verifyUser(username, Secure.hash(password))) {
            return Response.status(200).entity("{ \"authtoken\":\"" + AuthToken.getNewToken() + "\" }").build();
        }
        return Response.status(400).entity(" { \"Bad username or password supplied\" }").build();
    }

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
