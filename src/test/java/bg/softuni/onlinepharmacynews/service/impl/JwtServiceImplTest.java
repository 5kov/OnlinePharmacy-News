package bg.softuni.onlinepharmacynews.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JwtServiceImplTest {

    private static final String JWT_SECRET = "my-very-secret-key-that-is-long-enough-to-be-secure";

    @InjectMocks
    private JwtServiceImpl jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtServiceImpl(JWT_SECRET);
    }

    @Test
    void testExtractUserInformation() {
        // Given
        String jwtToken = "mockJwtToken";
        Claims mockClaims = mock(Claims.class);
        Jws<Claims> mockJws = mock(Jws.class);
        JwtParser mockParser = mock(JwtParser.class);
        JwtParserBuilder mockBuilder = mock(JwtParserBuilder.class);

        Key signingKey = jwtService.getSigningKey();

        when(mockJws.getBody()).thenReturn(mockClaims);
        when(mockClaims.getSubject()).thenReturn("testuser");
        when(mockClaims.get("roles", List.class)).thenReturn(List.of("ROLE_USER", "ROLE_ADMIN"));

        try (MockedStatic<Jwts> mockedJwts = Mockito.mockStatic(Jwts.class)) {
            // Mock the JwtParserBuilder chain
            mockedJwts.when(Jwts::parserBuilder).thenReturn(mockBuilder);
            when(mockBuilder.setSigningKey(signingKey)).thenReturn(mockBuilder);
            when(mockBuilder.build()).thenReturn(mockParser);
            when(mockParser.parseClaimsJws(jwtToken)).thenReturn(mockJws);

            // When
            UserDetails userDetails = jwtService.extractUserInformation(jwtToken);

            // Then
            assertThat(userDetails.getUsername()).isEqualTo("testuser");
            assertThat(userDetails.getAuthorities()).hasSize(2);
            assertThat(userDetails.getAuthorities().toString()).contains("ROLE_USER", "ROLE_ADMIN");
        }
    }

    @Test
    void testGetSigningKey() {
        // When
        Key key = jwtService.getSigningKey();

        // Then
        byte[] expectedKeyBytes = JWT_SECRET.getBytes(StandardCharsets.UTF_8);
        Key expectedKey = Keys.hmacShaKeyFor(expectedKeyBytes);

        assertThat(key).isEqualTo(expectedKey);
    }

    @Test
    void testExtractClaims() {
        // Given
        String jwtToken = "mockJwtToken";
        Claims mockClaims = mock(Claims.class);
        Jws<Claims> mockJws = mock(Jws.class);
        JwtParser mockParser = mock(JwtParser.class);
        JwtParserBuilder mockBuilder = mock(JwtParserBuilder.class);

        Key signingKey = jwtService.getSigningKey();

        when(mockJws.getBody()).thenReturn(mockClaims);

        try (MockedStatic<Jwts> mockedJwts = Mockito.mockStatic(Jwts.class)) {
            mockedJwts.when(Jwts::parserBuilder).thenReturn(mockBuilder);
            when(mockBuilder.setSigningKey(signingKey)).thenReturn(mockBuilder);
            when(mockBuilder.build()).thenReturn(mockParser);
            when(mockParser.parseClaimsJws(jwtToken)).thenReturn(mockJws);

            // When
            Claims claims = jwtService.extractClaims(jwtToken);

            // Then
            assertThat(claims).isEqualTo(mockClaims);
        }
    }

    @Test
    void testGetUserName() {
        // Given
        Claims mockClaims = mock(Claims.class);
        when(mockClaims.getSubject()).thenReturn("testuser");

        // When
        String username = jwtService.getUserName(mockClaims);

        // Then
        assertThat(username).isEqualTo("testuser");
    }

    @Test
    void testGetRoles() {
        // Given
        Claims mockClaims = mock(Claims.class);
        when(mockClaims.get("roles", List.class)).thenReturn(List.of("ROLE_USER", "ROLE_ADMIN"));

        // When
        List<String> roles = jwtService.getRoles(mockClaims);

        // Then
        assertThat(roles).containsExactly("ROLE_USER", "ROLE_ADMIN");
    }
}
