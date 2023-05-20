package com.zyr.listener.event;

import lombok.Data;

@Data
public class ListenerEventStarted extends ListenerEvent{
    @Override
    public String eventName(){ return "Started"; }
}
