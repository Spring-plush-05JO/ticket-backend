package org.example.springplusteam.domain.user;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springplusteam.common.BaseEntity;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String address;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Builder
    public User(Long id, String email, String password, String nickname, String address, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.userRole = userRole;
    }
}
