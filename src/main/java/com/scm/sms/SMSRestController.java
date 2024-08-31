package com.scm.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SMSRestController {

    @Autowired
    SMSService smsService;

    @PostMapping("/processSMS")
    public String processSMS(@RequestBody SMSSendRequest request){

        System.out.println(request.getDesSMSNumber());
        System.out.println(request.getMessage());

        return smsService.sendSMS(request.getDesSMSNumber(), request.getMessage());
    }

}
