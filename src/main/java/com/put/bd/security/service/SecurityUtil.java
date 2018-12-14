package com.put.bd.security.service;

import com.put.bd.security.model.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    private CurrentUser currentUserFromSecurityContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (CurrentUser) auth.getPrincipal();
    }

    public String getLogin() {
        return currentUserFromSecurityContext().getLogin();
    }

    public String getRole() {
        return currentUserFromSecurityContext().getRole();
    }

    public boolean isCurrentUserAdmin(){
        return getRole().equals("ROLE_ADMIN");
    }

    public boolean isThisUserCurrentlyLogged(String login) {
        return getLogin().equals(login);
    }

}
