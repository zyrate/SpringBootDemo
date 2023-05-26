package com.zyr.listener.event;

public class EventStopListenerBus extends ListenerEvent{

    @Override
    public String getEventType() {
        return "STOP_LISTENERBUS";
    }
    
}
