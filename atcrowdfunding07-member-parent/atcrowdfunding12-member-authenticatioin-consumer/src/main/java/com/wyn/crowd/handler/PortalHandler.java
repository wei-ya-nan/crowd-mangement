package com.wyn.crowd.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@Controller
public class PortalHandler {

    @RequestMapping("/")
    public String PortalIndex(){
        return "portal";
    }

}
