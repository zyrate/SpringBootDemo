package com.zyr.listener.event;

import lombok.Data;

@Data
public abstract class ListenerEvent {
    private int eventId;
    abstract public String eventName();
}
