package com.irvingmichael.irvapi.services;

import com.google.gson.Gson;
import com.irvingmichael.irvapi.entity.Voter;
import com.irvingmichael.irvapi.persistance.VoterDao;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Aaron Anderson on 10/28/16.
 */

@Path("/getVoterByEmail")
public class GetVoterByEmail {

    private final Logger log = Logger.getLogger("debugLogger");

    @GET
    @Produces("application/json")
    public Response getMessage(@QueryParam("email") String suppliedEmail) {
        log.debug("In getVoterByEmail");
        VoterDao voterDao = new VoterDao();
        Voter voter  = voterDao.getVoterByEmail(suppliedEmail);
        Gson gson = new Gson();
        String jsonString = gson.toJson(voter);
        return Response.status(200).entity(jsonString).build();
    }
}
