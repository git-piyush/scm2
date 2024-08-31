package com.scm.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    @Value("${twilio.account_sid}")
    String ACCOUNT_SID;

    @Value("${twilio.auth_token}")
    String AUTH_TOKEN;

    @Value("${twilio.trial_number}")
    String OUTGOING_SMS_NUMBER;

    @PostConstruct
    private void setup(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public String sendSMS(String number, String smsMessage){

        Message message = Message.creator(
                new PhoneNumber(number),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMessage).create();

        return message.getStatus().toString();
    }

}
