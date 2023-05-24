package com.zyr.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ListenerController {
    @Autowired
    private ListenerService listenerService;

    @RequestMapping("/register/{id}")
    @ResponseBody
    public String register(@PathVariable int id){
        return listenerService.register(id);
    }
    @RequestMapping("/master/return/{id}/error")
    @ResponseBody
    public String returnError(@PathVariable int id){
        RegisterHandler handler = listenerService.getCacheMap().get(id);
        handler.exception();
        return "send";
    }
    @RequestMapping("/master/return/{lid}/success/{mid}")
    @ResponseBody
    public String returnSuccess(@PathVariable int lid,@PathVariable int mid){
        RegisterHandler handler = listenerService.getCacheMap().get(lid);
        if(handler.success(mid)){
            listenerService.getCacheMap().remove(lid);
        }
        return "send";
    }

}
