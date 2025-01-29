package gr.aueb.cf.noteboard.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.aueb.cf.noteboard.dto.ResponseMessageDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO("UserNotAuthorized", "User is not allowed to visit this route");
        response.getWriter().write(objectMapper.writeValueAsString(responseMessageDTO));
    }
}
