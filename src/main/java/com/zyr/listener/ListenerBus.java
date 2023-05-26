package com.zyr.listener;

import com.zyr.listener.event.ListenerEvent;
import com.zyr.listener.event.EventWorkflowSubmitted;
import com.zyr.listener.event.EventStopListenerBus;
import com.zyr.listener.event.EventTaskFinished;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;
@Component
public class ListenerBus{
    private ConcurrentHashMap<Integer, EventListener> listeners = new ConcurrentHashMap<>();
    private LinkedBlockingDeque<ListenerEvent> queue = new LinkedBlockingDeque();
    private boolean isStarted = false;

    private Thread dispatchThread = null;

    private void dispatch() throws InterruptedException{
        System.out.println("ListenerBus started.");
        ListenerEvent nextEvent = queue.take();
        while(!nextEvent.getEventType().equals("STOP_LISTENERBUS")){
            for(EventListener listener : listeners.values()){
                handleEvent(listener, nextEvent);
            }
            nextEvent = queue.take();
        }
    }

    public void addListener(EventListener listener){
        listeners.put(listener.getListenerId(), listener);
        System.out.println("Add listener "+listener.getListenerId()+" to ListenerBus.");
    }

    public void removeListenerById(Integer listenerId){
        listeners.remove(listenerId);
    }

    public void handleEvent(EventListener listener, ListenerEvent event){
        switch(event.getEventType()){
            case "WORKFLOW_SUBMITTED": listener.onWorkflowSubmitted((EventWorkflowSubmitted) event); break;
            case "TASK_FINISHED": listener.onTaskFinished((EventTaskFinished) event); break;
            // and other event types...
        }
    }

    public void postEvent(ListenerEvent event){
        if(isStarted) {
            try {
                queue.put(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(){
        if(!isStarted){
            isStarted = true;
            dispatchThread = new Thread(){
                @Override
                public void run() {
                    try {
                        dispatch();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            dispatchThread.start();
        }
    }

    public void stop() {
        if(isStarted) {
            postEvent(new EventStopListenerBus());
            try {
                dispatchThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isStarted = false;
            System.out.println("ListenerBus stopped.");
        }
    }
}