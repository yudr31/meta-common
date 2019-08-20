package com.spring.boot.common.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class TwilioUtil {

    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACec6572226448e0765060822882fd6bd7";
    public static final String AUTH_TOKEN = "6cc9a9bd6079edfc8cff8e1c390dfb9e";

    public static void phoneCall(String phone){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String from = "12393603007";
        String to = "+86" + phone;
        URI uri = URI.create("http://demo.twilio.com/docs/voice.xml");
        Call call = Call.creator(new PhoneNumber(to), new PhoneNumber(from), uri).create();
        System.out.println(call.getSid());

    }

    public static void main(String[] args) {
        phoneCall("15926350676");
    }
}