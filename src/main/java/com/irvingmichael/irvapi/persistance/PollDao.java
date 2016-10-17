package com.irvingmichael.irvapi.persistance;

import com.irvingmichael.irvapi.entity.Poll;
import com.irvingmichael.irvapi.persistance.GenericDao;

/**
 * Created by Aaron Anderson on 10/9/16.
 */
public class PollDao extends GenericDao {

    public PollDao() {
        super(Poll.class);
    }
}
