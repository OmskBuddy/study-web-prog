package ru.itmo.wp.model.domain;

import java.util.Date;

public class Event {

    private long id;
    private long userId;
    private Type type;
    private Date creationTime;

    public enum Type {
        ENTER, LOGOUT
    }
}
