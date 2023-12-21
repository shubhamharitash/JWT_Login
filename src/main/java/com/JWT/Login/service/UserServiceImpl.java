package com.JWT.Login.service;

import com.JWT.Login.dto.AppUser;
import com.JWT.Login.dto.Role;
import com.JWT.Login.repository.AppUserRepository;
import com.JWT.Login.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service

@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=appUserRepository.findByUsername(username);

        if (appUser==null){
            log.error("User Not Found In DataBase");
            throw new UsernameNotFoundException("User Not Found In DataBase");
        }

        log.info("User {} Found in the Database",username);

        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();

        appUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving AppUser to DB");
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepository.save(appUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving Role {} to DB",role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding Role {} to AppUser {}",roleName,username);
        AppUser appUser=appUserRepository.findByUsername(username);
        Role role=roleRepository.findByName(roleName);
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String username) {
        log.info("Getting AppUser {} from DB",username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getAppUser() {
        log.info("Getting All AppUser from DB");
        return appUserRepository.findAll();
    }

}
