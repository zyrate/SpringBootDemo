package com.zyr.listener;

import com.zyr.listener.event.ListenerEvent;
import com.zyr.listener.event.ListenerEventStarted;
import com.zyr.listener.event.ListenerEventStopped;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;
@Component
public class ListenerBus{
    private ConcurrentHashMap<Integer, EventListener> listeners = new ConcurrentHashMap<>();
    private LinkedBlockingDeque<ListenerEvent> queue = new LinkedBlockingDeque();
    private boolean isStarted = false;

    public void addListener(EventListener listener){
        listeners.put(listener.getListenerId(), listener);
    }

    public void removeListenerById(Integer listenerId){
        listeners.remove(listenerId);
    }

    public void handleEvent(EventListener listener, ListenerEvent event){
        switch(event.eventName()){
            case "Started": listener.onStarted((ListenerEventStarted) event); break;
            case "Stopped": listener.onStopped((ListenerEventStopped) event); break;
        }
    }

    public void postEvent(ListenerEvent event){
        try {
            queue.put(event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        isStarted = true;
        new Thread(){
            @Override
            public void run() {
                while(isStarted){
                    ListenerEvent event;
                    try {
                        event = queue.take();
                        for(EventListener listener : listeners.values()){
                            handleEvent(listener, event);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    
                }
            }
        }.start();
    }

    public void stop(){
        isStarted = false;
    }
}
