package com.example.jwtsecurity.config;

import com.example.jwtsecurity.service.JwtService;
import com.example.jwtsecurity.util.UserUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserUtil userUtil;
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");

        String token=null;
        String userName=null;
        if(header!=null&&header.startsWith("Bearer ")){
            token=header.substring(7);
            try{
                userName=userUtil.getUserNameFromToken(token);
            }
            catch(IllegalArgumentException e){System.out.println("unable to get Token");}
            catch (ExpiredJwtException e){System.out.println("Token is Expired");}
        }
        else {
            System.out.println("Token does not starts with Bearer");
        }

        if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = jwtService.loadUserByUsername(userName);
            if(userUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
