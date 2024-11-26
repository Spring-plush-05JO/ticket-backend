package org.example.springplusteam.dto.user.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.springplusteam.domain.user.User;
import org.example.springplusteam.domain.user.UserRole;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateReqDto {
    private String email;
    private String password;
    private String nickname;
    private String address;
    private UserRole userRole;

    public static User from(UserCreateReqDto dto){
        return User.builder()
                .email(dto.email)
                .password(dto.password)
                .nickname(dto.nickname)
                .userRole(dto.userRole)
                .address(dto.address)
                .build();
    }
}