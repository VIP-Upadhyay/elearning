package com.sunsoor.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Component
@FeignClient(name = "LoginandRegistration")
public interface UserClient {
    @GetMapping("/getUser/{userId}")
    public Map<String,Object> getCurrentUser(@PathVariable("userId") long userId, @RequestHeader("Authorization") String token);


}
