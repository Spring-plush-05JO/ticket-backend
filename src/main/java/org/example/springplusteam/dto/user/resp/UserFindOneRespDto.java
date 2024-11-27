package org.example.springplusteam.dto.user.resp;

import lombok.Getter;
import lombok.val;
import org.example.springplusteam.domain.user.User;

@Getter
public class UserFindOneRespDto {
    private Long id;
    private String email;
    private String nickname;
    private String address;

    public UserFindOneRespDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getEmail();
        this.address = user.getAddress();
    }
}
