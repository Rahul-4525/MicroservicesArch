package com.microservices.PaymentInitService.util;

import io.jsonwebtoken.Jwts;

public class JwtUtil {
    private static final String SECRET = "34846d4c-4a66-4657-8b63-c82928809339";
    private static final long EXPIRATION_TIME = 3_600_000;

    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
