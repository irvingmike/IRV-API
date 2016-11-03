package com.irvingmichael.irvapi.services;

import com.google.gson.Gson;
import com.irvingmichael.irvapi.entity.Choice;
import com.irvingmichael.irvapi.entity.Poll;
import com.irvingmichael.irvapi.entity.Voter;
import com.irvingmichael.irvapi.persistance.ChoiceDao;
import com.irvingmichael.irvapi.persistance.PollDao;
import com.irvingmichael.irvapi.persistance.VoterDao;
import com.irvingmichael.irvapi.util.Secure;
import com.irvingmichael.irvapi.util.AuthToken;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.core.Response.Status;

/**
 * Main service class for Instant Runoff Voting API
 * @author Aaron Anderson
 */

@Path("/")
public class MainService {

    private final Logger log = Logger.getLogger("debugLogger");

    /**
     * Login with existing account
     * @param username Email address for account to access
     * @param password Password for account
     * @return Response sent back to requestor as JSON
     */
    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(@QueryParam("username") String username,
                          @QueryParam("password") String password) {
        String jsonToReturn = "Help! Help! I need an admin! So I can show them in the logs where the bad code touched me!";
        Status status = Status.BAD_REQUEST;
        VoterDao voterDao = new VoterDao();
        if (voterDao.verifyUser(username, Secure.hash(password))) {
            jsonToReturn = "{ \"authtoken\":\"" + AuthToken.getNewToken() + "\" }";
            status = Status.OK;
        } else {
            status = Status.UNAUTHORIZED;
            jsonToReturn = "{ \"result\":\"Login failed\" }";
        }
        return Response.status(status.getStatusCode()).entity(jsonToReturn).build();
    }

    /**
     * Method to get a single voter by supplying an id
     * @param authtoken Token required to access the API
     * @param suppliedEmail Email for voter to retrieve
     * @return Response sent requestor as JSON
     */
    @GET
    @Path("/getVoterByEmail")
    @Produces("application/json")
    public Response getVoterByEmail(@QueryParam("authtoken") String authtoken,
                                    @QueryParam("email") String suppliedEmail) {

        String jsonToReturn = "Help! Help! I need an admin! So I can show them in the logs where the bad code touched me!";
        Status status = Status.BAD_REQUEST;

        if (AuthToken.valid(authtoken)) {
            VoterDao voterDao = new VoterDao();
            Voter voter = voterDao.getVoterByEmail(suppliedEmail);
            Gson gson = new Gson();
            jsonToReturn = gson.toJson(voter);
            status = Status.OK;
        } else {
            status = Status.UNAUTHORIZED;
            jsonToReturn = "{ \"result\":\"Bad token supplied\" }";
        }

        return Response.status(status.getStatusCode()).entity(jsonToReturn).build();
    }

    /**
     * Add a new user to the database
     * @param authtoken Token required to access the API
     * @param firstname New voter first name
     * @param lastname New voter last name
     * @param email New voter email
     * @param password New voter password
     * @return Response sent requestor as JSON
     */
    @POST
    @Path("/createNewVoter")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createNewVoter(@QueryParam("authtoken") String authtoken,
                                   @QueryParam("firstname") String firstname,
                                   @QueryParam("lastname") String lastname,
                                   @QueryParam("email") String email,
                                   @QueryParam("password") String password,
                                   String jsonVoter) {
        String jsonToReturn = "Help! Help! I need an admin! So I can show them in the logs where the bad code touched me!";
        Status status = Status.BAD_REQUEST;

        if (AuthToken.valid(authtoken)) {

            Gson gson = new Gson();
            Voter newVoter = new Voter(firstname, lastname, email);

            if(!jsonVoter.isEmpty()) {
                newVoter = gson.fromJson(jsonVoter, Voter.class);
            }

            VoterDao voterDao = new VoterDao();
            int voterId = voterDao.create(newVoter);
            voterDao.setPasswordInDB(voterId, Secure.hash(password));

            jsonToReturn = "{ \"voterId\": \"" + voterId + "\" }";
            status = Status.OK;

        } else {

            status = Status.UNAUTHORIZED;
            jsonToReturn = "{ \"result\":\"Bad token supplied\" }";

        }
        return Response.status(status.getStatusCode()).entity(jsonToReturn).build();
    }

