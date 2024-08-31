package com.scm.sms;

import lombok.Data;

@Data
public class SMSSendRequest {
    private String desSMSNumber;
    private String message;
}
