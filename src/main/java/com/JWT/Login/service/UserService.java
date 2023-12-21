package com.JWT.Login.service;

import com.JWT.Login.dto.AppUser;
import com.JWT.Login.dto.Role;

import java.util.List;

public interface UserService {
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    AppUser getAppUser(String username);
    List<AppUser>getAppUser();

}
