package com.zyr.listener.event;

import lombok.Data;

@Data
public class ListenerEventStopped extends ListenerEvent{
    @Override
    public String eventName(){ return "Stopped"; }
}
