package com.islamlucas.projetantigaspi.security;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    boolean isAuthenticated();

    void autoLogin(String email, String password);

    void autoLogin(String login, String password, HttpServletRequest request);
}
