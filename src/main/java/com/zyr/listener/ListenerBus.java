package com.zyr.listener;

import com.zyr.listener.event.Event;

import java.util.ArrayList;
import java.util.List;

public class ListenerBus {
    private List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener){
        listeners.add(listener);
    }

    public void removeListener(EventListener listener){
        listeners.remove(listener);
    }

    public void postEvent(EventListener listener, Event event){

    }
}
