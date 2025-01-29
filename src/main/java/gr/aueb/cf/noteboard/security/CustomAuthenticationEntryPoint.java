package gr.aueb.cf.noteboard.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.aueb.cf.noteboard.dto.ResponseMessageDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ResponseMessageDTO responseMessageDTO;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (authException instanceof BadCredentialsException) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            responseMessageDTO = new ResponseMessageDTO("LoginFailure", "Username or password is incorrect");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseMessageDTO = new ResponseMessageDTO("UserNotAuthenticated", "User needs to authenticate in order to access this resource");
        }
        response.getWriter().write(objectMapper.writeValueAsString(responseMessageDTO));
    }
}
