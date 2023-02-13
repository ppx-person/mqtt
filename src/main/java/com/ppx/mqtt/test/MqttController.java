package com.ppx.mqtt.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
 
 
@RestController
@RequestMapping("/mqtt")
public class MqttController {
 
    @Resource
    private MqttGateway mqttGateway;
 
    @GetMapping("/send")
    public String send() {
    	MyMessage myMessage = new MyMessage();
    	// /abc/sensor匹配不上+/sensor，匹配上abc/sensor
    	myMessage.setTopic("abc/sensor");
    	myMessage.setContent("xxxxxx01");
        // 发送消息到指定主题
        mqttGateway.sendToMqtt(myMessage.getTopic(), 1, myMessage.getContent());
        return "send topic: " + myMessage.getTopic()+ ", message : " + myMessage.getContent();
    }
 
}
