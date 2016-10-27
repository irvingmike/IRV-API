package com.irvingmichael.irvapi.entity;

/**
 * Created by aaron on 9/10/16.
 */
public enum PollStatus {

    INITIAL,
    OPEN,
    CLOSED,
    COMPLETED;

    public String getClassString() {
        String classString = null;
        switch (this) {
            case INITIAL: { classString = "initial"; }
            case OPEN: { classString = "open"; }
            case CLOSED: { classString = "closed"; }
            case COMPLETED: { classString = "completed"; }
        }
        return classString;
    }
}
