package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Poll;

/**
 * Created by Aaron Anderson on 10/9/16.
 */
public class PollDao extends GenericDao {

    public PollDao() {
        super(Poll.class);
    }
}
