package com.zyr.listener;

import com.zyr.listener.event.EventWorkflowSubmitted;
import com.zyr.listener.event.EventTaskFinished;

public interface EventListener {
    
    int getListenerId();

    void onWorkflowSubmitted(EventWorkflowSubmitted eventWorkflowSubmitted);

    void onTaskFinished(EventTaskFinished eventTaskFinished);
}
