package com.sunsoor.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LectureUnlockRequest {
    List<Long> lectureId;
    SubscriptionRequest subscriptionRequest;
}
