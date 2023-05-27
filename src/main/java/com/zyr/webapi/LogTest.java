package com.zyr.webapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/logtest")
    @ResponseBody
    public void logtest(){
        while(true) {
            logger.info("testing");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
