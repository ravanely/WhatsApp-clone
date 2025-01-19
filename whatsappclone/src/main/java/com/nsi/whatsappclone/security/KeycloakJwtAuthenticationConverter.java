package com.nsi.whatsappclone.security;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author : ravanely
 * @Project : whatsapp-clone
 * @Date : dim. 19 janvier 2025 1:23 p.m.
 **/
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        return new JwtAuthenticationToken(source, Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source).stream(), extractResourceRoles(source).stream()).collect(Collectors.toSet()));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt source) {
        var resourceAccess = new HashMap<>(source.getClaim("resource_access"));

        var external = (Map<String, List<String>>) resourceAccess.get("account");
        var roles = external.get("roles");
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.replace("-", "_"))).collect(Collectors.toSet());
    }

}
