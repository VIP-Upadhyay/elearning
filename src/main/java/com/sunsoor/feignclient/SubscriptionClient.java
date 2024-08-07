package com.sunsoor.feignclient;

import com.sunsoor.request.SubscriptionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "wallet")
public interface SubscriptionClient {

    @PostMapping("/subscription/subscribeLecture/{teacherId}")
    public Map<String,Object> createSubscription(@PathVariable long teacherId, @RequestBody SubscriptionRequest subscription, @RequestHeader("Authorization") String token);
}
