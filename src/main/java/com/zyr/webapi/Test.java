package com.zyr.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyr.custom.MyListener;
import com.zyr.listener.ListenerBusManager;
import com.zyr.listener.event.ListenerEventStarted;
import com.zyr.listener.event.ListenerEventStopped;

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
        listenerBusManager.startHandling();
        listenerBusManager.addListener(new MyListener());
    }
    

    @RequestMapping("/post")
    @ResponseBody
    public void postEvent(){
        listenerBusManager.postEvent(new ListenerEventStarted());
        listenerBusManager.postEvent(new ListenerEventStopped());
    }

    //热加载
    @RequestMapping("/hotload")
    @ResponseBody
    public String hotload(){
        String pluginClass="com.example.demo.DemoHandler";
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader("file:/D:/demo-1.0.jar");

        Class<?> clazz = classLoader.loadClass(pluginClass);

        SpringUtil.registerBean("demoHandler",clazz);

        IDemoHandler plugin = (IDemoHandler)SpringUtil.getBean("demoHandler");

        String str= plugin.handler("RAY");
        return "OK";
    }
}
