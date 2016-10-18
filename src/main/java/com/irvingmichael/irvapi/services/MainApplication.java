package com.irvingmichael.irvapi.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Main application class for Instant Runoff Voting API
 *
 * @author Aaron Anderson
 */

@ApplicationPath("/irvapi")
public class MainApplication extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(MainService.class );
        return h;
    }
}
