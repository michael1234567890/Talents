package com.phincon.talents.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class SecureController {

    @RequestMapping(value="/secure", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello() {
        return "Secure Hello!";
    }

}
