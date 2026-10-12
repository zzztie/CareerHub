package com.zzztie.careerhub.interceptor;

import com.zzztie.careerhub.context.UserContext;
import com.zzztie.careerhub.domain.User;
import com.zzztie.careerhub.exception.InvalidTokenException;
import com.zzztie.careerhub.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;

    public AuthInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer "))
        {
            throw new InvalidTokenException();
        }
        String token = authorization.substring(7);

        Long userId = jwtService.parseUserId(token);
        UserContext.setUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
    ){
        UserContext.clear();
    }
}
