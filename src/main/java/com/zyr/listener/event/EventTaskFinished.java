package com.zyr.listener.event;

import lombok.Data;

@Data
public class EventTaskFinished extends ListenerEvent{
    @Override
    public String getEventType(){ return "TASK_FINISHED"; }
}
