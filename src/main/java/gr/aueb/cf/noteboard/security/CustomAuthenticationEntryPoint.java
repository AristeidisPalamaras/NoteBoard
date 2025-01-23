package gr.aueb.cf.noteboard.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String jsonResponse;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (authException instanceof BadCredentialsException) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            jsonResponse = "{\"code\": \"LoginFailure\", \"description\": \"Username or password is incorrect\"}";
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            jsonResponse = "{\"code\": \"UserNotAuthenticated\", \"description\": \"User needs to authenticate in order to access this route\"}";
        }
        response.getWriter().write(jsonResponse);
    }
}
