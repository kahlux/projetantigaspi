package com.islamlucas.projetantigaspi.security;

public interface SecurityService {
    boolean isAuthenticated();

    void autoLogin(String email, String password);
}
