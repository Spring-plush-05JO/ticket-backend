package org.example.springplusteam.controller;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.dto.user.req.UserCreateReqDto;
import org.example.springplusteam.dto.user.resp.UserCreateRespDto;
import org.example.springplusteam.dto.user.resp.UserFindOneRespDto;
import org.example.springplusteam.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<UserCreateRespDto> register(@RequestBody UserCreateReqDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
    }

    @GetMapping("/api/v1/profile")
    public ResponseEntity<UserFindOneRespDto> findUser(
            @AuthenticationPrincipal AuthUser user
    ) {
        UserFindOneRespDto respDto = userService.getUser(user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(respDto);
    }
}
