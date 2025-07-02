package me.yeseonghan.random.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
public class AddUserRequest {
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userName;
    private String userBirth;
    private String userPhoneNumber;
}
