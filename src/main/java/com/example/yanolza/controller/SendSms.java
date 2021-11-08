package com.example.yanolza.controller;


import lombok.AllArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@AllArgsConstructor
@Controller
public class SendSms {

  @RequestMapping(value = "/sms", method = RequestMethod.GET)
  @ResponseBody
  public String sms(String phone, String code) throws UnsupportedEncodingException {

    System.out.println("phone: " + phone);
    System.out.println("code: " + code);

    String api_key = ""; //키
    String api_secret = ""; //시크릿키
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", phone); // 수신번호
    params.put("from", ""); // 발신번호  -> coolsms에 발신등록된 번호만 사용가능함
    params.put("type", "SMS"); // Message type ( SMS, LMS, MMS, ATA )
    params.put("text", "[여기어때]인증번호: "+ code); // 문자내용
    params.put("app_version", "JAVA SDK v2.2"); // application name and version

    try {
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (CoolsmsException e) {
      System.out.println(e.getMessage());
      System.out.println(e.getCode());
    }
    return "전송성공";
  }
}

