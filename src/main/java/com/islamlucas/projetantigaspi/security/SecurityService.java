package com.islamlucas.projetantigaspi.security;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    boolean isAuthenticated();


    void autoLogin(String email, String password, HttpServletRequest request);

    void autoLogin(String email, String password);

}

    
