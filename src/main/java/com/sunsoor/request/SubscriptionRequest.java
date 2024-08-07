package com.sunsoor.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubscriptionRequest {
    private SubscriptionType type;
    private double subscriptionAmount;
    private String pincode;
    private String gstNo;
    private Long userId;
    private Long lectureUserId;
}
