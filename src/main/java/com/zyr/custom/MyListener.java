package com.zyr.custom;

import com.zyr.listener.EventListener;
import com.zyr.listener.event.EventWorkflowSubmitted;
import com.zyr.listener.event.EventTaskFinished;

public class MyListener implements EventListener{

    @Override
    public int getListenerId() {
        return 1001;
    }

    @Override
    public void onWorkflowSubmitted(EventWorkflowSubmitted eventWorkflowSubmitted) {
        System.out.println("Yes~ Workflow submitted!");
    }

    @Override
    public void onTaskFinished(EventTaskFinished eventTaskFinished) {
        System.out.println("Yes~ Task finished!");
    }
}
