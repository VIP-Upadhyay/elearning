package com.sunsoor.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {
    private Long id;
    private String fullName;
    private String email;
    private String mobileNo;
    private String profilePic;
}