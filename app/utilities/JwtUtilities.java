package utilities;

import com.mysql.cj.util.StringUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.MacAlgorithm;
import play.Logger;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.security.sasl.AuthenticationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtilities {

    private static final MacAlgorithm JWT_SIGNATURE_ALGO = Jwts.SIG.HS256;
    private static final String SECRET = "YourSecretKeyForJWTSigning1234567890123456";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final Logger.ALogger logger = Logger.of("common.JwtUtilities");

    public static String generateToken(final String id, final Map<String, String> payload) {
        logger.info("id " + id + " payload " + payload);
        long AUTH_TOKEN_EXPIRATION = 60 * 60 * 1000;
        final long token = AUTH_TOKEN_EXPIRATION + System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder().
                id(id).
                subject(payload.toString()).
                expiration(new Date(token))
                .signWith(key, JWT_SIGNATURE_ALGO);
        logger.info("token is generated " + token);
        return builder.compact();
    }

    public static Map<String, String> decodeToken(final String jwt) throws AuthenticationException {
        logger.info("decode token "+ jwt);
        if (StringUtils.isNullOrEmpty(jwt)) {
            throw new AuthenticationException("Invalid Token");
        }
        Map<String, String> payload = new HashMap<>();
        Claims claims = (Claims) Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(jwt)
                .getPayload();

        payload.put("id", claims.getId());
        payload.put("payload", claims.getSubject());
        return payload;
    }
}
