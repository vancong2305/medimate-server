package com.example.medimateserver.config.jwt;

import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.service.UserService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.ResponseUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sound.midi.Soundbank;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        JwtProvider.gI().setAccessTime(jwtProvider.getAccessTime());
        JwtProvider.gI().setRefreshTime(jwtProvider.getRefreshTime());
        if (request.getServletPath().contains("/api/category")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getServletPath().contains("/test")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getServletPath().contains("/api/product")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getServletPath().contains("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getServletPath().contains("/api/auth/refresh")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String tokenInformation = request.getHeader("Authorization").substring(7);
            // Xác thực user
            UserDto user = GsonUtil.gI().fromJson(jwtProvider.getUsernameFromToken(tokenInformation), UserDto.class);
            UserDto userDto = userService.findById(user.getId());
            if (user==null) {
                System.out.println("123213");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write(HttpStatus.UNAUTHORIZED.value());
                return;
            }
            if (userDto.getPassword().toString().compareTo(user.getPassword().toString()) != 0 || userDto.getPhone().toString().compareTo(user.getPhone().toString()) != 0) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            // Xác thực token hiện tại có trùng với token trong csdl hay không, trùng cho qua
            TokenDto tokenDto = tokenService.findById(user.getId());
            if (JwtProvider.gI().verifyToken(tokenInformation, tokenDto)) {
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("Thông tin token sai");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(ResponseUtil.failedExpriration().getBody().toString());
        } catch (Exception ex) {
            System.out.println("Không có token lỗi ở đây");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(ResponseUtil.failedExpriration().getBody().toString());
        }
    }

}
