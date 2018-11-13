package com.censof.myfi.hidefmyfi.service;


public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}