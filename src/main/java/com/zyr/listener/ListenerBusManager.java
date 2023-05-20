package com.zyr.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zyr.listener.event.ListenerEvent;

@Component
public class ListenerBusManager {
    @Autowired
    private ListenerBus listenerBus;

    public void addListener(EventListener listener){
        listenerBus.addListener(listener);
    }

    public void postEvent(ListenerEvent event){
        listenerBus.postEvent(event);
    }

    public void startHandling(){
        listenerBus.run();
    }

}
