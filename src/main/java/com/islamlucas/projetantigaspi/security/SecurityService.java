package com.islamlucas.projetantigaspi.security;

import com.islamlucas.projetantigaspi.users.User;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Transactional(readOnly = true)
public interface SecurityService {
    boolean isAuthenticated();
    User getUserLogged();
    void autoLogin(String email, String password, HttpServletRequest request);
}
