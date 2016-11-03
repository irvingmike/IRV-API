package com.irvingmichael.irvapi.util;

import com.google.gson.Gson;
import com.irvingmichael.irvapi.entity.Poll;
import org.junit.Test;

/**
 * Created by Aaron Anderson on 11/2/16.
 */
public class GsonTesting {

    private String json  = "{\"title\":\"test api create poll name\",\"description\":\"api create poll test description\",\"creator\":11,\"choices\":[{\"name\":\"test api create poll choice 1 name\",\"description\":\"test api create poll choice 1 description\"},{\"name\":\"test api create poll choice 2 name\",\"description\":\"test api create poll choice 2 description\"},{\"name\":\"test api create poll choice 3 name\",\"description\":\"test api create poll choice 3 description\"},{\"name\":\"test api create poll choice 4 name\",\"description\":\"test api create poll choice 4 description\"}]}";
    private Gson gson = new Gson();

    @Test
    public void testGsonDeserialization() {
        Poll poll = gson.fromJson(json, Poll.class);
        System.out.println(poll.getTitle());
        System.out.println(poll.getChoices().get(0).getName());
    }
}
