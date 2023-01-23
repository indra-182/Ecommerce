package com.jdt11.ecommerce.security.component;

import com.jdt11.ecommerce.security.domain.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

@Slf4j
@Component
public class JWTUtils {

    @Value("${jwt.expiredMs}")
    private int jwtExpiredMs;


    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public static String SECRET_KEY = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIICXQIBAAKBgQDRLnxOztD8w9c9jdhScyoCdOoXooiFIr2iPoCpSEwHF8hwnPsP\n" +
            "A8QZaMm0GAgfhVLkifKiHaGdekfjKx4IKVaB7ZuI5On3xd6utGcNSkV2M8ux28bC\n" +
            "k9XFJwsysYN6e4ZPb0pL6c8pYLPgWRkx6LhYqiV0Mi5+TqQs8G4v0iuAFwIDAQAB\n" +
            "AoGAHYSRxg0pMIHyPZ1miTv3EaGt5ufom+DrIZnwTfMFx3pzrnB4fQXrBirjS6aY\n" +
            "ftoxeE5/DqFfzPkUa9oJwt0Sd8He7Hu2I2a7MaznIiFWBfJm/ei1QBzWmxy4lgXV\n" +
            "6TTtdy/wGOsTG8kfmpGfUjgfv+7YoVHNYEvF77/c/CmB4QECQQDqkTtZPwwZPlju\n" +
            "0wcsjI/DNuU2jnPnYXDZ9nY/6TvXixsnGE59LK1Xucac9zxRZzVxMiLe941kc5//\n" +
            "dQOUkafVAkEA5Et0q3v/P7XOBKuGUxaYQUZldNsNgjE69pyh6b4liapChuLoTGma\n" +
            "Qcvvn/StMPN2FblK1eh9EBtqO+o5WR2KOwJBALpwONXUsM8XqSQaAye7iUDGTyUv\n" +
            "0Jvl7QaxSIe/39qQLELEpQ4FBrolZOG5+O1StT2FdgX2iSGAbXlkDjZ2FwkCQF/S\n" +
            "nCHuZfDj6pljNXpinX2ogqVN3Jms+EbS79MgkLj70fiz/TU1jh3X77iABsHpLsNn\n" +
            "htXKQ9Qi7bBAW9nf4EkCQQCNuJhBbjT5SwNh7MP4C/gfBapa7SMDr1PmrZDuBl53\n" +
            "SMutU+X6AwfHbDHkSvMOTUukxU2ltD+BofbDkLmJnbGY\n" +
            "-----END RSA PRIVATE KEY-----";

    public String generateToken(Authentication authentication) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PemReader pemReader = new PemReader(new StringReader(SECRET_KEY));
        PemObject pemObject = pemReader.readPemObject();
        KeyFactory factory = KeyFactory.getInstance("RSA");
        byte[] content = pemObject.getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(privKeySpec);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject((principal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiredMs))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
