package com.example.medimateserver.config.jwt;

import com.example.medimateserver.util.ResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/category")) {
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

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        // Kiểm tra header và lấy token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                username = jwtProvider.getUsernameFromToken(token);
                if (username.equals("")) {
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                    response.getWriter().write(ResponseUtil.failedExpriration().getBody().toString());
                    return;
                }
            } catch (ExpiredJwtException e) {
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.getWriter().write(ResponseUtil.failedExpriration().getBody().toString());
                return;
            }
        }
        System.out.println("Không được vào đây");

        // Xác thực nếu có username và token chưa được xác thực trước đó
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtProvider.validateToken(token, userDetails)) { // Kiểm tra token còn hiệu lực
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
