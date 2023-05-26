package com.zyr.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyr.custom.MyListener;
import com.zyr.listener.EventListener;
import com.zyr.listener.ListenerBusManager;
import com.zyr.listener.event.EventWorkflowSubmitted;
import com.zyr.listener.event.EventTaskFinished;
import com.zyr.util.Util;

@Controller
public class Test {
    @Autowired
    private MyService myService;

    @Autowired
    private ListenerBusManager listenerBusManager;

    @RequestMapping("/test")
    @ResponseBody
    public String hello(){
        int a = myService.test();
        return "hello there. the num: "+a;
    }

    @RequestMapping("/countdown")
    @ResponseBody
    public void countdown(){
        myService.countdown();
    }
    @RequestMapping("/break")
    @ResponseBody
    public void breakwait(){
        myService.breakwait();
    }


    @RequestMapping("/addlistener")
    @ResponseBody
    public void addListener(){
        listenerBusManager.addListener(new MyListener());
    }

    @RequestMapping("/start")
    @ResponseBody
    public void start(){
        listenerBusManager.startBus();
    }
    
    @RequestMapping("/stop")
    @ResponseBody
    public void stop(){
        listenerBusManager.stopBus();
    }
    

    @RequestMapping("/post")
    @ResponseBody
    public void postEvent(){
        listenerBusManager.postEvent(new EventWorkflowSubmitted());
        listenerBusManager.postEvent(new EventTaskFinished());
    }



    //热加载
    @RequestMapping("/hotload")
    @ResponseBody
    public String hotload() throws Exception{
        EventListener myListener = (EventListener)Util.loadClassFromJar("file:///D:\\Desktop\\UserCustomTest.jar", "com.xxx.MyListener");
        listenerBusManager.addListener(myListener);
        return "OK";
    }
}
