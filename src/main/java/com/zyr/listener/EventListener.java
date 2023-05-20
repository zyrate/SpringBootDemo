package com.zyr.listener;

import com.zyr.listener.event.ListenerEventStarted;
import com.zyr.listener.event.ListenerEventStopped;

import lombok.Data;
@Data
public abstract class EventListener {
    
    private int listenerId;

    public abstract void onStarted(ListenerEventStarted listenerEventStarted);

    public abstract void onStopped(ListenerEventStopped listenerEventStopped);
}