    /**
     * Create new poll and choices, poll and choices must be supplied as a JSON in the body with authtoken as a param.
     * @param authtoken Token required to access the API
     * @param newPollJson JSON from body of request
     * @return Response with new poll as json
     */
    @POST
    @Path("/createNewPoll")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createNewPoll(@QueryParam("authtoken") String authtoken,
                                  String newPollJson) {
        String jsonToReturn = "Help! Help! I need an admin! So I can show them in the logs where the bad code touched me!";
        Status status = Status.BAD_REQUEST;
        if (AuthToken.valid(authtoken)) {
            Gson gson = new Gson();
            PollDao pollDao = new PollDao();
            ChoiceDao choiceDao = new ChoiceDao();

            Poll poll = gson.fromJson(newPollJson, Poll.class);
            poll.setAvailable(false);
            poll.setPollid(pollDao.create(poll));

            List<Choice> choices = poll.getChoices();
            for (Choice choice : choices) {
                choice.setPollId(poll.getPollid());
                choiceDao.create(choice);
            }

            jsonToReturn = gson.toJson(poll);
            status = Status.OK;

        } else {
            status = Status.UNAUTHORIZED;
            jsonToReturn = "{ \"result\":\"Bad token supplied\" }";
        }

        return Response.status(status.getStatusCode()).entity(jsonToReturn).build();
    }

    /**
     * Returns a specific poll as json based on pollid provided
     * @param authtoken Token required to access the API
     * @param pollId Id of poll to return
     * @return Poll as JSON
     */
    @GET
    @Path("/getPollById")
    @Produces("application/json")
    public Response getPollById(@QueryParam("authtoken") String authtoken,
                                @QueryParam("pollid") int pollId) {
        String jsonToReturn = "Help! Help! I need an admin! So I can show them in the logs where the bad code touched me!";
        Status status = Status.BAD_REQUEST;
        if (AuthToken.valid(authtoken)) {
            try {
                PollDao pollDao = new PollDao();
                Gson gson = new Gson();
                Poll poll = (Poll) pollDao.getById(pollId);
                jsonToReturn = gson.toJson(poll);
                status = Status.OK;
            } catch (Exception e) {
                log.error(e);
                status = Status.BAD_REQUEST;
                jsonToReturn = "{ \"result\":\"Poll doesn't exist\" }";
            }
        } else {
            status = Status.UNAUTHORIZED;
            jsonToReturn = "{ \"result\":\"Bad token supplied\" }";        }

        return Response.status(status.getStatusCode()).entity(jsonToReturn).build();
    }

   /**
     *
     * @param authtoken Token required to access the API
     * @param pollcode Pollcode for poll to register with
     * @param stringId VoterId for voter to register
     * @return Response sent back to requestor as JSON
     */
    @POST
    @Path("/registerWithPoll")
    @Produces("application/json")
    public Response registerForPollWithCode(@QueryParam("authToken") String authtoken,
                                            @QueryParam("pollcode") String pollcode,
                                            @QueryParam("voterid") String stringId) {
        String jsonToReturn = "Help! Help! I need an admin! So I can show them in the logs where the bad code touched me!";
        Status status = Status.BAD_REQUEST;

        if (AuthToken.valid(authtoken)) {
            VoterDao voterDao = new VoterDao();
            PollDao pollDao = new PollDao();
            int voterId = Integer.parseInt(stringId);
            if (!voterDao.validateVoterId(voterId)) {
                jsonToReturn = " { \"Bad voterId supplied\" }";
                status = Status.BAD_REQUEST;
            } else if (pollcode.matches("^[a-zA-Z0-9]{8}$") && pollDao.registerVoterForPoll(pollcode, voterId)) {
                jsonToReturn = "{ \"result\":\"success\" }";
                status = Status.OK;
            } else {
                jsonToReturn = " { \"Bad poll code supplied\" }";
                status = Status.BAD_REQUEST;
            }
        } else {
            status = Status.UNAUTHORIZED;
            jsonToReturn = "{ \"result\":\"Bad token supplied\" }";
        }
        return Response.status(status.getStatusCode()).entity(jsonToReturn).build();
    }

    /* EXAMPLES FOR REFERENCE ONLY
    // Just for basic request setup testing
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
    */
}
