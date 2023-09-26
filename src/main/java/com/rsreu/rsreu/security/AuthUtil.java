package com.rsreu.rsreu.security;

import com.rsreu.rsreu.data.entity.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtil {

    public static UserInfo getUserFromContext() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            return (UserInfo) user;
        }
        return null;

    }
}
