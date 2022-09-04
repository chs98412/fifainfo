package com.fifainfo.fifainfo.dto;

import com.fifainfo.fifainfo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {

    private String userEmail;
    private String userNickname;
    private String password;



//    public UserDTO(final User user) {
//        this.name = member.getName();
//        this.friends = member.getFriends();
//        this.schedules = member.getSchedules();
//    }

    public static User toEntity(final UserDTO dto) {
        return User.builder()
                .nickname(dto.getUserNickname())
                .userEmail(dto.getUserEmail())
                .password(dto.getPassword())
                .build();
    }
}