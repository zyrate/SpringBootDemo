package com.zyr.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
    @Autowired
    private MyService myService;
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
}
