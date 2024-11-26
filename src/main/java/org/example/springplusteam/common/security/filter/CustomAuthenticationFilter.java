package org.example.springplusteam.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.springplusteam.common.security.AuthUser;
import org.example.springplusteam.common.security.dto.LoginUserDto;
import org.example.springplusteam.common.security.jwt.JwtUtil;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(authenticationManager);
        setFilterProcessesUrl("/auth/login");
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            LoginUserDto loginUserDto = mapper.readValue(request.getInputStream(), LoginUserDto.class);
            UsernamePasswordAuthenticationToken authenticationToken
                    = UsernamePasswordAuthenticationToken.unauthenticated(loginUserDto.getEmail(), loginUserDto.getPassword());
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        SecurityContextHolder.getContext().setAuthentication(authResult);
        String token = jwtUtil.generateToken((AuthUser) authResult.getPrincipal());

        response.addHeader("Authorization", token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);

        response.getWriter().write(mapper.writeValueAsString(((AuthUser) authResult.getPrincipal()).getId()));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(400);
        response.getWriter().write("로그인 실패하였습니다.");
    }
}
