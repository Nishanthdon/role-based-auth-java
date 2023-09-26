package com.myFirstTask.authApi.common;

import com.myFirstTask.authApi.entity.User;
import com.myFirstTask.authApi.repo.UserRepo;
import com.myFirstTask.authApi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtTokenFIlter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final UserService userService;

    public JwtTokenFIlter(JWTUtils jwtUtils,
                          UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("tokennn : "+token);

        if (token == null || token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("verify"+ jwtUtils.verify(token));
        if (!jwtUtils.verify(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String currentUserName = jwtUtils.extractUserName(token);

        User user = userService.getUserByUserName(currentUserName);

        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
        System.out.println("userDetailsImpl.getAuthorities()"+ userDetailsImpl.getAuthorities());
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetailsImpl, null,
                userDetailsImpl.getAuthorities()
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

}
