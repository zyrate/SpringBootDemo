package com.zyr.custom;

import com.zyr.listener.EventListener;
import com.zyr.listener.event.ListenerEventStarted;
import com.zyr.listener.event.ListenerEventStopped;

public class MyListener extends EventListener{

    @Override
    public void onStarted(ListenerEventStarted listenerEventStarted) {
        System.out.println("Now the application is started!!!");
    }

    @Override
    public void onStopped(ListenerEventStopped listenerEventStopped) {
        System.out.println("Stopped !!!");
    }
    
}
