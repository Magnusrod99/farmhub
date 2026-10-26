// Author: Magnus Rodrigues
// Roll No: 2628
package com.farmhub.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().equals("/api/health");
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing Firebase ID token");
            return;
        }

        try {
            FirebaseToken token = FirebaseAuth.getInstance()
                .verifyIdToken(header.substring(7));
            request.setAttribute("uid", token.getUid());
            chain.doFilter(request, response);
        } catch (Exception exception) {
            response.sendError(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Invalid or expired Firebase ID token"
            );
        }
    }
}
