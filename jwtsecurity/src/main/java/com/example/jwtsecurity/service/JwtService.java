package com.example.jwtsecurity.service;

import com.example.jwtsecurity.entity.AuthRequest;
import com.example.jwtsecurity.entity.AuthResponse;
import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.repository.UserRepository;
import com.example.jwtsecurity.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserUtil userUtil;

    public AuthResponse createJwtToken(AuthRequest authRequest)throws Exception{
        String userName= authRequest.getUserName();
        String userPassword= authRequest.getUserPassword();
        authenticate(userName,userPassword);
        final UserDetails userDetails = loadUserByUsername(userName);
        String generatedToken = userUtil.generateToken(userDetails);
        User user=userRepository.findByUsername(userName).get();
        return new AuthResponse(user,generatedToken);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username).get();

        if(user!=null){
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    getAuthorities(user));
        }
        else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    private Set getAuthorities(User user){
        Set authorities=new HashSet();
        user.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())));
        return authorities;
    }

    private void authenticate(String userName,String userPassword) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        }
        catch (DisabledException e){
            throw new Exception("User is disabled");
        }
        catch (BadCredentialsException e){
            throw new Exception("Bad credentials");
        }
    }
}
