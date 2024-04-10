package com.example.medimateserver.config.jwt;

import com.example.medimateserver.dto.TokenDto;
import com.example.medimateserver.dto.UserDto;
import com.example.medimateserver.service.TokenService;
import com.example.medimateserver.util.GsonUtil;
import com.example.medimateserver.util.MLogger;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JwtProvider {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Sử dụng khóa mới tạo
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    public static String generateToken(String username) {
        System.out.println(key);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 40000)) // 1 day
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static String generateRefreshToken(String username) {
        System.out.println(key);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 1 day
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static String getUsernameFromToken(String token) {
        String result = "";
        try {
            result = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception ex) {
            System.out.println("Token lỗi!");
            result = "";
        }
        return result;
    }

    public static boolean validateToken(String token, UserDetails userDetails) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            MLogger.LOGGER.severe("Invalid JWT signature: {}" + ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty: {}", ex.getMessage());
        }
        return false;
    }
    public static boolean verifyToken(String accessToken, TokenDto tokenDto) {
        try {
            if (!tokenDto.getAccessToken().equals(accessToken)) {
                return false;
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(accessToken)
                    .getBody();

            // Lấy thông tin từ token
            String username = claims.getSubject();
            Date issuedAtDate = claims.getIssuedAt();
            Date expirationDate = claims.getExpiration();

            // Định dạng ngày tháng
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String issuedAt = dateFormat.format(issuedAtDate);
            String expiration = dateFormat.format(expirationDate);

            // Kiểm tra thời gian hết hạn
            if (expirationDate.before(new Date())) {
                return false;
            } else {
                return true;
            }

        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException e) {
            System.out.println("Lỗi giải mã token: " + e.getMessage());
            return false;
        }
    }
    public static boolean verifyRefreshToken(String refreshToken, TokenDto tokenDto) {
        try {
            if (!tokenDto.getRefreshToken().equals(refreshToken)) {
                return false;
            }
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(refreshToken)
                    .getBody();

            // Lấy thông tin từ token
            String username = claims.getSubject();
            Date issuedAtDate = claims.getIssuedAt();
            Date expirationDate = claims.getExpiration();

            // Định dạng ngày tháng
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String issuedAt = dateFormat.format(issuedAtDate);
            String expiration = dateFormat.format(expirationDate);

            // Kiểm tra thời gian hết hạn
            if (expirationDate.before(new Date())) {
                return false;
            } else {
                return true;
            }

        } catch (ExpiredJwtException e) {
            return false;
        } catch (JwtException e) {
            return false;
        }
    }
}
