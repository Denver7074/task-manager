package com.denver7074.taskmanager.utils;


import java.util.List;
import java.util.regex.Pattern;

public final class Constants {

    public final static List<String> LIST_FORMAT_DATE = List.of("yyyy-MM-dd", "dd.MM.yyyy", "yyyy.MM.dd", "MM/dd/yyyy");
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    public static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

}
