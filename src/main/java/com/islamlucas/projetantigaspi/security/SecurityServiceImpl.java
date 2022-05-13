package com.islamlucas.projetantigaspi.security;

import com.islamlucas.projetantigaspi.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserService userDetailsService;

    private final AuthenticationManager authenticationManager;
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @Override
    public void autoLogin(String email, String password) {

    }

    @Override
    public void autoLogin(String login, String password, HttpServletRequest request) {
        try {
            request.login(login, password);
        } catch (ServletException ignore) {
        }
    }

    @Override
    public void autoLogin(String email, String password) {

    }
}
