package com.zyr.webapi;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ListenerService {
    private Map<Integer, RegisterHandler> cacheMap = new HashMap<>();
    public String register(int id){
        RegisterHandler handler = new RegisterHandler(id);
        cacheMap.put(id, handler);
        synchronized (handler){
            try {
                handler.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return handler.getResult();
    }
    public Map<Integer, RegisterHandler> getCacheMap(){
        return cacheMap;
    }
}
