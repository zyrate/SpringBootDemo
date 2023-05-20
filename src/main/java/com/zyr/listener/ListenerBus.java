package com.zyr.listener;

import com.zyr.listener.event.ListenerEvent;
import com.zyr.listener.event.ListenerEventStarted;
import com.zyr.listener.event.ListenerEventStopped;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;
@Component
public class ListenerBus implements Runnable{
    private ConcurrentHashMap<Integer, EventListener> listeners = new ConcurrentHashMap<>();
    private ConcurrentLinkedQueue<ListenerEvent> queue = new ConcurrentLinkedQueue<>();
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
        queue.offer(event);
    }

    @Override
    public void run() {
        isStarted = true;
        while(isStarted){
            ListenerEvent event = queue.poll();
            for(EventListener listener : listeners.values()){
                handleEvent(listener, event);
            }
        }
    }
}
