package com.zyr.listener.event;

import lombok.Data;

@Data
public class EventWorkflowSubmitted extends ListenerEvent{
    @Override
    public String getEventType(){ return "WORKFLOW_SUBMITTED"; }
}
