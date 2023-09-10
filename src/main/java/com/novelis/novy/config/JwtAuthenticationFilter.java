package com.novelis.novy.config;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override

    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,@Nonnull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader= request.getHeader("Authorization");// to extract the header
        final String jwtToken;
        final String userName;
        if(authHeader == null ||!authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        jwtToken = authHeader.substring(7);
        userName= jwtService.extractUserName(jwtToken);//extract  JWT Token
        // check if we have the username and it is not authenticated

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null ){//user not connected

            //get userDetails from database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            //check if the token still valid
            if(jwtService.isTokenValid(jwtToken,userDetails)){
                Collection<? extends GrantedAuthority> authorities = jwtService.extractAuthorities(jwtToken);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                       authorities
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                //update the security context holder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request,response);
        }
    }
}
