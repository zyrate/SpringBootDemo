package com.zyr.webapi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegisterHandler {
    private int listenerId;
    private String result;
    private List<Integer> registerd = new ArrayList();
    public RegisterHandler(int id){
        this.listenerId = id;
    }
    public boolean success(int id){
        registerd.add(id);
        if(registerd.contains(11)&&registerd.contains(22)&&registerd.contains(33)){
            setResult("registerd");
            synchronized (this){
                this.notifyAll();
            }
            return true;
        }
        return false;
    }
    public void exception(){
        setResult("error!");
        synchronized (this){
            this.notifyAll();
        }
    }

}
