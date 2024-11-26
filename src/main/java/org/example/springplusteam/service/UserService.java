package org.example.springplusteam.service;

import lombok.RequiredArgsConstructor;
import org.example.springplusteam.common.exception.CustomApiException;
import org.example.springplusteam.common.exception.ErrorCode;
import org.example.springplusteam.domain.user.UserRepository;
import org.example.springplusteam.dto.user.req.UserCreateReqDto;
import org.example.springplusteam.dto.user.resp.UserCreateRespDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreateRespDto addUser(UserCreateReqDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new CustomApiException(ErrorCode.EXISTS_EMAIL);
                });
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return new UserCreateRespDto(userRepository.save(UserCreateReqDto.from(dto)).getId());
    }
}
