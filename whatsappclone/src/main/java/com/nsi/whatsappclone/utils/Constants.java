package com.nsi.whatsappclone.utils;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : dim. 19 janvier 2025 1:16 p.m.
 **/
public class Constants {

    public static final String[] SECURITY_IGNORE_URL = {"/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/ws/**"};

    public static final String[] HTTP_VERBS = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};
    public static final String CLIENT_URL = "http://localhost:4200";
}
