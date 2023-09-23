package com.example.projetfilrouge_Spring;

import com.example.projetfilrouge_Spring.repository.entity.User;
import com.example.projetfilrouge_Spring.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Mock
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateJwtToken() {
        when(user.getUsername()).thenReturn("testuser");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        String token = jwtUtils.generateJwtToken(authentication);

        assertNotNull(token);
    }

    @Test
    void testValidateTokenWithValidToken() {
        when(user.getUsername()).thenReturn("testuser");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        String token = jwtUtils.generateJwtToken(authentication);

        boolean isValid = jwtUtils.validateToken(token);

        assertTrue(isValid);
    }

    @Test
    void testValidateTokenWithInvalidToken() {
        String invalidToken = "invalidToken";

        boolean isValid = jwtUtils.validateToken(invalidToken);

        assertFalse(isValid);
    }

    @Test
    void testGetUsernameFromToken() {
        when(user.getUsername()).thenReturn("testuser");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        String token = jwtUtils.generateJwtToken(authentication);

        String username = jwtUtils.getUsernameFromToken(token);

        assertEquals("testuser", username);
    }

    @Test
    void testGetUsernameFromInvalidToken() {
        String invalidToken = "invalidToken";

        assertThrows(Exception.class, () -> jwtUtils.getUsernameFromToken(invalidToken));
    }
}